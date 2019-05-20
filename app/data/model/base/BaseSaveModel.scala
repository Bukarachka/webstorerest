package domain.models.base

trait BaseSaveModel[T] {
  def save(obj: T): String
}
