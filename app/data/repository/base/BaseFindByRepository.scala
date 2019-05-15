package data.repository.base

trait BaseFindByRepository[T] {
  def findBy(id: String): T
}
