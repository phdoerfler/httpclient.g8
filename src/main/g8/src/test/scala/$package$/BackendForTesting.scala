package $package$

import sttp.client._
import org.specs2._
import org.specs2.specification._
import org.specs2.execute._
import TypeAliases._

trait BackendForTesting extends ForEach[Backend] {
  def foreach[R: AsResult](f: Backend => R): Result = {
    val backend = HttpURLConnectionBackend()
    try AsResult(f(backend))
    finally backend.close()
  }
}