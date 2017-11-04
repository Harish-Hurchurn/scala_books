package co.ioctl.chapter1.json

import org.scalatest.{FreeSpec, Inside, Matchers}

class JsonSpec extends FreeSpec with Matchers with Inside {
  import co.ioctl.chapter1.json.JsonSyntax._
  import co.ioctl.chapter1.json.JsonWriterInstances._

  "Using the Json syntax" - {
    "it should be able to return a JsNumber" in {
      10.toJson shouldBe JsNumber(10)
    }

    "it should return a JsObject representing a Person" in {
      inside(Person("Harish", "Harish@someone.org").toJson) {
        case JsObject(value: Map[String, Json]) =>
          value.get("name") shouldBe Some(JsString("Harish"))
          value.get("email") shouldBe Some(JsString("Harish@someone.org"))
      }
    }

    "it should be able to print a string" in {
      "Hello World".toJson shouldBe JsString("Hello World")
    }
  }
}
