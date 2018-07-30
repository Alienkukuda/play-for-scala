package controllers


import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import javax.inject._
import services.EmployeeService

class EmployeeController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  val employeeService = new EmployeeService

  def employeeList = Action { implicit request: Request[AnyContent] =>
    val employee = employeeService.getEmployees
    Ok(views.html.employeeList(employee))
  }
}
