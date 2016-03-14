import java.time.Instant

import com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyKeys._
import scoverage.ScoverageKeys._

object Build
  extends Build with LibraryDependencyVersions {

  lazy val commonSettings =
    Seq(
      test in assembly := {},
      version := sys.env.getOrElse(
        "SVC_VERSION",
        s"0.1.${Instant.now.getEpochSecond}"
      ),
      coverageEnabled in test := true,
      coverageEnabled in IntegrationTest := true,
      coverageMinimum := 90,
      coverageFailOnMinimum := true,
      updateOptions := updateOptions
        .value
        .withCachedResolution(true),
      parallelExecution in IntegrationTest := false,
      organization := "com.urlable",
      scalaVersion := "2.11.7",
      scalacOptions := Seq(
        "-unchecked",
        "-deprecation",
        "-encoding",
        "utf8"
      ),
      libraryDependencies ++=
        Seq(
          "com.urlable" %% "short-url-svc-scala-sdk" % "0.1.0+1457933137",
          "org.testobjects" %% "test-objects-for-scala" % "0.1.4" % "test,it",
          "org.scalatest" %% "scalatest" % "2.2.5" % "test,it",
          "org.mockito" % "mockito-core" % "1.10.19" % "test,it",
          "com.iheart" %% "ficus" % ficusVersion % "test,it",
          "com.softwaremill.macwire" %% "macros" % "2.2.2" % "provided"
        ),
      resolvers += Resolver.bintrayRepo(
        "urlable",
        "maven"
      )
    ) ++
      Defaults
        .itSettings

  lazy val root =
    Project(
      id = "root",
      base = file(".")
    )
      .aggregate(
        server,
        restApi
      )
      .settings(
        commonSettings
      )
      .configs(IntegrationTest)
      .dependsOn(
        server,
        restApi
      )
      .enablePlugins(JavaServerAppPackaging)

  lazy val server =
    Project(
      id = "server",
      base = file("server")
    )
      .settings(
        commonSettings ++
          Seq(
            libraryDependencies ++= Seq(
              "com.iheart" %% "ficus" % ficusVersion
            )
          ): _*
      )
      .configs(IntegrationTest)
      .dependsOn(
        restApi % "test->test;it->test;compile"
      )

  lazy val restApi =
    Project(
      id = "rest-api",
      base = file("rest-api")
    )
      .settings(
        commonSettings ++
          Seq(
            libraryDependencies ++= Seq(
              "ch.qos.logback" % "logback-classic" % logbackClassicVersion
            )
          ): _*
      )
      .configs(IntegrationTest)

}
