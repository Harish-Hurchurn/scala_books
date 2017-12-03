package co.ioctl.chapter2.boolean_monoid

object BooleanAndMonoid {
  import cats.Monoid

  implicit val booleanAndMonoid = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean) = x && y
    override def empty = true
  }
}
