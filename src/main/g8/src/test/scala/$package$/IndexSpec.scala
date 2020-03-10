package $package$

import sttp.client._
import sttp.client.circe._
import sttp.model.Uri
import io.circe._, io.circe.generic.semiauto._, io.circe.generic.auto._
import org.specs2._
import org.specs2.specification._
import org.specs2.execute._
import TypeAliases._

class IndexSpec extends Specification with BackendForTesting with VerboseDecodingFailures { def is =
s2"""
# $name$

The httpbin API provides endpoints for
  - testing GET \$e1
"""

  def e1 = { (b: Backend) =>
    implicit val back = b
    val res = basicRequest.get(uri"https://httpbin.org/get?foo=bar").response(asJsonUnsafeVerbose[Echo]).send().body
    res must not beNull
  }
}