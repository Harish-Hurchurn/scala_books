package co.ioctl.chapter2.superadder

object SuperAdder {
  import cats.Monoid
  import cats.implicits._

  def add(items: List[Int]): Int =
    items.sum

  def add[A : Monoid](items: List[A]): A =
    items.foldLeft(Monoid[A].empty)(_ |+| _)

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def combine(x: Order, y: Order) = Order(x.totalCost + y.totalCost, y.quantity + y.quantity)
    override def empty = Order(0, 0)
  }
}
