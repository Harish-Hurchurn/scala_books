package co.ioctl.chapter1.printable

object PrintableSyntax {
  implicit class PrintOps[A](value: A) {
    def Format(implicit p: Printable[A]): String = p.printableFormat(value)
    def print(implicit p: Printable[A]): Unit = println(p.printableFormat(value))
  }
}
