import com.google.gson.Gson
import com.google.inject.AbstractModule
import data.model.UserModel
import data.model.impl.SimpleUserModel
import data.repository.UserRepository
import data.repository.impl.SimpleUserRepository
import data.store.MongoStore.MUserStore
import data.store.UserStore
import services.ApiJsonMessage
import services.auth.BearerTokenGenerator

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure() = {

    val gson = new Gson()
    bind(classOf[Gson]).toInstance(gson)

    val message = new ApiJsonMessage
    bind(classOf[ApiJsonMessage]).toInstance(message)

    val tokenGenerator = new BearerTokenGenerator
    bind(classOf[BearerTokenGenerator]).toInstance(tokenGenerator)

    this.bindModel
    this.bindRepositories
    this.bindStore
  }

  private def bindModel = {
    bind(classOf[UserModel]).to(classOf[SimpleUserModel])
  }

  private def bindRepositories = {
    bind(classOf[UserRepository]).to(classOf[SimpleUserRepository])
  }

  private def bindStore = {
    bind(classOf[UserStore]).to(classOf[MUserStore])
  }
}
