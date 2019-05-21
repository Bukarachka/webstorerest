package data.repository

import data.entity.Order
import data.repository.base._

import java.util

trait OrderRepository
  extends BaseGetAllRepository[Order]
    with BaseFindByRepository[Order]
    with BaseUpdateRepository[Order]
    with BaseSaveRepository[Order]
    with BaseDeleteRepository{

  def findByUserId(userId: String): util.List[Order]
  def findCompleted(): util.List[Order]
}
