package co.ioctl.chapter2.boolean_monoid

object BooleanExclusiveOrMonoid {
  import cats.Monoid

  implicit val booleanExclusiveOrMonoid = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean) = (x && !y) && (!x && y)
    override def empty = false
  }
}
