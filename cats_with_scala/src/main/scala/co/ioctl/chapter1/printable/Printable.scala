package co.ioctl.chapter1.printable

trait Printable[A] {
  def Format(value: A): String
}
