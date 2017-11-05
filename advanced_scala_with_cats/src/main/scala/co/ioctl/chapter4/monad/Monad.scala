package co.ioctl.chapter4.monad

import scala.language.higherKinds

trait Monad[F[_]] {
  def pure[A](value: A): F[A]

  def flatMap[A, B](value: A)(f: A ⇒ F[B]): F[B]

  def map[A, B](value: A)(f: A ⇒ B): F[B] =
    flatMap(value)(x ⇒ pure(f(x)))
}