name := "advanced_scala_with_cats"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.0-RC1"
)

scalacOptions += "-Ypartial-unification"
