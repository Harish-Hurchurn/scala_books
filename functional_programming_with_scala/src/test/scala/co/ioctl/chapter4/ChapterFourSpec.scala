package co.ioctl.chapter4

import org.scalatest.{FlatSpec, Matchers}

class ChapterFourSpec extends FlatSpec with Matchers {
  behavior of "Option.map"
  it should "Return None when the Option contains a None" in {
    val x = None
    x map(i => i) shouldBe None
  }

  it should "Return Some(10) when the Option contains 10" in {
    val x = Some(10)
    x map(i => i) shouldBe Some(10)
  }

  behavior of "Option.flatMap"
  it should "Return None when passed in a None" in {
    None flatMap(i => Some(10)) shouldBe None
  }

  it should "Return Some(11) when passed Some(10)" in {
    Some(10) flatMap(i => Some(i + 1))
  }

  behavior of "Option.getOrElse"
  it should "Return the default value when the option is None" in {
    None getOrElse "None" shouldBe "None"
  }

  it should "Return the non-default value when it is not a None" in {
    Some(10) getOrElse "None" shouldBe 10
  }

  behavior of "Option.filter"
  it should "Return the value if the filter evaluates to true" in {
    Some(10) filter (i => i % 2 == 0) shouldBe Some(10)
  }

  behavior of "Option.map2"
  def testing(a: Int, b: Int) = a + b

  it should "Return None when the first argument is None" in {
    Option.map2(None, Some(10))(testing) shouldBe None
  }

  it should "Return None when the second argument is None" in {
    Option.map2(Some(10), None)(testing) shouldBe None
  }

  it should "Return None when both arguments are None" in {
    Option.map2(None, None)(testing) shouldBe None
  }
  it should "Return Some(20) when neither argument is None" in {
    Option.map2(Some(10), Some(10))(testing) shouldBe Some(20)
  }

  behavior of "Option.sequence"
  it should "Return Option[List(Some(1), Some(2), Some(3) when passed in List(Some(1), Some(2), Some(3)" in {
    Option.sequence(List(Some(1), Some(2), Some(3))) shouldBe Some(List(1, 2, 3))
  }

  it should "Return None when a None is in the List" in {
    Option.sequence(List(None, Some(1))) shouldBe None
  }
}
