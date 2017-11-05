package co.ioctl.chapter4.Monad.IdMonad

trait IdMonad[A] {
  import cats.Id

  def pure(value: Id[A]): Id[A] = value

  def flatMap[B](value: A)(f: A ⇒ Id[B]): Id[B] = f(value)

  def map[B](value: A)(f: A ⇒ Id[B]): Id[B] = f(value)
}