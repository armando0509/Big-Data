//Ocegueda Meraz Armando
//14212337

def breakingRecords(games:Array[Int]): List[Int] = {   //funcion para para obtener la matriz de enteros con el numero de veces que rompio sus registros

var max = games(0)  // variable Max describe la Mayoria de puntos
var countMax = 0    // variable CountMax  cuenta la Mayoria de puntos
var min = games(0)  // variable Min describe la Menor cantidad de puntos
var countMin = 0     // variable CountMin cuenta la Menor cantidad de puntos
for ( i <- games) {   // ciclo for para llevar los registros
  if ( i > max ){     // en caso de if se cuenta el numero mayor
max = i
countMax += 1 
  }else if( i < min) {  // en caso de else if se cuenta el nuemro menor
min = i 
countMin += 1
 }
}
var MyList = List(countMax,countMin)  // se crea  una variable MyList  para obtener el registro del conteo del Mayor y Menor numero de veces que rompio sus records
return MyList  // se regresa el resultado
}
var totalgames = 9 // variable con el total de numero de juegos
var games = Array(10,5,20,20,4,5,2,25,1)    // variables Games que muestran los numeros respectivos de score 
var games2 = Array(3,4,21,36,10,28,35,5,24,42)

println(breakingRecords(games))  // imprimiendo los resultados
println(breakingRecords(games2))