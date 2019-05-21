package controllers

import java.util.Date

import data.entity.Order
import data.model.OrderModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage
import services.auth.{AdminAction, AuthAction}

class OrderController @Inject()(cc: ControllerComponents,
                                authAction: AuthAction,
                                adminAction: AdminAction,
                                model: OrderModel,
                                message: ApiJsonMessage)
  extends AbstractController(cc){

  def findBy(id: String) = adminAction{
    try {
      Ok(model.findBy(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def findByUserId(userId: String) = authAction{ implicit request =>
    if(userId != request.userId){
      Unauthorized("")
    }else {
      try {
        Ok(model.findByUserId(userId))
      }
      catch {
        case e: Exception =>
          InternalServerError(message.error(e.getLocalizedMessage))
      }
    }
  }

  def getAll = adminAction{
    try{
      Ok(model.getAll())
    }
    catch{
      case e:Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def update = adminAction{ implicit request =>
    request.body.asJson.map { json =>
      ( json \ "id").asOpt[String].map { id =>
        ( json \ "userId").asOpt[String].map { userId =>
          ( json \ "postId").asOpt[String].map { postId =>
            ( json \ "comment").asOpt[String].map { comment =>
              ( json \ "date").asOpt[Long].map { date =>
                ( json \ "completed").asOpt[Boolean].map { completed =>
                  try{
                    Ok(model.update(new Order(id, userId, postId, comment, new Date(date), completed)))
                  }
                  catch {
                    case e: Exception =>
                      InternalServerError(message.error(e.getLocalizedMessage))
                  }
                }.getOrElse {
                  BadRequest(message.error("Expecting completed"))
                }
              }.getOrElse {
                BadRequest(message.error("Expecting date"))
              }
            }.getOrElse {
              BadRequest(message.error("Expecting comment"))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting postId"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting userId"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting id"))
      }
    }.getOrElse {
      BadRequest(message.error("Expecting order data"))
    }
  }

  def save = authAction{ implicit request =>
    request.body.asJson.map { json =>
      ( json \ "userId").asOpt[String].map { userId =>
        ( json \ "postId").asOpt[String].map { postId =>
          ( json \ "comment").asOpt[String].map { comment =>
            try{
              Ok(model.save(new Order(userId, postId, comment, new Date(), false)))
            }
            catch {
              case e: Exception =>
                InternalServerError(message.error(e.getLocalizedMessage))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting comment"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting postId"))
        }
      }.getOrElse {
        BadRequest(message.error("Expecting userId"))
      }
    }.getOrElse {
      BadRequest(message.error("Expecting order data"))
    }
  }

  def delete(id: String) = adminAction{
    try{
      Ok(message.success(model.delete(id)))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }
}
