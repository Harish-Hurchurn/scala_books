package co.ioctl.chapter5

import org.scalatest.{FlatSpec, Matchers}

class ChapterFiveSpec extends FlatSpec with Matchers {
  behavior of "drop"
  it should "return Stream(2,3) when called with drop(1)" in {
    val x = Stream(1, 2, 3)
    val res = x.drop(1)
    res.toList shouldBe List(2, 3)
  }

  it should "return Stream() when drop is called on Stream(1,2,3) and drop(3)" in {
    val x = Stream(1, 2, 3)
    val res = x.drop(3)
    res.toList shouldBe List()
  }

  behavior of "fibs"
  it should "return Stream(0, 1, 1, 2, 3, 5, 8, 13) when fibs.take(5).toList" in {
    val x = Stream.fibs.take(8).toList shouldBe List(0, 1, 1, 2, 3, 5, 8, 13)
  }

  behavior of "from"
  it should "return Stream(1,2,3,4,5,6,7,8,9,10) when called with from(10)" in {
    val x = Stream(1)
    val res = x.from(10)
    res.toList shouldBe List(1,2,3,4,5,6,7,8,9,10)
  }

  behavior of "forAll"
  it should "return true when called on a Stream containing 2, 6, 6, 8 as these numbers are even" in {
    val x = Stream(2, 4, 6, 8).forAll(_ % 2 == 0)
    x === true
  }

  it should "return true when called on a Stream containing 1, 3, 5, 7 as these numebrs are odd" in {
    val x = Stream(1, 3, 5, 7).forAll(_ % 2 != 0)
    x === true
  }

  behavior of "headOption"
  it should "return Some(1) when called on Stream(1,2,3)" in {
    Stream(1,2,3).headOption shouldBe Some(1)
  }

  behavior of "headOptionViaFoldRight"
  it should "return Some(1) when called on Stream(1,2,3)" in {
    val x = Stream(1,2,3).headOptionViaFoldRight shouldBe Some(1)
  }

  behavior of "take"
  it should "return Stream(1) when called with take(1)" in {
    Stream(1, 2, 3).take(1).toList shouldBe List(1)
  }

  it should "return Stream(1, 2) when called with take(2)" in {
    Stream(1, 2, 3).take(2).toList shouldBe List(1,2)
  }

  behavior of "takeWhile"
  it should "return Stream(1,2,3) when called with takeWhile(_ < 3)" in {
    val x = Stream(1, 2, 3, 4)
    val res = x.takeWhile(_ < 4)
    res.toList shouldBe List(1, 2, 3)
  }

  behavior of "takeWhileViaFoldRight"
  it should "return Stream(1,2,3) when called with takeWhile(_ < 3)" in {
    val x = Stream(1, 2, 3, 4)
    val res = x.takeWhileViaFoldRight(_ < 4)
    res.toList shouldBe List(1, 2, 3)
  }

  behavior of "toList"
  it should "return List() for an empty stream" in {
    val x = Stream()
    x.toList shouldBe List()
  }

  it should "return a List from a Stream(1, 2, 3)" in {
    val x = Stream(1, 2, 3)
    x.toList shouldBe List(1, 2, 3)
  }

  it should "not return List(1,2,3) for a Stream(1,2,3,4,5,6,7,8,9)" in {
    val x = Stream(1, 2, 3, 4, 5, 6, 7, 8, 9)
    x.toList shouldNot be(List(1, 2, 3))
  }

  it should "not return List(1,2,3,4,5,6,7,8,9) for a Stream(1,2,3)" in {
    val x = Stream(1, 2, 3)
    x.toList shouldNot be(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
  }
}
