

// Ocegueda Meraz Armando # 14212337, Titulo: Basics_Statics



         <br>  Correlacion ######</br> 

//  Que es el algoritmo Correlacion?

Calculo de correlacion la matriz de correlacion para el conjunto de datos de entrada de vectores utilizando el metodo especificado. La salida sera un DataFrame que contiene la matriz de correlacion de la columna de vectores.

// Como funciona
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
import org.apache.spark.ml.linalg. {Matriz, Vectores}
importar org.apache.spark.ml.stat.Correlation
importar org.apache.spark.sql.Row
importar org.apache.spark.sql.SparkSession

// Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("CorrelationExample"). GetOrCreate ()

// Realizamos una importacion de:
 import spark.implicits._
implicitas objeto de conversaciones implicitas para convertir objetos Scala (incl. DDR) en un conjunto de datos, marco de datos, columnas para apoyar conversaciones.implicits es un objeto que se define dentro de SparkSession y, por lo tanto, requiere que cree una instancia de SparkSession primero antes de importar las implicitsconversiones.

// Declaracion de los vectores que se utiliza para este algoritmo las diferencias que existen entre declarar un vector como disperso o un vector denso es que el vector denso esta respaldado por una matriz doble que representa sus valores de entrada, mientras que un vector disperso esta respaldado por dos matrices paralelas: indices y valores.

* val data = Seq (
  Vectors.sparse (4, Seq ((0, 1.0), (3, -2.0))),
  Vectores densos (4.0, 5.0, 0.0, 3.0),
  Vectors.dense (6.0, 7.0, 0.0, 8.0),
  Vectors.sparse (4, Seq ((0, 9.0), (3, 1.0)))
    )

// Posteriormente creamos el marco de datos y creemos un marco de datos a partir  de la siguiente manera:
* val df = data.map (Tuple1.apply) .toDF ("caracteristicas")

// Generamos nuestra Matriz de correlacion de Pearson e imprimimos los resultados:
* val Row (coeff1: Matrix) = Correlation.corr (df, "features"). head
* println (s "Matriz de correlacion de Pearson: \ n $ coeff1")

Y por ultimo generamos nuestra Matriz de correlacion de Spearman e imprimimos los resultados:
* val Row (coeff2: Matrix) = Correlation.corr (df, "features", "spearman"). head
* println (s "Matriz de correlacion de Spearman: \ n $ coeff2")


####  Evaluacion_de_la_hipotesis
###  Que es el algoritmo Evaluacion de la hipotesis?
La prueba de hipotesis es una herramienta poderosa en estadistica para determinar si un resultado es estadisticamente significativo, si este resultado es por casualidad o no. spark.mlactualmente es compatible con Chi-cuadrado de Pearson (2) pruebas de independencia.

###  Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* import org.apache.spark.ml.linalg. {Vector, Vectors}
* importar org.apache.spark.ml.stat.ChiSquareTest
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("EvaluacionHipotesisExample"). getOrCreate ()

Realizamos una importacion de:
* import spark.implicits._

implicitasobjeto de conversaciones implicitas para convertir objetos Scala (incl. DDR) en un conjunto de datos, marco de datos, columnas para apoyar conversaciones.
implicits es un objeto que se define dentro de SparkSession y, por lo tanto, requiere que cree una instancia de SparkSession primero antes de importar las implicitsconversiones.

La declaracion de los vectores densos esta respaldada por una matriz doble que representa sus valores de entrada.

* val data = Seq (
  (0.0, Vectors.dense (0.5, 10.0)),
  (0.0, Vectors.dense (1.5, 20.0)),
  (1.0, Vectors.dense (1.5, 30.0)),
  (0.0, Vectors.dense (3.5, 30.0)),
  (0.0, Vectors.dense (3.5, 40.0)),
  (1.0, Vectors.dense (3.5, 40.0))
) 
 Convertimos de etiquetas a caracteristicas el dataframe
 * val df = data.toDF ("etiqueta", "caracteristicas")


Funcion de prueba de invocacion de la clase ChiSquareTest:
Realice la prueba de independencia de Pearson para cada caracteristica contra la etiqueta. Para cada caracteristica, los pares (caracteristica, etiqueta) verifique en una matriz de contingencia para la cualidad calcular la estadistica Chi-cuadrado. Todos los valores de etiquetas y caracteristicas deben ser categoricos.
La hipotesis nula es que la aparicipn de los resultados es estadisticamente independiente.

Parametros:
conjunto de datos- DataFrame de etiquetas categoricas y caracteristicas categoricas. Las caracteristicas con valor real se trataron como categorias para cada valor distinto.

Devoluciones:
DataFrame que contiene el resultado de la prueba para cada caracteristica contra la etiqueta. Este DataFrame contiene una fila unica con los siguientes campos: - pValues: Vector - degreesOfFreedom: Array [Int] - estadisticas: Vector Cada uno de estos campos tiene un valor por caracteristica.
* val chi = ChiSquareTest.test (df, "caracteristicas", "etiqueta"). head

Finalmente imprimimos los resultados del algoritmo:
* println (s "pValues ??= $ {chi.getAs [ Vector ] (0)}") ---> Nombre de la columna de caracteristicas en el conjunto de datos, de tipo Vector (VectorUDT) y Nombre de la columna de etiqueta en el conjunto de datos, de cualquier tipo numerico
*   println (s "degreesOfFreedom $ {chi.getSeq [ Int ] (1) .mkString (" [",", ","] ")}") ---> grado de libertad en el que se puede desplazar
son el numero de celdas que necesita completar antes, dados los totales en los margenes, puede completar el resto de la cuadricula utilizando una formula.
Si tiene un conjunto dado de totales para cada columna y fila, entonces no tiene libertad ilimitada al completar las celdas. 
* println (s "estadisticas $ {chi.getAs [ Vector ] (2)}")
Se tiene el 75% de posibilidades de encontrar una discrepancia entre las distribuciones observadas y esperadas que es al menos este extremo.

####  Sumarizacion 
###  Que es el algoritmo Sumarizacion?
El uso del resumen para calcular la media y la varianza para una columna de vector del marco de datos de entrada, con y sin una columna de peso.

###  Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* import org.apache.spark.ml.linalg. {Vector, Vectors}
* importar org.apache.spark.ml.stat.Summarizer
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("CorrelationExample"). GetOrCreate ()

Realizamos una importacion de:
* import spark.implicits._
implicitasobjeto de conversaciones implicitas para convertir objetos Scala (incl. DDR) en un conjunto de datos, marco de datos, columnas para apoyar conversaciones.
implicits es un objeto que se define dentro de SparkSession y, por lo tanto, requiere que cree una instancia de SparkSession primero antes de importar las implicitsconversiones.
* Importar resumen ._
Herramientas para estadisticas vectorizadas en vectores MLlib.
Los metodos en este paquete tienen varias estadisticas para los vectores contenidos dentro de DataFrames.

La declaracion de los vectores densos esta respaldada por una matriz doble que representa sus valores de entrada.
val data = Seq (
  (Vectors.dense (2.0, 3.0, 5.0), 1.0),
  (Vectors.dense (4.0, 6.0, 7.0), 2.0)
  )

 creacion del marco de datos y Convertimos de etiquetas a peso el marco de datos
* val df = data.toDF ("caracteristicas", "peso")

Calcule la media y la varianza de las columnas del marco de datos usando weightCol el cual es Parametro para el nombre de la columna de peso.
* val (meanVal, varianceVal) = df.select (metrics ("mean", "varnce"). summary ($ "features", $ "weight"). as ("summary")). select ("summary.mean "," summary.variance "). como [(Vector, Vector)]. first ()

Resultado 
* println (s "con ponderacion: mean = $ {meanVal}, varnce = $ {varianceVal}")


Calcule la media y la varianza de las columnas del marco de datos sin usar weightCol
* val (meanVal2, varianceVal2) = df.select (mean ($ "features"), varnce ($ "features")). como [(Vector, Vector)]. first ()

Resultados
* println (s "sin peso: mean = $ {meanVal2}, sum = $ {varianceVal2}")