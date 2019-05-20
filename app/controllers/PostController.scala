package controllers

import data.entity.Post
import data.model.PostModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage
import services.auth.{AdminAction, AuthAction, UnAuthAction}

class PostController @Inject()(cc: ControllerComponents,
                               authAction: AuthAction,
                               unAuthAction: UnAuthAction,
                               adminAction: AdminAction,
                               model: PostModel,
                               message: ApiJsonMessage)
  extends AbstractController(cc){

  def findBy(id: String) = unAuthAction{
    try{
      Ok(model.findBy(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def getAll = unAuthAction{
    try {
      Ok(model.getAll())
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def update = adminAction{ implicit request =>
    request.body.asJson.map { json =>
      ( json \ "id").asOpt[String].map { id =>
        ( json \ "title").asOpt[String].map { title =>
          ( json \ "description").asOpt[String].map { description =>
            ( json \ "price").asOpt[Double].map { price =>
              ( json \ "image").asOpt[String].map { image =>
                try {
                  Ok(model.update(new Post(id, title, description, price, image)))
                }
                catch {
                  case e: Exception =>
                    InternalServerError(message.error(e.getLocalizedMessage))
                }
              }.getOrElse {
                BadRequest(message.error("Expecting image as link"))
              }
            }.getOrElse {
              BadRequest(message.error("Expecting price"))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting description"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting title"))
        }
      }.getOrElse {
        BadRequest(message.error("Expecting id"))
      }
    }.getOrElse {
      BadRequest(message.error("Expecting post data"))
    }
  }

  def save = adminAction{ implicit request =>
    request.body.asJson.map { json =>
      ( json \ "title").asOpt[String].map { title =>
        ( json \ "description").asOpt[String].map { description =>
          ( json \ "price").asOpt[Double].map { price =>
            ( json \ "image").asOpt[String].map { image =>
              try{
                Created(model.save(new Post(title, description, price, image)))
              }
              catch {
                case e: Exception =>
                  InternalServerError(message.error(e.getLocalizedMessage))
              }
            }.getOrElse {
              BadRequest(message.error("Expecting image"))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting price"))
          }
        }.getOrElse {
          BadRequest(message.error("Expectin description"))
        }
      }.getOrElse {
        BadRequest(message.error("Expecting title"))
      }
    }.getOrElse {
      BadRequest(message.error("Expecting post data"))
    }
  }

  def delete(id: String) = adminAction{
    try {
      Ok(message.success(model.delete(id)))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }
}
