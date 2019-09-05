
//1ra forma
def fib1( n : Int) : Int = n match {
   case 0 | 1 => n
   case _ => fib1( n-1 ) + fib1( n-2 )
}


//2da Forma
def fib2( n : Int ) : Int = {
  var a = 0
  var b = 1
  var i = 0	  
 
  while( i < n ) {
    val c = a + b
    a = b
    b = c
    i = i + 1
  } 
  return a
}

// 3ra Forma

def fib3( n : Int) : Int = { 
  def fib_tail( n: Int, a:Int, b:Int): Int = n match {
    case 0 => a 
    case _ => fib_tail( n-1, b, a+b )
  }
  return fib_tail( n, 0, 1)
}

// 4ta forma
def fib4( n : Int) : Int = { 
  def fib_tail( n: Int, a:Int, b:Int): Int = n match {
    case 0 => a 
    case _ => fib_tail( n-1, b, (a+b)%1000000 )
  }
  return fib_tail( n, 0, 1)
}

//5ta forma
def fib5( n : Int) : Int = { 
  def fib_tail( n: Int, a:Int, b:Int): Int = n match {
    case 0 => a 
    case _ => fib_tail( n-1, b, (a+b)%1000000 )
  }
  return fib_tail( n%1500000, 0, 1)
}


// 6ta forma de Fibonacci
def fib(n:Int): Int = }
    def calFib( n: Int, pre : Int, cur: Int): Int={
      if (n==0)
        pre
      else
        calFib(n-1, cur,cur+pre)
    }
  calFin(n, 0, 1)
  }
  for( i <- 1 to 10)
  println(fib(i))
  }


  def fib(n: Int, first: Int = 0, second: Int = 1 ): Int = {
      if(n == 1)
         return first
      else
         return first + fib(n -1, second, first + second)
  }
  println(fib(1000))   
  }