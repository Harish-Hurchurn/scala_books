package co.ioctl.chapter4.safe_foldright

import org.scalatest.{FreeSpec, Matchers}

class SafeFoldRightSpec extends FreeSpec with Matchers {
  "SafeFoldRight should pass" in {
    SafeFoldRight.foldRight((1 to 100000).toList, 0)(_ + _)
  }
}
