package data.model.impl

import java.util

import com.google.gson.Gson
import data.entity.Order
import data.model.OrderModel
import data.repository.OrderRepository
import javax.inject.Inject

case class ROrder(
                 id: String,
                 userId: String,
                 postId: String,
                 comment: String,
                 date: Long,
                 completed: Boolean
                 )

class SimpleOrderModel @Inject()(repo: OrderRepository,
                                 gson: Gson)
  extends OrderModel{

  private def map(order: Order): ROrder = {
    ROrder(order.getId, order.getUserId, order.getPostId, order.getComment, order.getDate.getTime, order.isCompleted)
  }

  override def getAll(): String = {
    val rOrderList = new util.ArrayList[ROrder]
    repo.getAll.forEach(order =>{
      rOrderList.add(map(order))
    })
    gson.toJson(rOrderList)
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }

  override def save(obj: Order): String = {
    val order = repo.save(obj)
    gson.toJson(map(order))
  }

  override def update(obj: Order): String = {
    val order = repo.update(obj)
    gson.toJson(map(order))
  }

  override def findBy(id: String): String = {
    val order = repo.findBy(id)
    gson.toJson(map(order))
  }

  override def findByUserId(userId: String): String = {
    val rOrderList = new util.ArrayList[ROrder]
    repo.findByUserId(userId).forEach(order =>{
      rOrderList.add(map(order))
    })
    gson.toJson(rOrderList)
  }

  override def findCompleted(): String = {
    val rOrderList = new util.ArrayList[ROrder]
    repo.findCompleted().forEach(order =>{
      rOrderList.add(map(order))
    })
    gson.toJson(rOrderList)
  }
}
