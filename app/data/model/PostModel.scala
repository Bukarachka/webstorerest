package data.model

import data.entity.Post
import domain.models.base._

trait PostModel
  extends BaseDeleteModel
    with BaseFindByModel
    with BaseGetAllModel
    with BaseSaveModel[Post]
    with BaseUpdateModel[Post]{

}
