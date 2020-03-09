package $package$

import sttp.client._
import sttp.client.circe._
import io.circe._

trait VerboseDecodingFailures {
  def asJsonUnsafeVerbose[B: Decoder: IsOption]: ResponseAs[B, Nothing] =
    asStringAlways.map(deserializeOrThrowVerbose(deserializeJson))
  
  def deserializeOrThrowVerbose[E, T](doDeserialize: String => Either[E, T]): String => T =
    s =>
      doDeserialize(s) match {
        case Left(df: DecodingFailure) => throw df.copy(message =
          DecodingFailure.showDecodingFailure.show(df))
        case Left(e)  => throw DeserializationError(s, e)
        case Right(b) => b
      }
}