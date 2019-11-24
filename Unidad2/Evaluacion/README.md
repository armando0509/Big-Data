// Primero para empezar a utilizar el desarrollo del examen se hace la importación del algoritmo Multilayer Perception Classifier, estas son importantes para generar parte del algoritmo y trabajar con nuestros datos posteriormente
//1.- hacer la limpieza de datos necesaria para poder ser procesado con el siguiente algoritmo
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.types._
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Aqui es donde se hace la creación de la sección de spark.
val Spark= SparkSession.builder().getOrCreate()

// En esta estructura es donde se hace la carga y lectura de nuestro dataset en un formato csv.
val dataset = spark.read.option("header","true").option("inferSchema", "true")csv("Iris.csv")

//Podemos aqui mostrar y ver el data set que cargamos anteriormente.
dataset.show()

//En esta parte se muestra la visualización del esquema y en como se encuentran nuestros datos.
dataset.printSchema()

// Aqui se crea una variable que contiene el objeto StructType el cual puede tener uno o más structfields se puede extraer por nombres, en caso de extraer varios
// structfield este se volvera un objeto structtype. Si uno de estos nombres que nosotros proporcionamos no contiene un campo

//En caso de extraer un solo Structfield se devolverá un valor nulo.

val datasetSchema =  /// estructura de un StructType
StructType(

StructField("Cinco/uno", DoubleType, true) :: /// Este es el nombre del campo, tipo de dato en el que corresponda e indicar si los valores de este capo serán valores nulos.
StructField("Tres/tres", DoubleType, true) ::///los metadatos de este campo deben de conservarse durante la transformación si el contenido de la columna no se modifican.
StructField("Uno/cuatro", DoubleType, true) ::
StructField("Cero/dos",DoubleType, true) ::
StructField("Iris-setosa", StringType, true) :: Nil)

/// En esta parte es donde se carga y se muestra el análisis del dataframe que esta completo y posteriormente listo para ser utilizado.
val dataset2 = spark.read.option("header", "false").schema(datasetSchema)csv("Iris.csv")
dataset2.columns

//En esta parte aqui se muestra la lectura del dataset limpio.
// creación de nuestra etiqueta con la que se trabajara en este algoritmo.

 //En esta se muestra las características con las que cuenta nuestra dataset en cual será un nuevo vector Assembler el cual contendrá nuestra entrada de datos con un arreglo el cual contendrá nuestras características y tendremos una salida de "factures" características.
 val featureIndexer = new VectorAssembler().setInputCols(Array("Cinco/uno", "Tres/tres", "Uno/cuatro", "Cero/dos")).setOutputCol("features")


// En la función de entrenamiento será de 60% de entrenamiento y 40% de prueba con una profundidad o semilla de 1234L.
val splits = dataset2.randomSplit(Array(0.6, 0.4), seed = 1234L)
    val train = splits(0)
    val test = splits(1)

// Aqui eas donde se muestra a detalle la capa de red neuronal de la siguiente manera:
// En la la capa de entrada será de tamaño con 4 características, dos capas oculta de tamaño 5 y 4, y así tendremos una salida de tamaño de 3 clases.
val layers = Array[Int](4, 5, 4, 3)

//creamos el entrenador multilayerPercptronClassifier y establecimiento de sus parámetros.
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("label").setFeaturesCol("features").setPredictionCol("prediction").setBlockSize(128).setSeed(1234L).setMaxIter(100)

//e.- Explique la función de error que utilizó para el resultado final
val pipeline = new Pipeline().setStages(Array(labelIndexer,featureIndexer,trainer))

//En esta parte es donde entrenamos el modelo de clasificación de perceptrón multicapa utilizando el estimador anterior.
val model = pipeline.fit(train)

//Aqui es donde se calcula la predicción con la muestra del conjunto de prueba.
val result = model.transform(test)

//Aqui se imprime los resultados del cálculo del conjunto de prueba con un result.show().
result.show()

//Este es la evaluación del modelo para el rendimiento de predicción del algoritmo.
val predictionAndLabels = result.select("prediction", "label")
predictionAndLabels.show()

// Este es el evaluador de nuestra predicción.
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
//Salida de la prueba de predicion.
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")

//Aqui se muestra la salida de la prueba de la predicción total.
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")