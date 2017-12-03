package co.ioctl.chapter2.superadder

import org.scalatest.{FreeSpec, Inside, Matchers}

class SuperAdderSpec extends FreeSpec with Inside with Matchers {
  "SuperAdder" - {
    "should be able to add numbers together in a List of int" in {

      SuperAdder.add(List(1, 2, 3, 4)) shouldEqual 10
    }

    "should be able to add numbers together in a List of Options" in {
      import cats.Monoid
      import cats.implicits._

      implicit val monoid = Monoid[List[Option[Int]]]
      SuperAdder.add(List(Some(1): Option[Int], Some(2): Option[Int], Some(3): Option[Int], Some(4): Option[Int])).get shouldBe 10
    }

    "should be able to process an Order" in {
      import cats.implicits._
      import co.ioctl.chapter2.superadder.SuperAdder._

      inside(Order(10.0, 20.0) |+| Order(10.0, 20.0)) {
        case res => res.totalCost shouldEqual 20.0
          res.quantity shouldEqual 40.0
      }
    }
  }
}