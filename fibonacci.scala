import scala.io.StdIn.readInt

object Main extends App {

  def fibonacci_of(n: Int): Int = {    
  if ( n == 1 || n == 0) {
    n
  } else {
    fibonacci_of(n - 1) + fibonacci_of(n - 2)
  }
}

  println("Enter a number: ")
  var num = readInt()

  while (num <= 0) {
    println("Please, Choose another numnber: ")
    num = readInt()
  }

  val t0 = System.currentTimeMillis()
  println(s"Fibonacci: ${fibonacci_of(num)}")
  val t1 = System.currentTimeMillis()
  println("Elapsed time of " + (t1 - t0) + " Millis")
}
