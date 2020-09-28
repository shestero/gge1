import java.sql.DriverManager

import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{KeyedEntity, Schema, Session, SessionFactory}
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.annotations.Column

object DBPostgres extends Schema with DBInterface {

  private val host = "localhost"
  private val db = "squeryl"
  private val table = "PERSON"
  private val user = "shestero"
  private val pwd = "1"

  private val url = s"jdbc:postgresql://$host/$db"
  println(s"NOTE: Using URL=$url")

  Class.forName("org.postgresql.Driver")

  SessionFactory.concreteFactory = Some(
    () =>
      Session.create(DriverManager.getConnection(url, user, pwd),
                     new PostgreSqlAdapter))

  private case class Person(firstName: String, lastName: String, id: Long = 0L)
      extends KeyedEntity[Long]

  private val persons = table[Person]("PERSON")

  on(persons)(
    s =>
      declare(
        s.id is (unique, autoIncremented("persons_seq"))
    ))

  // ====
  // Methods:

  override def mydrop(): Unit = transaction { persons.schema.drop }

  override def mycreate(): Unit = transaction { persons.schema.create }

  override def myinsert(firstName: String, lastName: String): Long =
    transaction { persons.insert(Person(firstName, lastName)).id }

  override def mydelete(id: Long) = transaction {
    persons.deleteWhere(_.id === id)
  }

  override def myselect(limit: Option[Int] = None): Unit = transaction {
    val allRecords = from(persons)(record => select(record))
    val records = limit.map(allRecords.take).getOrElse(allRecords.toIterable)
    records.foreach(println)
  }
}
