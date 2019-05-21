package data.store.MongoStore

import java.util

import data.{MorphiaDAO, StoreProvider}
import data.entity.Order
import data.store.OrderStore
import javax.inject.Inject
import org.bson.types
import org.bson.types.ObjectId

class MOrderStore @Inject()(private val storeProvider: StoreProvider)
  extends OrderStore{
  override def find(orderId: String): Order = {
    val objectId = new ObjectId(orderId)
    this.storeProvider.get().get(classOf[Order], objectId)
  }

  override def findByUserId(userId: String): util.List[Order] = {
    val query = this.storeProvider.get().find(classOf[Order])
    query.and(
      query.criteria(
        "userId"
      ).equal(userId)
    )
    query.asList()
  }

  override def findAll(): util.List[Order] = {
    val query = this.storeProvider.get().find(classOf[Order])
    query.asList()
  }

  override def update(order: Order): Order = {
    val entityId = storeProvider.get().merge(order).getId
    this.find(entityId.toString)
  }

  override def save(order: Order): Order = {
    val entityId = new MorphiaDAO[Order].save(this.storeProvider.get(), order).getId
    this.find(entityId.toString)
  }

  override def delete(orderId: String): Boolean = {
    val query = this.storeProvider.get().find(classOf[Order])
    query.and(
      query.criteria(
        "id"
      ).equal(new types.ObjectId(orderId))
    )
    this.storeProvider.get().delete(query)
    true
  }

  override def findCompleted(): util.List[Order] = {
    val query = this.storeProvider.get().find(classOf[Order])
    query.and(
      query.criteria(
        "completed"
      ).equal(true)
    )
    query.asList()
  }
}
