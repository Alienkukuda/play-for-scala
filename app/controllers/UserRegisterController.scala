package controllers

import javax.inject.Inject
import models.User
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.UserService

class UserRegisterController @Inject() (cc: ControllerComponents) extends AbstractController(cc){

	def register = Action {
		Ok(views.html.UserRegister("用户注册"))
	}
	def doRegister = Action { implicit request: Request[AnyContent] =>
		val userservice = new UserService
		Ok(views.html.UserRegister("用户注册"))
	}
}
