name := "scala_books"

lazy val scalaBooks = (project in file("."))
  .settings(Common.settings: _*)
  .aggregate(advancedScalaWithCats)
  .aggregate(functionalProgrammingWithScala)

lazy val advancedScalaWithCats = (project in file("advanced_scala_with_cats"))
  .settings(libraryDependencies ++= Common.dependencies)

lazy val functionalProgrammingWithScala = (project in file("functional_programming_with_scala"))
  .settings(libraryDependencies ++= Common.dependencies)

lazy val functionalProgrammingSimplified = (project in file("functional_programming_simplified"))
  .settings(libraryDependencies ++= Common.dependencies)
