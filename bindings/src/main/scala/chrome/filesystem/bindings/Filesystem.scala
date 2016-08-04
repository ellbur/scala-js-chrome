
package chrome.filesystem.bindings

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
@JSName("chrome.fileSystem")
object Filesystem extends js.Object {
  // So named because the actual JavaScript function overloads
  // the callback (which is tricky to do with static typing).
  @JSName("chooseEntry")
  def chooseOneEntry(options: js.Object, callback: js.Function1[FileEntry, Unit]): Unit = js.native
}
