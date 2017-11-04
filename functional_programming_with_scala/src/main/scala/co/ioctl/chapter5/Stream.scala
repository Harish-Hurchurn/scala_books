package co.ioctl.chapter5

import co.ioctl.chapter5.Stream._

import scala.{Stream ⇒ _}
import scala.collection.mutable.ListBuffer

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

sealed trait Stream[+A] {
  def append[B >: A](s: => Stream[B]): Stream[B] =
    foldRight(s)((h, t) => cons(h, t))

  def drop(n: Int): Stream[A] = {
    @annotation.tailrec
    def loop(s: Stream[A], i: Int): Stream[A] = {
      if (i <= 0) s
      else s match {
        case Cons(h, t) => loop(t(), i - 1)
        case _ => empty
      }
    }

    loop(this, n)
  }

  def exists(p: A => Boolean): Boolean = {
    this match {
      case Cons(h, t) => p(h()) || t().exists(p)
      case _ => false
    }
  }

  def from(n: Int): Stream[Int] = {
    cons(n, from(n + 1))
  }

  def filter(p: A => Boolean): Stream[A] = {
    foldRight(empty[A])((h, t) => if (p(h)) cons(h, t) else t)
  }

  def flatMap[B](f: A => Stream[B]): Stream[B] =
    foldRight(empty[B])((h, t) => f(h) append t)

  def forAll(p: A => Boolean): Boolean = {
    foldRight(false)((a, b) => p(a) && b)
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = {
    this match {
      case Cons(h, t) => f(h(), t().foldRight(z)(f))
      case _ => z
    }
  }

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  def headOptionViaFoldRight: Option[A] = {
    foldRight(None: Option[A])((h, t) => Some(h))
  }

  def map[B](f: A => B): Stream[B] = {
    foldRight(empty[B])((h, t) => cons(f(h), t))
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  def takeWhile(p: A => Boolean): Stream[A] = {
    this match {
      case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
      case _ => empty
    }
  }

  def takeWhileViaFoldRight(p: A => Boolean): Stream[A] = {
    foldRight(empty[A])((h, t) => if (p(h)) cons(h, t) else empty)
  }

  def toList: List[A] = {
    val buffer = new ListBuffer[A]

    @annotation.tailrec
    def loop(s: Stream[A]): List[A] = s match {
      case Cons(h, t) => buffer += h(); loop(t())
      case _ => buffer.toList
    }

    loop(this)
  }
}

object Stream {
  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def constant[A](a: A): Stream[A] = {
    lazy val tail: Stream[A] = cons(a, tail)
    tail
  }

  def empty[A]: Stream[A] = Empty

  def fibs: Stream[Int] = {
    def loop(i: Int, j: Int): Stream[Int] = {
      cons(i, loop(j, i + j))
    }

    loop(0, 1)
  }

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
    f(z) match {
      case Some((a, s)) => cons(a, unfold(s)(f))
      case None => empty
    }
  }

  def fibsViaUnfold = {
    unfold((0, 1)) {
      case ((a, b)) => Some((a, (b, a + b)))
    }
  }

  def fromViaUnfold(i: Int) = {
    unfold(i) {
      case n => Some(n, n + 1)
    }
  }

  def constantViaUnfold[A](i: A) = {
    unfold(i) {
      case a => Some(a, a)
    }
  }

  def onesViaUnfold: Stream[Int] = {
    unfold(1)(_ => Some((1, 1)))
  }
}
