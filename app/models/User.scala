package models

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._


/**
  * Created by miroslav.matejovsky on 30/05/16.
  */
case class User(nickname: String, email: String, phoneNumber: String)

object User {

  implicit val reads: Reads[User] = (
    (JsPath \ "nickname").read[String](minLength[String](4)) and
    (JsPath \ "email").read[String](email) and
    (JsPath \ "phone-number").read[String]
    //(JsPath \ "phone-number").read[String](pattern("""\d+""".r, "phone.number"))
  )(User.apply _)

}