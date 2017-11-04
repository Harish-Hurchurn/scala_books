package co.ioctl.chapter3

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def depth[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(n: Int) => 1
      case Branch(left, right) => 1 + depth(left) max depth(right)
    }
  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
    tree match {
      case Leaf(n) => Leaf(f(n))
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
    }
  }

  def maximum[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(n: Int) => n
      case Branch(left, right) => maximum(left) max maximum(right)
    }
  }

  def sum[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(n: Int) => 1
      case Branch(left, right) => sum(left) + sum(right)
    }
  }
}
