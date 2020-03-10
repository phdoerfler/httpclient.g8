package $package$

import sttp.client._

object TypeAliases {
  type Backend = SttpBackend[Identity, Nothing, NothingT]
}