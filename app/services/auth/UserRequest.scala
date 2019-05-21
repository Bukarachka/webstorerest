package services.auth

import play.api.mvc.{Request, WrappedRequest}

case class UserRequest[A](userId: String, request: Request[A])
  extends WrappedRequest[A](request)
