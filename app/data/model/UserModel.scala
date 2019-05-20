package data.model

trait UserModel {

  def login(username: String, password: String): String
  def signUp(username: String, password: String, phoneNumber: String, name: String): String
}
