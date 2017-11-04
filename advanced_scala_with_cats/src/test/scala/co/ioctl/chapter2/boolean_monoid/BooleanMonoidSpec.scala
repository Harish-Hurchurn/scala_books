package co.ioctl.chapter2.boolean_monoid

import org.scalatest.{FreeSpec, Matchers}

class BooleanMonoidSpec extends FreeSpec with Matchers {
  import cats.Monoid

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

  "The logical AND monoid" - {
    import co.ioctl.chapter2.boolean_monoid.BooleanAndMonoid._

    "should pass the associative law" - {
      "when passed true and true" in {
        associativeLaw(true, true, true) shouldBe true
      }

      "when passed false and true" in {
        associativeLaw(false, true, false) shouldBe true
      }

      "when passed true and false" in {
        associativeLaw(true, false, true) shouldBe true
      }
    }

    "should pass the identity law" - {
      "when passed true" in {
        identityLaw(true) shouldBe true
      }

      "when passed false" in {
        identityLaw(false) shouldBe true
      }
    }
  }

  "The logical OR monoid" - {
    import co.ioctl.chapter2.boolean_monoid.BooleanOrMonoid._

    "should pass the associative law" - {
      "when passed true and true" in {
        associativeLaw(true, true, true) shouldBe true
      }

      "when passed false and true" in {
        associativeLaw(false, true, true) shouldBe true
      }

      "when passed true and false" in {
        associativeLaw(true, false, true) shouldBe true
      }
    }

    "should pass the identity law" - {
      "when passed true" in {
        identityLaw(true) shouldBe true
      }

      "when passed false" in {
        identityLaw(false) shouldBe false
      }
    }
  }

  "The logical EXCLUSIVE OR monoid" - {
    import co.ioctl.chapter2.boolean_monoid.BooleanExclusiveOrMonoid._

    "should pass the associative law" - {
      "when passed true and true" in {
        associativeLaw(true, true, true)
      }

      "when passed false and true" in {
        associativeLaw(false, true, true)
      }

      "when passed true and false" in {
        associativeLaw(true, false, true)
      }
    }

    "should pass the identity law" - {
      "when passed true" in {
        identityLaw(true)
      }

      "when passed false" in {
        identityLaw(false)
      }
    }
  }
}
