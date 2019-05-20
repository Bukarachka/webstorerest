package data.model.impl

import com.google.gson.Gson
import data.entity.User
import data.model.UserModel
import data.repository.UserRepository
import javax.inject.Inject

case class RUser(
                id: String,
                username: String,
                phoneNumber: String,
                name: String,
                token: String
                )

class SimpleUserModel @Inject()(repo: UserRepository,
                                gson: Gson)
  extends UserModel{

  private def map(user: User): RUser = {
    RUser(user.getId, user.getUsername, user.getPhoneNumber, user.getName, user.getToken)
  }

  override def login(username: String, password: String): String = {
    val user = repo.login(username, password)
    gson.toJson(map(user))
  }

  override def signUp(username: String, password: String, phoneNumber: String, name: String): String = {
    val user = repo.signUp(username, password, phoneNumber, name)
    gson.toJson(map(user))
  }
}
