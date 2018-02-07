
lazy val concurrency = project
  .settings(
    name := "concurrency",
    version := "0.0.1",
    scalaVersion := "2.12.4"
  )

lazy val rootProject = (project in file("."))
  .aggregate(concurrency)