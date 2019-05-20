package data.repository

import data.entity.Post
import data.repository.base._

trait PostRepository
  extends BaseGetAllRepository[Post]
    with BaseFindByRepository[Post]
    with BaseUpdateRepository[Post]
    with BaseSaveRepository[Post]
    with BaseDeleteRepository{

}
