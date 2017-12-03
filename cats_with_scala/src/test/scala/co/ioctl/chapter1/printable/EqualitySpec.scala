package co.ioctl.chapter1.printable

import org.scalatest.FreeSpec

class EqualitySpec extends FreeSpec {
  "Using the Eq typeclass" - {
    "it should be possible to compare two objects" in {
      import cats.implicits._
      import cats.kernel.Eq

      implicit val catCompare: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
        cat1.name == cat2.name &&
        cat1.age == cat2.age &&
        cat1.color == cat2.color
      }

      final case class Cat(name: String, age: Int, color: String)

      val cat1 = Cat("Garfield", 35, "orange and black")
      val cat2 = Cat("Heathcliff", 30, "orange and black")

      cat1 =!= cat2
    }

    "it should be possible to compare two Option objects" in {
      import cats.kernel.Eq
      import cats.implicits._

      implicit val catCompare: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
        cat1.name == cat2.name
        cat1.age == cat2.age
        cat1.color == cat2.color
      }

      final case class Cat(name: String, age: Int, color: String)

      val cat1 = Option(Cat("Garfield", 35, "orange and black"))
      val cat2 = Option(Cat("Heathcliff", 30, "orange and black"))

      cat1 =!= cat2
    }
  }
}
