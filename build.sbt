name := "sitemap-generator"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "com.konghq" % "unirest-java" % "3.1.03" classifier "standalone"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"

enablePlugins(JavaAppPackaging)
