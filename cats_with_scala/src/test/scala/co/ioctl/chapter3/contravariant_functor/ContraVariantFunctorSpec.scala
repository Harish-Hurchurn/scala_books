package co.ioctl.chapter3.contravariant_functor

import org.scalatest.{FreeSpec, Matchers}

class ContraVariantFunctorSpec extends FreeSpec with Matchers {

  def format[A](value: A)(implicit p: ContraVariantFunctor[A]): String =
    p.format(value)

  "Calling the Printable typeclass" - {
    "it should be possible to print a boolean" in {
      implicit val booleanPrintable = new ContraVariantFunctor[Boolean] {
        override def format(value: Boolean) = if (value) "yes" else "no"
      }

      format(true) shouldBe "yes"
    }

    "it should be possible to print a string" in {
      implicit val stringPrintable = new ContraVariantFunctor[String] {
        override def format(value: String) = "\"" + value + "\""
      }

      format("hello") shouldBe "\"hello\""
    }
  }

  "Calling the contramap method to extract the original value from a context" - {
    final case class Box[A](value: A)

    implicit def boxPrintable[A](implicit p: ContraVariantFunctor[A]): ContraVariantFunctor[Box[A]] =
      p.contramap[Box[A]](_.value)

    """with a Box["Hello"] should return the string Hello""" in {
      implicit val stringPrintable = new ContraVariantFunctor[String] {
        override def format(value: String) = value
      }

      format(Box("Hello")) shouldBe "Hello"
    }

    "with a Box[true] should return the boolean true" in {
      implicit val booleanPrintable = new ContraVariantFunctor[Boolean] {
        override def format(value: Boolean) = if (value) "true" else "false"
      }

      format(Box(true)) shouldBe "true"
    }

    "with a Box[false] should return the boolean false" in {
      implicit val booleanPrintable = new ContraVariantFunctor[Boolean] {
        override def format(value: Boolean) = if (value) "true" else "false"
      }

      format(Box(false)) shouldBe "false"
    }

    "with a Box[101] should return the integer 101" in {
      implicit val intPrintable = new ContraVariantFunctor[Int] {
        override def format(value: Int) = value.toString
      }

      format(Box(101)) shouldBe "101"
    }
  }
}

