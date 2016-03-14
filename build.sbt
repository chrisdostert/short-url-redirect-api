import java.io.PrintWriter

packageName := "short-url-svc"

lazy val generateVersionFile =
  taskKey[Unit]("Writes the version of the current build to target/VERSION")

generateVersionFile := {
  val writer = new PrintWriter(new File("target/VERSION"))
  writer.write(version.value)
  writer.close()
}

enablePlugins(DockerPlugin)

dockerBaseImage := "java:8u72-jre"
dockerExposedPorts += 8080

// assembly plugin
mainClass in Compile := Some("com.chrisdostert.shorturlredirectapi.server.Server")
