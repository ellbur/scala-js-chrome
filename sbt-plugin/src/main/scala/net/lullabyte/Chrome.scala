package net.lullabyte

import chrome.permissions.{HostPermission, APIPermission, Permission}
import sbt._

object Chrome {

  def icons(base: String, name: String, sizes: Set[Int]): Map[Int, String] = {
    sizes.map{ size =>
      size -> s"$base/$size/$name"
    }.toMap
  }

  def buildExtentionDirectory(unpacked: File)(manifest: File, jsLib: File,
                                              jsDeps: File, jsLauncher: File, content: File): File =  {
    IO.createDirectory(unpacked)
    IO.copyDirectory(content, unpacked, overwrite = true, preserveLastModified = true)
    IO.copy(List(
      (jsLib, unpacked / "main.js"),
      (jsDeps, unpacked / "deps.js"),
      (jsLauncher, unpacked / "launcher.js"),
      (manifest, unpacked / "manifest.json")
    ), overwrite = true, preserveLastModified = true)
    unpacked
  }
  def generateManifest(out: File)(name: String, version: String, permissions: Set[Permission],
    defaultLocale: Option[String], offlineEnabled: Boolean, icons: Map[Int, String]): File = {
    import AppManifest._
    val manifest = AppManifest(
      App(
        Background(
          scripts = List("deps.js", "main.js", "launcher.js")
        )
      ),
      name = name,
      version = version,
      manifestVersion = 2,
      icons = icons,
      offlineEnabled = offlineEnabled,
      permissions = permissions,
      defaultLocale = defaultLocale
    )
    val content = upickle.write(manifest)
    IO.write(out, content)
    out
  }
}