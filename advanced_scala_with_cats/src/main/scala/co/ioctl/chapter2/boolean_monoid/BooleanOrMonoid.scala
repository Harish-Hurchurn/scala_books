package co.ioctl.chapter2.boolean_monoid

object BooleanOrMonoid {
  import cats.Monoid

  implicit val booleanOrMonoid = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean) = x || y
    override def empty = true
  }
}