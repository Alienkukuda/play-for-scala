package models

case class User (
	id: Long = 0,
	email: String,
	username: String,
	avatar: String,
	city: String,
	password: String
)