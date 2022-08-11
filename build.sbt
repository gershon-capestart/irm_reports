ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "irm_scala"
  )

libraryDependencies += "com.microsoft.sqlserver" % "mssql-jdbc" % "11.2.0.jre8"
libraryDependencies += "org.apache.commons" % "commons-dbcp2" % "2.9.0"
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.10"
libraryDependencies += "com.sendgrid" % "sendgrid-java" % "4.9.3"

