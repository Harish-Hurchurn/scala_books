package co.ioctl.chapter4.monads.IdMonad

import org.scalatest.{FreeSpec, Matchers}

class IdMonadSpec extends FreeSpec with Matchers {
  import co.ioctl.chapter4.Monad.IdMonad.IdMonad

  "IdMonad" - {
    val idMonad: IdMonad[Int] = new IdMonad[Int]{}
    val x = idMonad.pure(10)

    "should provide a pure method which should lift a plain value into the context of the monad" in {
      import cats.Id
      x shouldBe a [Id[_]]
    }

    "should provide a flatMap operation" in {
      val res = idMonad.flatMap(x)(_ + 1) shouldBe 11
    }

    "should provide a map operation" in {
      idMonad.map(x)(_ + 1) shouldBe 11
    }
  }
}
