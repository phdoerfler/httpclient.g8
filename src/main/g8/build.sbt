ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0"
ThisBuild / organization     := "io.doerfler"
ThisBuild / organizationName := "Philipp Dörfler"
ThisBuild / crossPaths       := false
ThisBuild / turbo            := true

scalacOptions := Seq("-unchecked", "-deprecation", "-language:_", "-encoding", "UTF-8", "-target:jvm-1.8")

lazy val root = (project in file(".")).
  settings(
    name := "$name$",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core"                 % "$sttp_version$",
      "com.softwaremill.sttp.client" %% "circe"                % "$sttp_version$",
      "io.circe"                     %% "circe-generic"        % "$circe_version$",
      "io.circe"                     %% "circe-generic-extras" % "$circe_extras_version$",
      "org.scala-lang.modules"       %% "scala-parallel-collections" % "$parallel_collections_version$",
      "com.github.pathikrit"         %% "better-files"               % "$better_files_version$",
      "com.github.scopt"             %% "scopt"                      % "$scopt_version$",
      "org.fusesource.jansi"          % "jansi"       % "$jansi_version$",
      "jline"                         % "jline"       % "$jline_version$",
      "com.github.nscala-time"       %% "nscala-time" % "$nscala_time_version$"
    )
  ).
  settings(specs2Settings).
  settings(graalVmSettings).
  enablePlugins(JavaAppPackaging, GraalVMNativeImagePlugin, BuildInfoPlugin)

lazy val buildInfoSettings: Seq[Setting[_]] = Seq(
  buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, executableScriptName),
  buildInfoPackage := "$package$"
)

// Refine scalac params from tpolecat
scalacOptions --= Seq(
  "-Xfatal-warnings"
)

def randomDataGenerator(magnolia: Boolean) = {
  val p = if (magnolia) "-magnolia" else ""
  "com.danielasfregola" %% f"random-data-generator\$p%s" % "2.6"
}

def specs2(version: String, features: Seq[String]) =
  features.map(fe => "org.specs2" %% f"specs2-\$fe%s" % version % "test")

lazy val specs2Settings = Seq(
  libraryDependencies ++= specs2("4.7.0", Seq("core", "html", "scalacheck")),
  testOptions in Test += Tests.Argument("console"),
  testOptions in Test += Tests.Argument("html"),
  scalacOptions in Test += "-Yrangepos"
)

lazy val graalVmSettings: Seq[Setting[_]] = List(
  graalVMNativeImageOptions ++= Seq(
    "-H:+ReportExceptionStackTraces",
    "-H:+TraceClassInitialization",
    "--no-fallback",
    "--verbose",
    "--report-unsupported-elements-at-runtime",
    "--allow-incomplete-classpath",
    "--initialize-at-build-time")
)