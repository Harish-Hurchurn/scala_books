package co.ioctl.chapter4.safe_foldright

object SafeFoldRight {
  import cats.Eval

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {

    def foldRightEval[Z, X](as: List[Z], acc: Eval[X])(fn: (Z, Eval[X]) => Eval[X]): Eval[X] =
      as match {
        case head :: tail => Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
        case Nil => acc
      }

    foldRightEval(as, Eval.now(acc)) { (a, b) => b.map(fn(a, _)) }.value
  }
}