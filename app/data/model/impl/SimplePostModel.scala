package data.model.impl

import com.google.gson.Gson
import data.entity.Post
import data.model.PostModel
import data.repository.PostRepository
import javax.inject.Inject
import java.util

case class RPost(
                id: String,
                title: String,
                description: String,
                price: Double,
                image: String
                )

class SimplePostModel @Inject()(repo: PostRepository,
                                gson: Gson)
  extends PostModel{

  private def map(post: Post): RPost = {
    RPost(post.getId, post.getTitle, post.getDescription, post.getPrice, post.getImage)
  }

  override def save(obj: Post): String = {
    val post = repo.save(obj)
    gson.toJson(map(post))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }

  override def getAll(): String = {
    val rPostList = new util.ArrayList[RPost]
    repo.getAll.forEach(post =>{
      rPostList.add(map(post))
    })
    gson.toJson(rPostList)
  }

  override def update(obj: Post): String = {
    val post = repo.update(obj)
    gson.toJson(map(post))
  }

  override def findBy(id: String): String = {
    val post = repo.findBy(id)
    gson.toJson(map(post))
  }
}
