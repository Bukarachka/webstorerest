package data.model

import data.entity.Order
import domain.models.base._

import java.util

trait OrderModel
  extends BaseDeleteModel
    with BaseFindByModel
    with BaseGetAllModel
    with BaseSaveModel[Order]
    with BaseUpdateModel[Order] {

  def findByUserId(userId: String): String
  def findCompleted(): String
}
