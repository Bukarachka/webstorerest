package data.repository.impl

import data.entity.User
import data.repository.UserRepository
import data.store.UserStore
import javax.inject.Inject
import services.auth.BearerTokenGenerator

class SimpleUserRepository @Inject()(private val store: UserStore,
                                     private val tokenGenerator: BearerTokenGenerator)
  extends UserRepository{
  
  override def login(username: String, password: String): User = {
    val user = store.find(username, password)
    if(user == null){
      throw new NoSuchElementException("There is no such user!")
    }else{
      user.updateToken(tokenGenerator.generateSHAToken(username))
      user
    }
  }

  override def signUp(username: String, password: String, phoneNumber: String, name: String): User = {
    val user = store.findByUsername(username)
    if(user != null){
      throw new IllegalArgumentException("User already exists")
    }else{
      store.save(new User(username, password, phoneNumber, name, tokenGenerator.generateSHAToken(username)))
    }
  }
}
