// Basic project information
name          := "TestDL"

version       := "1.0"

organization  := "com.understandling"

scalaVersion  := "2.11.8"

// Add multiple dependencies
libraryDependencies ++= Seq(
    "org.deeplearning4j" % "deeplearning4j-core" % "0.7.2",
    "org.deeplearning4j" % "deeplearning4j-nlp" % "0.7.2",
    "org.nd4j" % "nd4j-native-platform" % "0.7.2",
    "org.nd4j" % "nd4j-native" % "0.7.2" classifier "windows-x86_64",
	//"org.nd4j" % "nd4j-cuda-7.5-platform" % "0.7.2",
    //"org.nd4j" % "nd4j-cuda-7.5" % "0.7.2",
	"org.slf4j" % "slf4j-api" % "1.7.22",
	"org.slf4j" % "slf4j-simple" % "1.7.22"
)