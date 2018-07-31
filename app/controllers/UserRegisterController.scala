package controllers

import javax.inject.Inject
import models.User
import play.api.mvc._
import services.UserService
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import scala.concurrent.Future
import play.api.mvc.Result

class UserRegisterController @Inject() (cc: ControllerComponents) extends AbstractController(cc){
	def register = Action {
		Ok(views.html.UserRegister("用户注册"))
	}

	def doRegister = Action.async { implicit request =>
		val (x, userName, city, password) = request.body
//		println(userData)
//		val newUser = User(userData.email, userData.userName,userData.city,userData.password)
		val user = User(
			email = x,
			userName = userName,
			city = city,
			password = password
		)
		val userService = new UserService
		val fut = for {
			id <- userService.addUser(user)
			userR <- userService.getUser(id)
		} yield userR
		fut.map(r => Ok(r))
		// OK("captain")
	}

	val userForm = Form(
		tuple(
			"email" -> text,
			"userName" -> text,
			"city" -> text,
			"password" -> text
		)
	)
}
