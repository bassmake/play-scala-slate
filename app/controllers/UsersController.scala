package controllers

import javax.inject.Inject

import models.User
import play.api.Logger
import play.api.libs.json.{JsError, Json}
import play.api.mvc._

/**
  * Created by miroslav.matejovsky on 30/05/16.
  */
class UsersController @Inject() extends Controller {

  def createUser() = Action(BodyParsers.parse.json) { request =>
    val userResult = request.body.validate[User]
    userResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      user => {
        Logger.info(s"Created $user")
        Ok(Json.obj("status" -> "OK", "message" -> s"User '${user.nickname}' created."))
      }
    )
  }

}
