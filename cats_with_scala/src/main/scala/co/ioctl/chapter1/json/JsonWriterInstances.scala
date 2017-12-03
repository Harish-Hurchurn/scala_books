package co.ioctl.chapter1.json

case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val numberWriter: JsonWriter[Int] =
    (value: Int) => JsNumber(value)

  implicit val personWriter: JsonWriter[Person] =
    (value: Person) => JsObject(Map(
      "name" -> JsString(value.name),
      "email" -> JsString(value.email))
    )

  implicit val stringWriter: JsonWriter[String] = 
    (value: String) => JsString(value)

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    (value: Option[A]) ⇒ value match {
      case Some(x) ⇒ writer.write(x)
      case None ⇒ JsNull
    }

}
