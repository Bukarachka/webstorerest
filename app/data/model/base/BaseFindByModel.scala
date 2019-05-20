package domain.models.base

trait BaseFindByModel {
  def findBy(id: String): String
}
