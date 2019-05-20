package data.store.MongoStore

import data.{MorphiaDAO, StoreProvider}
import data.entity.User
import data.store.UserStore
import javax.inject.Inject
import org.bson.types.ObjectId

class MUserStore @Inject()(private val storeProvider: StoreProvider) extends UserStore{
  override def find(userId: String): User = {
    val objectId = new ObjectId(userId)
    this.storeProvider.get().get(classOf[User], objectId)
  }

  override def findByUsername(username: String): User = {
    val query = this.storeProvider.get().find(classOf[User])
    query.and(
      query.criteria(
        "username"
      ).equal(username)
    )
    query.get()
  }

  override def find(username: String, password: String): User = {
    val query = this.storeProvider.get().find(classOf[User])
    query.and(
      query.criteria(
        "username"
      ).equal(username),
      query.criteria(
        "password"
      ).equal(password)
    )
    query.get()
  }

  override def update(user: User): User = {
    val entityId = this.storeProvider.get().merge(user).getId
    this.find(entityId.toString)
  }

  override def save(user: User): User = {
    val entityId = new MorphiaDAO[User].save(this.storeProvider.get(), user).getId
    this.find(entityId.toString)
  }
}
