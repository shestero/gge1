object DemoDBApp extends App {
  val app = "gge1"

  println(s"HELLO\t$app")

  try { DBPostgres.mycreate() } catch {
    case e: Throwable => println("ERROR during CREATE: " + e.getMessage)
  }

  try {
    println("Insert record id=" + DBPostgres.myinsert("John", "Melmoth"))
    val id = DBPostgres.myinsert("Alonzo", "Monçada")
    println("Insert record id=" + id)
    println("Insert record id=" + DBPostgres.myinsert("Isidora", "Guzman"))

    DBPostgres.myselect()

    DBPostgres.mydelete(id)
    println(s"Record with id=$id deleted.")

    DBPostgres.myselect()

  } catch {
    case e: Throwable =>
      println("ERROR (INSERT, DELETE, SELECT): " + e.getMessage)
  } finally {
    DBPostgres.mydrop()
  }

  println(s"BYE\t$app")
}
