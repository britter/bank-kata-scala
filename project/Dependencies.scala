import sbt._

object Version {
  final val Scala     = "2.11.8"
  final val ScalaTest = "3.0.0"
  final val ScalaMock = "3.3.0"
}

object Library {
  val scalaTest = "org.scalatest" %% "scalatest" % Version.ScalaTest
  val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % Version.ScalaMock
}
