package co.ioctl.chapter1.json

case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val numberWriter = new JsonWriter[Int] {
    override def write(value: Int): Json = JsNumber(value)
  }

  implicit val personWriter = new JsonWriter[Person] {
    override def write(value: Person): Json = JsObject(Map(
      "name" -> JsString(value.name),
      "email" -> JsString(value.email)
    ))
  }

  implicit val stringWriter = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }
}
