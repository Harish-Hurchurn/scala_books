package co.ioctl.chapter3.invariant_functor

import org.scalatest.{FreeSpec, Matchers}

class InvariantFunctorSpec extends FreeSpec with Matchers {
  "It should" - {
    "be possible to encode an int to a string" in {
      implicit val intCoder = new InvariantFunctor[Int] {
        import scala.util.Try

        override def decode(value: String) = Try(value.toInt).toOption

        override def encode(value: Int) = value.toString
      }

      def encode(value: Int)(implicit i: InvariantFunctor[Int]): String =
        i.encode(value)

      encode(10) shouldBe "10"
    }

    "when using the Invariant functor be possible" - {
      final case class Box[A](value: A)

      implicit def decode[A](value: A)(implicit i: InvariantFunctor[A]): Option[A] =
        i.decode(value)

      implicit def encode[A](value: A)(implicit i: InvariantFunctor[A]): String =
        i.encode(value)

      implicit def boxConverter[A](implicit i: InvariantFunctor[A]): InvariantFunctor[Box[A]] =
        i.imap[Box[A]](Box[A], _.value)

      "to convert from a Box(123) to an integer 123" in {
        implicit val x = new InvariantFunctor[Int] {
          override def decode(value: String): Option[Int] = Some(value.toInt)
          override def encode(value: Int): String = value.toString
        }

        encode(Box(123)) shouldBe "123"
      }

      "to convert a Box(123) to a Some(Box(123)" in {
        implicit val x = new InvariantFunctor[Int] {
          override def decode(value: String): Option[Int] = Some(value.toInt)
          override def encode(value: Int): String = value.toString
        }

        decode(Box[Int](123)) shouldBe Some(Box(123))
      }
    }
  }
}
