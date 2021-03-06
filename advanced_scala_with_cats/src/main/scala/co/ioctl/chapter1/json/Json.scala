package co.ioctl.chapter1.json

sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsNumber(get: Double) extends Json
final case class JsString(get: String) extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}
