package co.ioctl.chapter3.contravariant_functor

trait ContraVariantFunctor[A] {
  def contramap[B](func: B ⇒ A): ContraVariantFunctor[B] =
    (value: B) => format(func(value))

  def format(value: A): String
}