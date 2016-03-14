
libraryDependencies ++= {
  val akkaStreamAndHttpVersion = "2.4.2"
  Seq(
    "com.typesafe.akka" %% "akka-stream" % akkaStreamAndHttpVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaStreamAndHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaStreamAndHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamAndHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit-experimental" % "2.4.2-RC3" % "test,it",
    "com.typesafe.akka" %% "akka-slf4j" % akkaStreamAndHttpVersion
  )
}