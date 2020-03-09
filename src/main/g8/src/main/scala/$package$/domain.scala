package $package$

import io.circe._, io.circe.generic.semiauto._, io.circe.generic.auto._
import java.time.Instant
import java.time.ZonedDateTime

// TODO Insert case classes for decoding JSON
// Get them from https://transform.tools/json-to-scala-case-class
case class Echo (
  args: Map[String, String],
  headers: Map[String, String],
  origin: String,
  url: String
)

