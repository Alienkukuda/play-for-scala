package controllers

import akka.actor.Status.Success
import javax.inject.Inject
import models.loginSuccessMsg
import play.api.mvc._
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json.Json

import scala.concurrent.Future

class UserLoginController @Inject() (cc: ControllerComponents) extends AbstractController(cc){
	val userService = new UserService

	def login = Action.async {
		Future{
			Ok(views.html.UserLogin("用户登录"))
		}
	}

	def loginSuc(userName: String) = Action.async { implicit request =>
		Future {
			print(userName)
			val msgMsg = loginSuccessMsg("用户主页", userName)
			Ok(views.html.loginSuc(msgMsg))
		}
	}

	def doLogin = Action.async(parse.form(loginForm)) { implicit request =>
		val (userName, password) = request.body
		for {
			userR <- userService.getUser(userName)
		} yield {
			userR match {
				case Some(r) => {
					if(password.equals(r.password))
						Ok(Json.obj("suc" -> true, "userName" -> r.userName))
					else
						Ok(Json.obj("suc" -> false))
				}
				case _ => Ok("用户不存在")
			}
		}
	}
	val loginForm = Form(
		tuple(
			"userName" -> text,
			"password" -> text
		)
	)
}
