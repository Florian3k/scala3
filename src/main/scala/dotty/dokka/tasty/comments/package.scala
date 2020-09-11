package dotty.dokka.tasty.comments

import scala.jdk.CollectionConverters._

import org.jetbrains.dokka.model.{doc => dkkd}
import com.vladsch.flexmark.util.{ast => mdu}

object kt:
  import kotlin.collections.builders.{ListBuilder => KtListBuilder, MapBuilder => KtMapBuilder}

  def emptyList[T] = new KtListBuilder[T]().build()
  def emptyMap[A, B] = new KtMapBuilder[A, B]().build()

object dkk:
  def text(str: String) =
    dkkd.Text(str, kt.emptyList, kt.emptyMap)

object dbg:
  case class See(n: mdu.Node, c: Seq[See]) {
    def show(sb: StringBuilder, indent: Int): Unit = {
      sb ++= " " * indent
      sb ++= n.toString
      sb ++= "\n"
      c.foreach { s => s.show(sb, indent + 2) }
    }

    override def toString = {
      val sb = new StringBuilder
      show(sb, 0)
      sb.toString
    }
  }

  def see(n: mdu.Node): See =
    See(n, n.getChildIterator.asScala.map(see).toList)

  def parseRaw(str: String) =
    MarkdownCommentParser(null).stringToMarkup(str)

  def parse(str: String) =
    parseRaw( Preparser.preparse( Cleaner.clean(str) ).body )
