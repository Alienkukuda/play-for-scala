package controllers

import akka.actor.Status.Success
import javax.inject.Inject
import models.User
import play.api.mvc._
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import scala.concurrent.Future

class UserRegisterController @Inject() (cc: ControllerComponents) extends AbstractController(cc){
	val userService = new UserService

	def register = Action {
		Ok(views.html.UserRegister("用户注册"))
	}

	def doRegister = Action.async(parse.form(userForm)) { implicit request =>
		val (email, userName, avatar, city, password) = request.body
		//		println(userData)
		//		val newUser = User(userData.email, userData.userName,userData.city,userData.password)
		val user = User(
			email = email,
			userName = userName,
			avatar = avatar,
			city = city,
			password = password
		)
		for {
			id <- userService.addUser(user)
			userR <- userService.getUser(id)
		} yield {
			println("--------" + userR)
			userR match {
				case Some(r) => Ok(r.userName.toString + "注册成功")
				case _ => Ok("注册失败")
			}
		}
	}

	val userForm = Form(
		tuple(
			"email" -> email,
			"userName" -> text,
			"avatar" -> optional(text),
			"city" -> text,
			"password" -> text
		)
	)
}
