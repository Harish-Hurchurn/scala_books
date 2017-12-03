name := "scala_books"

lazy val scalaBooks = (project in file("."))
  .settings(Common.settings: _*)
  .aggregate(catsWithScala)
  .aggregate(functionalProgrammingWithScala)

lazy val catsWithScala = (project in file("cats_with_scala"))
  .settings(libraryDependencies ++= Common.dependencies)

lazy val functionalProgrammingWithScala = (project in file("functional_programming_with_scala"))
  .settings(libraryDependencies ++= Common.dependencies)
