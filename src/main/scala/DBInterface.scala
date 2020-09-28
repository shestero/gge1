trait DBInterface {
  def mydrop()
  def mycreate()
  def myinsert(firstName: String, lastName: String): Long // return id
  def mydelete(id: Long)
  def myselect(limit: Option[Int]) // You can specify LIMIT if you wish
  def myselect(): Unit = myselect(None)
  def myselect(limit: Int): Unit = myselect(Some(limit))
}
