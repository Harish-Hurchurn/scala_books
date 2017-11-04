name := "scala_books"

lazy val scalaBooks = (project in file("."))
  .settings(Common.settings: _*)
  .aggregate(functionalProgrammingWithScala)

lazy val functionalProgrammingWithScala = (project in file("functional_programming_with_scala"))
  .settings(libraryDependencies ++= Common.dependencies)
