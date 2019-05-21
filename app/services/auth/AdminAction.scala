package services.auth

import java.util.Date

import data.store.UserStore
import javax.inject.Inject
import play.api.http.HeaderNames
import play.api.mvc._
import services.ApiJsonMessage

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class AdminAction @Inject()(message: ApiJsonMessage,
                           bodyParser: BodyParsers.Default,
                           store: UserStore)
                          (implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent]{

  override def parser: BodyParser[AnyContent] = bodyParser
  override protected def executionContext: ExecutionContext = ec

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    extractToken(request) map{token =>
      Try(validateToken(token)) match {
        case Success(userId) => block(UserRequest(userId, request))
        case Failure(exception) => Future.successful(Results.Unauthorized(message.error(exception.getLocalizedMessage)))
      }
    } getOrElse{
      Future.successful(Results.Unauthorized(message.error("Expecting token")))
    }
  }

  private def extractToken[A](request: Request[A]): Option[String] = {
    request.headers.get(HeaderNames.AUTHORIZATION)
  }

  private def validateToken(token: String): String = {
    val user = store.findByToken(token)
    if(user == null || user.getTokenExpiresDate.before(new Date()) || !user.isAdmin){
      throw new IllegalArgumentException("Blocked")
    } else {
      user.updateToken(user.getToken)
      user.getId
    }
  }
}
