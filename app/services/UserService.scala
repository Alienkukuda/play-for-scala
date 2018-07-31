package services

import com.typesafe.config.ConfigFactory
import models.User
import libs.dal.QuillIOSupport
import play.api.Configuration

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserService extends QuillIOSupport {
	val config = Configuration(ConfigFactory.load())
	lazy val quillCfg = config.get[Configuration]("Quill").underlying

	import QDB.{io => _, _}

	val Users = quote(query[User])

	def getUser: Future[Seq[User]] = QDB.io {
		Users
	}.run

	def getUser(id: Long): Future[Option[User]] = QDB.io {
		Users.filter(_.id == lift(id))
	}.run.map(_.headOption)


	def addUser(user: User) = QDB.io {
				Users.insert(lift(user)).returning(_.id)
		}.run
}