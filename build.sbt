name := "vert.io"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "io.vertx" %% "vertx-lang-scala" % "3.5.1",
  "io.vertx" %% "vertx-mongo-client-scala" % "3.5.1",
  "io.vertx" %% "vertx-web-scala" % "3.5.1"
)
        