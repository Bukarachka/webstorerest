package data.repository

import data.entity.User

trait UserRepository {
  def login(username: String, password: String): User
  def signUp(username: String, password: String, phoneNumber: String, name: String): User
}
