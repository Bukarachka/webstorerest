package data.store

import data.entity.Post
import java.util

trait PostStore {

  def find(postId: String): Post
  def findAll(): util.List[Post]
  def update(post: Post): Post
  def save(post: Post): Post
  def delete(postId: String): Boolean
}
