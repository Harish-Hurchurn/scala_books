package co.ioctl.chapter1.printable

trait Printable[A] {
  def printableFormat(value: A): String
}
