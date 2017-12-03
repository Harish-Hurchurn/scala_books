package co.ioctl.chapter3.invariant_functor

trait InvariantFunctor[A] {
  self ⇒
  def decode(value: String): Option[A]
  def encode(value: A): String
  def imap[B](dec: A => B, enc: B => A): InvariantFunctor[B] = new InvariantFunctor[B] {
    override def decode(value: String): Option[B] = self.decode(value).map(dec)
    override def encode(value: B): String = self.encode(enc(value))
  }
}




