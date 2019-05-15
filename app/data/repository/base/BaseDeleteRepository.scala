package data.repository.base

trait BaseDeleteRepository {
  def delete(id: String): Boolean
}
