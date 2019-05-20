package data

import com.mongodb.{MongoClient, MongoClientOptions, MongoClientURI, ReadPreference}
import data.config.ConnectionConfig
import org.mongodb.morphia.{Datastore, Morphia}


class Connection {
  val SOCKET_TIMEOUT = 60000
  val CONNECT_TIMEOUT = 15000
  val MAX_CONNECTION_IDLE_TIME = 600000

  def store(connectionConfig: ConnectionConfig): Datastore = {
    val dataStore = morphia().createDatastore(
      this.mongoClient(connectionConfig.hostUrl()),
      connectionConfig.dbName()
    )
    dataStore.ensureIndexes()
    dataStore
  }

  private def morphia(): Morphia = {
    val morphia = new Morphia
    morphia.mapPackage("data.entity")
    morphia
  }

  private def mongoClient(uri: String): MongoClient = {
    new MongoClient(
      new MongoClientURI(uri)
    )
  }

  private def clientOptions(): MongoClientOptions = {
    MongoClientOptions.builder()
      .socketTimeout(SOCKET_TIMEOUT)
      .connectTimeout(CONNECT_TIMEOUT)
      .maxConnectionIdleTime(MAX_CONNECTION_IDLE_TIME)
      .readPreference(ReadPreference.primaryPreferred())
      .build()
  }
}
