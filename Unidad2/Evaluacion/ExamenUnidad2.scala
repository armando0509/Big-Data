// Examen Unidad 2

// Desarollar las siguientes instrucciones en Spark con el lenguaje de programacion Scala, utilizando solo la documentacion de la libreria de Machine Learning Mllib de Spark y Google.
// 1./ Del dataset Iris.csv que se encuentra en http://github.com/jcromerohdz/iris, elabora la limpieza de datos necesaria para ser procesado por el siguiente algoritmo(Importante, esta limpieza debe ser por medio de un script Scala en spark)
//la limpieza de los datos para poder procesarlos.

//a.-  De la libreria Mllib de Spark utilice el algoritmo de Machine Learning llamado multilayer perceptron.

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.types._
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val Spark= SparkSession.builder().getOrCreate()

val dataset = spark.read.option("header","true").option("inferSchema", "true")csv("/home/armando/Escritorio/Datos Masivos/Unidad2/Evaluacion/Iris.csv")

dataset.show()

dataset.printSchema()


//b.- Disene su propia arquiectura con un minimo de tres neuronas en capa de entrada, dos o mas neurona en la capa oculta y tres neuronas en la capa de salida. 

val datasetSchema =
StructType(
StructField("Cinco/uno", DoubleType, true) ::
StructField("Tres/tres", DoubleType, true) ::
StructField("Uno/cuatro", DoubleType, true) ::
StructField("Cero/dos",DoubleType, true) ::
StructField("Iris-setosa", StringType, true) :: Nil)

//c.- explique detalladamente cada paso del proceso de machine learning dentro del codigo que desarrolle,
// En esta parte ya importada las librerias necesarias para utilizar el algoritmo multilayer perceptron se abrio un dataset llamado Iris.csv y se mostro en la consola la tablas correspondientes, posteriormente se crearon 3 neuronas en capa de entrada, oculta y de salida, despues se mostrara la estructura del tipo de elemento que que esta en el dataset, mostrandolo en etiquetas.

val dataset2 = spark.read.option("header", "false").schema(datasetSchema)csv("/home/armando/Escritorio/Datos Masivos/Unidad2/Evaluacion/Iris.csv")
dataset2.columns

 val labelIndexer = new StringIndexer().setInputCol("Iris-setosa").setOutputCol("label").fit(dataset2)
 val featureIndexer = new VectorAssembler().setInputCols(Array("Cinco/uno", "Tres/tres", "Uno/cuatro", "Cero/dos")).setOutputCol("features")

//d. Se creo  una variable para el dataset del archivo Iris.csv donde de utilizan las variables de entrenamiento y de prueba, 
val splits = dataset2.randomSplit(Array(0.6, 0.4), seed = 1234L)
    val train = splits(0)
    val test = splits(1)

val layers = Array[Int](4, 5, 4, 3)
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("label").setFeaturesCol("features").setPredictionCol("prediction").setBlockSize(128).setSeed(1234L).setMaxIter(100)

val pipeline = new Pipeline().setStages(Array(labelIndexer,featureIndexer,trainer))
val model = pipeline.fit(train)
val result = model.transform(test)
result.show()
val predictionAndLabels = result.select("prediction", "label")
predictionAndLabels.show()

// e. despues se crea un Array donde se almacenaran los valores que el algoritmo MultilayerPerceptronClassifier clasificara con los datos del dataframe usando los trainin y el test para clasificarlos, posteriormente mostrara el resultado de la prueba de precisión del conjunto mostrado.
 

val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
