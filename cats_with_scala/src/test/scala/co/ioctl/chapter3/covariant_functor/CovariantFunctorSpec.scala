package co.ioctl.chapter3.covariant_functor

import org.scalatest.{FreeSpec, Matchers}

class CovariantFunctorSpec extends FreeSpec with Matchers {
  "The Tree functor" - {
    "should be able to apply the map operation to branches and leaf nodes of a binary tree" in {
      import cats.Functor
      import cats.implicits._

      implicit val binaryTreeFunctor = new Functor[Tree] {
        override def map[A, B](fa: Tree[A])(f: A ⇒ B): Tree[B] = fa match {
          case Branch(left, right) ⇒ Branch(map(left)(f), map(right)(f))
          case Leaf(leafValue) ⇒ Leaf(f(leafValue))
        }
      }

      val x: Tree[Int] = Branch(Leaf(10): Tree[Int], Leaf(10): Tree[Int])
      x.map(_ * 2) shouldBe Branch(Leaf(20), Leaf(20))
    }
  }
}