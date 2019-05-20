package domain.models.base

trait BaseUpdateModel[T] {
  def update(obj: T): String
}
