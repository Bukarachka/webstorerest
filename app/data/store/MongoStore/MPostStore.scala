package data.store.MongoStore

import java.util

import data.{MorphiaDAO, StoreProvider}
import data.entity.Post
import data.store.PostStore
import javax.inject.Inject
import org.bson.types.ObjectId

class MPostStore @Inject()(private val storeProvider: StoreProvider)
  extends PostStore{
  override def find(postId: String): Post = {
    val objectId = new ObjectId(postId)
    this.storeProvider.get().get(classOf[Post], objectId)
  }

  override def findAll(): util.List[Post] = {
    val query = this.storeProvider.get().find(classOf[Post])
    query.asList()
  }

  override def update(post: Post): Post = {
    val entityId = storeProvider.get().merge(post).getId
    this.find(entityId.toString)
  }

  override def save(post: Post): Post = {
    val entityId = new MorphiaDAO[Post].save(this.storeProvider.get(), post).getId
    this.find(entityId.toString)
  }

  override def delete(postId: String): Boolean = {
    val query = this.storeProvider.get().find(classOf[Post])
    query.and(
      query.criteria(
        "id"
      ).equal(postId)
    )
    this.storeProvider.get().delete(query)
    true
  }
}
