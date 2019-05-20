package controllers

import com.google.gson.Gson
import data.repository.UserRepository
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage
import services.auth.UnAuthAction

class AuthController @Inject()(cc: ControllerComponents,
                               unAuthAction: UnAuthAction,
                               repo: UserRepository,
                               message: ApiJsonMessage,
                               gson: Gson)
  extends AbstractController(cc){

  def signUp() = unAuthAction{ implicit request =>
    request.body.asJson.map { json =>
      (json \ "username").asOpt[String].map { username =>
        (json \ "password").asOpt[String].map { password =>
          (json \ "phoneNumber").asOpt[String].map { phoneNumber =>
            (json \ "name").asOpt[String].map { name =>
              try {
                Ok(gson.toJson(repo.signUp(username, password, phoneNumber, name)))
              }
              catch {
                case e: NoSuchElementException =>
                  BadRequest(message.error(e.getLocalizedMessage))
                case e: Exception =>
                  InternalServerError(message.error(e.getLocalizedMessage))
              }
            }.getOrElse{
              BadRequest(message.error("Expecting name"))
            }
          }.getOrElse{
            BadRequest(message.error("Expecting phoneNumber"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting password"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting username"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting user data"))
    }
  }

  def login() = unAuthAction{ implicit request =>
    request.body.asJson.map {json =>
      (json \ "username").asOpt[String].map{username =>
        (json \ "password").asOpt[String].map{password =>
          try{
            Ok(gson.toJson(repo.login(username, password)))
          }
          catch{
            case e: NoSuchElementException =>
              BadRequest(message.error(e.getLocalizedMessage))
            case e: Exception =>
              InternalServerError(message.error(e.getLocalizedMessage))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting password"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting username"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting username and password"))
    }
  }
}
