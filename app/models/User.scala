package models

case class User (
	id: Long = 0,
	email: String,
	userName: String,
	avatar: Option[String],
	city: String,
	password: String
)
case class loginSuccessMsg (
	title: String,
	userName: String
)
