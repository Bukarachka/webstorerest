package data.store

import data.entity.User

trait UserStore {

  def find(userId: String): User
  def findByToken(token: String): User
  def findByUsername(username: String): User
  def find(username: String, password: String): User
  def update(user: User): User
  def save(user: User): User
}
