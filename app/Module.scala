import com.google.inject.AbstractModule
import java.time.Clock

import com.google.gson.Gson
import data.model.{OrderModel, PostModel, UserModel}
import data.model.impl.{SimpleOrderModel, SimplePostModel, SimpleUserModel}
import data.repository.{OrderRepository, PostRepository, UserRepository}
import data.repository.impl.{SimpleOrderRepository, SimplePostRepository, SimpleUserRepository}
import data.store.MongoStore.{MOrderStore, MPostStore, MUserStore}
import data.store.{OrderStore, PostStore, UserStore}
import services.auth.BearerTokenGenerator
import services.{ApiJsonMessage, ApplicationTimer, AtomicCounter, Counter}

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

    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    // Set AtomicCounter as the implementation for Counter.
    bind(classOf[Counter]).to(classOf[AtomicCounter])
  }

  private def bindModel = {
    bind(classOf[UserModel]).to(classOf[SimpleUserModel])
    bind(classOf[PostModel]).to(classOf[SimplePostModel])
    bind(classOf[OrderModel]).to(classOf[SimpleOrderModel])
  }

  private def bindRepositories = {
    bind(classOf[UserRepository]).to(classOf[SimpleUserRepository])
    bind(classOf[PostRepository]).to(classOf[SimplePostRepository])
    bind(classOf[OrderRepository]).to(classOf[SimpleOrderRepository])
  }

  private def bindStore = {
    bind(classOf[UserStore]).to(classOf[MUserStore])
    bind(classOf[PostStore]).to(classOf[MPostStore])
    bind(classOf[OrderStore]).to(classOf[MOrderStore])
  }
}
