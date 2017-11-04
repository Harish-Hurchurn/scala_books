package co.ioctl.chapter1.printable

object PrintableInstances {
  implicit val intPrintable: Printable[Int] = (value: Int) => value.toString
  implicit val stringPrintable: Printable[String] = (value: String) => value
}
