package co.ioctl.chapter4.monad.id_monad

trait IdMonad[A] {
  import cats.Id

  def pure(value: Id[A]): Id[A] = value

  def flatMap[B](value: A)(f: A ⇒ Id[B]): Id[B] = f(value)

  def map[B](value: A)(f: A ⇒ Id[B]): Id[B] = f(value)
}

object Test {
  import cats.implicits._

  def countPositives(x: List[Int]): Either[String, Int] = {
    x.foldLeft(0.asRight[String]) { (accumulator, num) ⇒
      if (num > 0) accumulator.map(_ + 1) else Left("Negative number")
    }
  }

  countPositives((1 to 10).toList) match {
    case Left(_) ⇒
    case Right(_) ⇒
  }
}