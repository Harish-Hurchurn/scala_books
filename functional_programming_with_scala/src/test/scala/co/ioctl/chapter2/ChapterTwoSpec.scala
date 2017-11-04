package co.ioctl.chapter2

import org.scalatest.{FlatSpec, Matchers}

class ChapterTwoSpec extends FlatSpec with Matchers {
  behavior of "ChapterTwo.factorial"
  "with a value of 0" should "return a value of 1" in {
    ChapterTwo.factorial(0) should be(1)
  }

  "Calling ChapterTwo.factorial with a value of 7" should "return a value of 5040" in {
    ChapterTwo.factorial(7) should be (5040)
  }

  behavior of "ChapterTwo.fib"
  "Calling ChapterTwo.fib with a value of 5" should "return 5" in {
    ChapterTwo.fib(5) should be(5)
  }

  "Calling ChapterTwo.fib with a value of 6" should "return 8" in {
    ChapterTwo.fib(6) should be(8)
  }

  behavior of "ChapterTwo.isSorted"
  "Calling ChapterTwo.isSorted on an array with values 1,2,3,4,5,6" should "should true" in {
    val x = (1 to 6).toArray
    ChapterTwo.isSorted[Int](x, (i, j) => i < j)
  }

  "Calling ChapterTwo.isSorted on an array with values 6,5,4,3,2,1" should "should true" in {
    val x = (1 to 6).toArray
    ChapterTwo.isSorted[Int](x, (i, j) => i > j)
  }

  "Calling ChapterTwo.isSorted on an array with values 2, 4, 6, 8, 10, 12" should "should true" in {
    val x = (1 to 6 by 2 ).toArray
    ChapterTwo.isSorted[Int](x, (i, j) => i % j == 0)
  }

  behavior of "ChapterTwo.curry"
  "Calling ChapterTwo.curry" should "should produce a curried function" in {
    def temp(a: Int, b: Int) = a + b
    val curried = ChapterTwo.curry(temp)
    curried(10)(10) should be(20)
  }

  behavior of "ChapterTwo.uncurry"
  "Calling ChapterTwo.uncurry" should "should produce a uncurried function" in {
    def temp(a: Int, b: Int) = a + b
    val curried = ChapterTwo.curry(temp)
    val uncurried = ChapterTwo.uncurry(curried)
    uncurried(10, 10) should be(20)
  }

  behavior of "ChapterTwo.compose"
  "Calling ChapterTwo.compose" should "should produce a composed function" in {
    val a = (x: Int) => x + 10
    val b = (y: Int) => y + 10

    val x = ChapterTwo.compose(a, b)

    x(10) should be(30)
  }
}
