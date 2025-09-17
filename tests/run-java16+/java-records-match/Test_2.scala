case class Foo(val value: String) extends Comparable[Integer]:
  override def compareTo(other: Integer) = 0

case class Bar(val value: String) extends Comparable[Bar]:
  override def compareTo(other: Bar) = 0

@main def Test =
  val r0 = Rec0_1()
  r0 match { case Rec0_1() => println("empty") }

  val r1 = Rec1_1("hello")
  r1 match { case Rec1_1(s) => println(s) }

  val r2 = Rec2_1(3, "ha")
  r2 match { case Rec2_1(i, s) => println(s * i) }

  // type param (no bounds)
  val r3a = Rec3_1(3, "he")
  r3a match { case Rec3_1(i, s) => println(s * i) }
  val r3b = Rec3_1(3, 7)
  r3b match { case Rec3_1(i, j) => println(i * j) }

  // type param with simple bounds
  val r4 = Rec4_1(3, Foo("hi"))
  r4 match { case Rec4_1(i, f) => println(f.value * i) }

  // type params with recursion / mutual reference
  val r5 = Rec5_1(3 : Integer, Foo("h"), Bar("o"))
  r5 match { case Rec5_1(i, f, b) => println((f.value + b.value) * i) }
