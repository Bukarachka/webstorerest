package services.auth

import javax.inject.Inject
import play.api.http.HeaderNames
import play.api.mvc._
import services.ApiJsonMessage

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class AuthAction @Inject()(message: ApiJsonMessage, bodyParser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent]{

  override def parser: BodyParser[AnyContent] = bodyParser
  override protected def executionContext: ExecutionContext = ec

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    extractToken(request) map{token =>
      Try(validateToken(token)) match {
        case Success(_) => block(UserRequest(request))
        case Failure(exception) => Future.successful(Results.Unauthorized(message.error(exception.getLocalizedMessage)))
      }
    } getOrElse{
      Future.successful(Results.Unauthorized(message.error("Expecting token")))
    }
  }

  private def extractToken[A](request: Request[A]): Option[String] = {
    request.headers.get(HeaderNames.AUTHORIZATION)
  }

  private def validateToken(token: String): Unit = {
    if(token.length < 1){
      throw new Exception("Invalid token")
    }
  }
}
