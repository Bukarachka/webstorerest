package data.config


class StagingConnectionConfig extends ConnectionConfig {

  override def hostUrl(): String = s"mongodb://${this.host()}:${this.port()}/${this.dbName()}"

  override def dbName() = {
    "webstoredb"
  }

  override def userName ()= {
    ""
  }

  override def password ()= {
    ""
  }

  override def host() = {
    "localhost"
  }

  override def port() = {
    "27017"
  }
}