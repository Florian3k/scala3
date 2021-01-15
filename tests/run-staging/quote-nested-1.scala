import quoted._
import scala.quoted.staging._

object Test {
  given Compiler = Compiler.make(getClass.getClassLoader)
  def main(args: Array[String]): Unit = withQuotes {
    val q = '{ (q: Quotes) ?=> '{3} }
    println(q.show)
  }
}
