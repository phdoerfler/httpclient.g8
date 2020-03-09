package $package$

import sttp.client._
import sttp.client.circe._
import sttp.model.Uri
import io.circe._, io.circe.generic.semiauto._, io.circe.generic.auto._

object Main extends App with VerboseDecodingFailures {
  implicit val backend = HttpURLConnectionBackend()

  val res = basicRequest.get(uri"https://httpbin.org/get?foo=bar").response(asJsonUnsafeVerbose[Echo]).send().body
  println(res)
  backend.close()
}
