package co.ioctl.chapter4.monads.IdMonad

import org.scalatest.{FreeSpec, Matchers}

class IdMonadSpec extends FreeSpec with Matchers {
  import co.ioctl.chapter4.monad.id_monad.IdMonad

  "IdMonad" - {
    "should provide a pure method which should lift a plain value into the context of the monad" in {
      import cats.Id
      val idMonad: IdMonad[Int] = new IdMonad[Int]{}
      idMonad.pure(10) shouldBe a [Id[_]]
    }

    "should provide a flatMap operation" in {
      val idMonad: IdMonad[Int] = new IdMonad[Int]{}
      val a = idMonad.pure(1)
      idMonad.flatMap(a)(_ + 1) shouldBe 2
    }

    "should provide a map operation" in {
      val idMonad: IdMonad[Int] = new IdMonad[Int]{}
      val b = idMonad.pure(1)
      idMonad.map(b)(_ + 1) shouldBe 2
    }

    "should allow for-comprehensions to work" in {
      import cats.implicits._

      val idMonad: IdMonad[Int] = new IdMonad[Int]{}
      val a = idMonad.pure(3)
      val b = idMonad.flatMap(a)(_ + 1)

      val res = for {
        x <- a
        y <- b
      } yield x + y

      res shouldBe 7
    }
  }
}
