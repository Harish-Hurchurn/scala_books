package co.ioctl.chapter3

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(x, xs), Cons(y, ys)) => Cons(f(x, y), zipWith(xs, ys)(f))
  }

  def addOne(list: List[Int]): List[Int] = {
    foldRight(list, List[Int]())((x, xs) => Cons(x + 1, xs))
  }

  def append[A](listA: List[A], listB: List[A]): List[A] = {
    foldRight(listA, listB)(Cons(_, _))
  }

  def concat[A](list: List[List[A]]): List[A] = {
    foldRight(list, List[A]())(append)
  }

  @annotation.tailrec
  def drop[A](list: List[A], n: Int): List[A] = {
    if (n <= 0) list
    else list match {
      case Nil => List()
      case Cons(_, xs) => drop(xs, n - 1)
    }
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = {
    def loop(y: List[A]): List[A] = {
      y match {
        case Cons(x, xs) if f(x) => loop(xs)
        case Nil => Nil
        case _ => l
      }
    }

    loop(l)
  }

  def filter[A](xs: List[A])(f: A => Boolean): List[A] = {
    foldRightAsFoldLeft(xs, List[A]()) {
      (x, xs) => if (f(x)) Cons(x, xs) else xs
    }
  }

  def filterViaFlatMap[A](xs: List[A])(f: A => Boolean): List[A] = {
    flatMap(xs) {
      x => if (f(x)) List(x) else Nil
    }
  }

  def flatMap[A, B](xs: List[A])(f: A => List[B]): List[B] = {
    foldRightAsFoldLeft(xs, List[B]()) {
      (x, xs) => append(f(x), xs)
    }
  }

  def foldLeft[A, B](list: List[A], z: B)(f: (B, A) => B): B = {
    list match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }
  }

  def foldLeftSum(ints: List[Int]): Int = {
    List.foldLeft(ints, 0)(_ + _)
  }

  def foldLeftProduct(ds: List[Double]) = {
    foldLeft(ds, 0.0)(_ * _)
  }

  def foldLeftLength[A](as: List[A]): Int = {
    foldLeft(as, 0)((acc, _) => acc + 1)
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  }

  def foldRightAsFoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    foldLeft(reverse(as), z)((b, a) => f(a, b))
  }

  def init[A](l: List[A]): List[A] = l match {
    case Cons(_, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
    case Nil => sys.error("init of empty list")
  }

  def length[A](as: List[A]): Int = {
    foldRight(as, 0) {
      (_, acc) => acc + 1
    }
  }

  def listToString(list: List[Double]): List[String] = {
    foldRight(list, List[String]())((x, xs) => Cons(x.toString, xs))
  }

  def map[A, B](xs: List[A])(f: A => B): List[B] = {
    foldRightAsFoldLeft(xs, List[B]()) {
      (x, xs) => Cons(f(x), xs)
    }
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def reverse[A](list: List[A]): List[A] = {
    foldLeft(list, List[A]())((acc, h) => Cons(h, acc))
  }

  def setHead[A](elem: A, list: List[A]): List[A] = list match {
    case Nil => List()
    case Cons(_, xs) => Cons(elem, xs)
  }

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def tail[A](list: List[A]): List[A] = list match {
    case Nil => List()
    case Cons(_, xs) => xs
  }
}

