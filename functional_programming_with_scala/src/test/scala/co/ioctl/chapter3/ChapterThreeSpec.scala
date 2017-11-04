package co.ioctl.chapter3

import org.scalatest.{FlatSpec, Matchers}

class ChapterThreeSpec extends FlatSpec with Matchers {
  behavior of "List.addOne"
  it should "return List(2, 3, 4, 5, 6, 7) when passed List(1,2,3,4,5,6)" in {
    List.addOne(List(1,2,3,4,5,6)) shouldBe List(2,3,4,5,6,7)
  }

  behavior of "List.append"
  it should "return List(1,2,3,4,5,6) when passed List(1,2,3) and List(4,5,6)" in {
    List.append(List(1,2,3), List(4,5,6)) shouldBe List(1,2,3,4,5,6)
  }

  it should "return List(1,2,3) when passed in List(1,2,3) and List()" in {
    List.append(List(1, 2, 3), List()) shouldBe List(1,2,3)
  }

  it should "return List(4,5,6) when passed in List() and List(4,5,6)" in {
    List.append(List(), List(4, 5, 6)) shouldBe List(4,5,6)
  }

  behavior of "List.concat"
  it should "return List(1,2,3,4,5,6) when passed in List(List(1,2,3,4,5,6)" in {
    List.concat(List(List(1,2,3,4,5,6))) shouldBe List(1,2,3,4,5,6)
  }

  behavior of "List.drop"
  it should "return an empty list when called on List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)" in {
    val x = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    List.drop(x, 10) shouldBe List()
  }

  it should "return List(3, 4, 5) when called on List(1, 2, 3, 4, 5)" in {
    val x = List(1, 2, 3, 4, 5)
    List.drop(x, 2) shouldBe List(3, 4, 5)
  }

  behavior of "List.dropWhile"
  it should "return List(4,5) when called with x < 4" in {
    val x = List(1, 2, 3, 4, 5)
    List.dropWhile(x)(_ < 4)
  }

  it should "return List() when called on List(2, 4, 6, 8, 10)" in {
    val x = List(2, 4, 6, 8, 10)
    List.dropWhile(x)(_ % 2 == 0) shouldBe List()
  }

  behavior of "List.filter"
  it should "return List(2,4,6) when called on List.filter(List(1,2,3,4,5,6,7))(_ % 2 == 0)" in {
    List.filter(List(1,2,3,4,5,6,7))(_ % 2 == 0) shouldBe List(2,4,6)
  }

  behavior of "List.filterViaFlatMap"
  it should "return List(2,4,6) when called on List.filter(List(1,2,3,4,5,6,7))(_ % 2 == 0)" in {
    List.filterViaFlatMap(List(1,2,3,4,5,6,7))(_ % 2 == 0) shouldBe List(2,4,6)
  }

  behavior of "List.flatMap"
  it should "return List(1, 1, 2, ,2, 3, 3, 4, 4, 5, 5) when called with List.flatMap(List(1,2,3,4,5)(i => List(i, i)" in {
    List.flatMap(List(1,2,3,4,5))(i => List(i, i)) shouldBe List(1,1,2,2,3,3,4,4,5,5)
  }

  behavior of "List.foldLeft"
  it should "return 10 for List.foldLeft(List(1,2,3,4), 0)(_ + )" in {
    List.foldLeft(List(1,2,3,4), 0)(_ + _) shouldBe 10
  }

  behavior of "List.init"
  it should "return List(1,2,3) when called on List(1,2,3,4)" in {
    val x = List(1, 2, 3, 4)
    List.init(x) shouldBe List(1, 2, 3)
  }

  behavior of "List.length"
  it should "return 0 as length of List()" in {
    List.length(List()) shouldBe 0
  }

  it should "return 4 as length of List(1,2,3,4)" in {
    List.length(List(1,2,3,4)) shouldBe 4
  }

  behavior of "List.listToString"
  it should "return List(\"1.0\", \"2.0\") when called with List(1.0, 2.0)" in {
    List.listToString(List(1.0, 2.0)) shouldBe List("1.0", "2.0")
  }

  behavior of "List.map"
  it should "return List(2,3,4) when called with List.map(List(1,2,3)(_ + 1)" in {
    List.map(List(1,2,3))(_ + 1) shouldBe List(2,3,4)
  }

  behavior of "List.setHead"
  it should "not modify the original list with the original list being List(1,2,3,4,5)" in {
    val x = List(1, 2, 3, 4)
    val y = List.setHead(5, x)
    x shouldBe List(1, 2, 3, 4)
    y shouldBe List(5, 2, 3, 4)
  }

  it should "return List(1, 3 ,4, 5) when called on a list containing List(2, 3, 4, 5)" in {
    val x = List(2, 3, 4, 5)
    List.setHead(1, x) shouldBe List(1, 3, 4, 5)
  }

  behavior of "List.tail"
  it should "return List(2,3,4,5) when called with List(1,2,3,4,5)" in {
    val x = List(1, 2, 3, 4, 5)
    List.tail(x) shouldBe List(2, 3, 4, 5)
  }

  it should "return List() when called with List.tail(Nil)" in {
    List.tail(Nil) shouldBe List()
  }
}
