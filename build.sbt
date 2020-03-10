// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name := "HttpApiClient",
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    scriptedBufferLog := false,
    scriptedLaunchOpts ++= List("-Xms256m", "-Xmx256m", "-XX:ReservedCodeCacheSize=128m", "-Dfile.encoding=UTF-8"),
    resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
  )
