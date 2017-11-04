package co.ioctl.chapter4

import scala.{Option ⇒ _}

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = {
    this match {
      case None => None
      case Some(x) => Some(f(x))
    }
  }

  def flatMap[B](f: A => Option[B]): Option[B] = {
    this match {
      case None => None
      case Some(x) => f(x)
    }
  }

  def getOrElse[B >: A](default: => B): B = {
    this match {
      case None => default
      case Some(x) => x
    }
  }

  def filter(f: A => Boolean): Option[A] = {
    this match {
      case Some(x) if f(x) => this
      case None => None
    }
  }

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  def orElse[B >: A](ob: => Option[B]): Option[B] = {
    this match {
      case None => None
      case Some(_) => this
    }
  }

  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs) flatMap ((m: Double) => mean(xs.map(x => math.pow(x - m, 2))))
}

object Option {
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    for {
      aa <- a
      bb <- b
    } yield f(aa, bb)
  }

  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    def loop(list: List[Option[A]], subList: List[A]): Option[List[A]] = {
      list match {
        case None :: _ => None
        case Nil => Some(subList)
        case Some(x) :: xs => loop(xs, x :: subList)
      }
    }

    loop(a, Nil).map(_.reverse)
  }

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    a match {
      case Nil => Some(Nil)
      case x :: xs => map2(f(x), traverse(xs)(f))(_ :: _)
    }
  }

  def sequenceViaTraverse[A](a: List[Option[A]]): Option[List[A]] =
    traverse(a)(i => i)
}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]
