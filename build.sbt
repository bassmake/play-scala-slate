name := """play-scala-slate"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.jayway.restassured" % "rest-assured" % "2.9.0" % Test,
  "org.springframework.restdocs" % "spring-restdocs-restassured" % "1.1.0.RC1" % Test
)

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "spring-snapshots" at "https://repo.spring.io/libs-snapshot"
)


// need to add `bundle install` and `bundle exec middleman build` here
// documentation now available on http://localhost:9000/assets/docs/index.html