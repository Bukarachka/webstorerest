package data.config

trait ConnectionConfig {

  def hostUrl(): String = s"mongodb://${this.userName()}:${this.password()}@${this.host()}:${this.port()}/${this.dbName()}"

  def dbName(): String

  def userName(): String

  def password(): String

  def host(): String

  def port(): String

}
