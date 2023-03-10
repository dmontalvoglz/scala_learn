import scala.io.StdIn.readLine

def fibonacci_of(n: Int): Int = {
    if Set(0, 1) contains n then
      n
    else
      fibonacci_of(n - 1) + fibonacci_of(n - 2)
  }

@main def main =
  println("enter a number: ")
  var num = readLine().toInt

  while
    num <= 0 
  do
    println("please, choose another number: ")
    num = readLine().toInt 

  println(s"Fibonacci: ${fibonacci_of(num)}")