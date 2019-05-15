package data.repository.base

trait BaseSaveRepository[T] {
  def save(obj: T): T
}
