package models

case class User (
	id: Long = 0,
	email: String,
	userName: String,
	city: String,
	password: String
)
case class loginSuccessMsg (
	title: String,
	userName: String
)
