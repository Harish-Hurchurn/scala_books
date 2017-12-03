package co.ioctl.chapter1.printable

import org.scalatest.{FreeSpec, Inside, Matchers}

class PrintableSpec extends FreeSpec with Matchers with Inside {
  "Using the Printable typeclass" - {
    "it should be possible to return a new string" in {
      import co.ioctl.chapter1.printable.PrintableInstances._
      import co.ioctl.chapter1.printable.PrintableSyntax._

      "10".Format shouldBe "10"
    }

    "it should be possible to print to the console" in {
      import co.ioctl.chapter1.printable.PrintableInstances._
      import co.ioctl.chapter1.printable.PrintableSyntax._

      "10".print shouldBe()
    }

    "it should be possible to extend the typeclass and define and print a new type" in {
      import co.ioctl.chapter1.printable.PrintableSyntax._

      final case class Cat(name: String, age: Int, color: String)

      implicit val catPrintable: Printable[Cat] = (value: Cat) => {
        val catName = value.name
        val catAge = value.age
        val catColor = value.color

        s"$catName is a $catAge year-old $catColor cat."
      }

      inside(Cat("Moggy", 1, "White").Format) {
        case str => str shouldBe "Moggy is a 1 year-old White cat."
      }
    }
  }

  "Using the Cats show typeclass" - {
    "it should be possible to extend the typeclass and define and print a new type" in {
      import cats.Show
      import cats.implicits._

      final case class Cat(name: String, age: Int, color: String)

      implicit val catPrintable: Show[Cat] = (value: Cat) => {
        val catName = value.name
        val catAge = value.age
        val catColor = value.color

        s"$catName is a $catAge year-old $catColor cat."
      }

      inside(Cat("Moggy", 1, "White").show) {
        case str => str shouldBe "Moggy is a 1 year-old White cat."
      }
    }
  }
}
