import java.io.{BufferedWriter, File, FileWriter}

import kong.unirest.Unirest

import scala.jdk.CollectionConverters._

object Main extends App{
  if (args.length != 1) {
    println("USAGE: java -jar <token>")
    System.exit(-1)
  }

  val token = args(0)

  val response = Unirest.post("https://api.hubenthal.no/api/collections/get/posts")
    .header("accept", "application/json")
    .header("Authorization", s"Bearer $token")
    .asObject(classOf[PostWrapper])
    .getBody

  val siteMap =
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
  <url>
    <loc>https://hubenthal.no</loc>
  </url>
  <url>
    <loc>https://hubenthal.no/about</loc>
  </url>
  {response.getEntries.asScala.map(post =>
  <url>
    <loc>https://hubenthal.no/post/{post.getTitle_slug}</loc>
  </url>
  )}
</urlset>

  println(siteMap.toString())
}
