
package chrome.downloads.bindings

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
@JSName("chrome.downloads")
object Downloads extends js.Object {
  // https://developer.chrome.com/extensions/downloads#method-download
  def download(options: js.Object): Unit = js.native
  
  // https://developer.chrome.com/extensions/downloads#method-download
  def download(options: js.Object, callback: js.Function1[Int,Unit]): Unit = js.native
}
