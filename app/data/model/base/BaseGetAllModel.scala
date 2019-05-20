package domain.models.base

trait BaseGetAllModel {
  def getAll(userId: String): String
}
