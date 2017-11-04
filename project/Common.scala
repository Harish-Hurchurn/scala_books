import sbt.Keys._
import sbt._

object Common {
  val dependencies: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % Versions.scalatest % "test"
  )

  val settings: Seq[Def.Setting[_]] = Seq(
  organization := "IOCTL Solutions",
  scalacOptions ++= Seq(
    "-unchecked",
    "-deprecation",
    "-language:_",
    "-encoding", "UTF-8"
  ))
  scalaVersion := "2.12.3"
}

object Versions {
  val scalatest = "3.0.1"
}
