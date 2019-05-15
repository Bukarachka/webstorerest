package data.repository.base

trait BaseUpdateRepository[T] {
  def update(obj: T): T
}
