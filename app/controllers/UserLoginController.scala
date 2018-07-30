package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.UserService

class UserLoginController @Inject() (cc: ControllerComponents) extends AbstractController(cc){
	val userService = new UserService
	def login = Action {
		Ok(views.html.UserLogin("用户登录"))
	}
	def doLogin(username: String, password: String) = Action {
		val mess = username
		Ok(mess)
	}
}
