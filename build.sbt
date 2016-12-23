lazy val root = (project in file(".")).
  settings(
    name := "Clip",
    version := "1.1.1",
    scalaVersion := "2.11.4",
    mainClass in Compile := Some("Clip")        
  )

libraryDependencies ++= Seq(
  // "org.apache.spark" %% "spark-core" % "1.2.0" % "provided",
  // "org.apache.spark" %% "spark-streaming" % "1.2.0" % "provided",
  // "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.2.0"
)

// META-INF discarding

// mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
//    {
//     case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//     case x => MergeStrategy.first
//    }
// }
