package data.repository.impl

import java.util

import data.entity.Post
import data.repository.PostRepository
import data.store.PostStore
import javax.inject.Inject

class SimplePostRepository @Inject()(private val store: PostStore)
  extends PostRepository{
  override def save(obj: Post): Post = {
    val post = store.find(obj.getId)
    if(post != null){
      throw new IllegalArgumentException("Object already exists")
    }else{
      store.save(obj)
    }
  }

  override def getAll: util.List[Post] = {
    store.findAll()
  }

  override def delete(id: String): Boolean = {
    val post = store.find(id)
    if(post == null){
      throw new NoSuchElementException("There is no such object")
    }else{
      store.delete(id)
    }
  }

  override def update(obj: Post): Post = {
    val post = store.find(obj.getId)
    if(post == null){
      throw new NoSuchElementException("There is no such object")
    }else{
      store.update(obj)
    }
  }

  override def findBy(id: String): Post = {
    val post = store.find(id)
    if(post == null){
      throw new NoSuchElementException("There is no such object")
    }else{
      post
    }
  }
}
