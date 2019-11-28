 </b>Primero para empezar a utilizar el desarrollo del examen se hace la importación del algoritmo Multilayer Perception Classifier, estas son importantes para generar parte del algoritmo y trabajar con nuestros datos posteriormente. </b></br> 

 <br> </b>&bull;  1.- hacer la limpieza de datos necesaria para poder ser procesado con el siguiente algoritmo. </b></br> 

<br> import org.apache.spark.ml.classification.MultilayerPerceptronClassifier</br> 
<br> import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator</br> 
<br> import org.apache.spark.sql.SparkSession</br> 
<br> import org.apache.spark.ml.Pipeline</br> 
<br> import org.apache.spark.sql.types._</br> 
<br> import org.apache.log4j._</br> 
<br> Logger.getLogger("org").setLevel(Level.ERROR)</br> 

<br> &bull;  Aqui es donde se hace la creación de la sección de spark.</br> 
<br> val Spark= SparkSession.builder().getOrCreate()</br> 

<br><b> &bull; En esta estructura es donde se hace la carga y lectura de nuestro dataset en un formato csv.</br></b> 
<br>val dataset = spark.read.option("header","true").option("inferSchema", "true")csv("Iris.csv")</br>

<b><br> &bull; Podemos aqui mostrar y ver el data set que cargamos anteriormente.
dataset.show()</br></b>

<br><b> &bull; En esta parte se muestra la visualización del esquema y en como se encuentran nuestros datos.</br></b>
<br>dataset.printSchema()</br>

<br><b> &bull;  Aqui se crea una variable que contiene el objeto StructType el cual puede tener uno o más structfields se puede extraer por nombres, en caso de extraer varios.</br></b>
<br><b> structfield este se volvera un objeto structtype. Si uno de estos nombres que nosotros proporcionamos no contiene un campo</br></b>

<br><b> &bull;  En caso de extraer un solo Structfield se devolverá un valor nulo.</br></b>

<br>val datasetSchema =  /// estructura de un StructType
StructType(</br>

<br>StructField("Cinco/uno", DoubleType, true) :: /// Este es el nombre del campo, tipo de dato en el que corresponda e indicar si los valores de este capo serán valores nulos.</br>
<br>StructField("Tres/tres", DoubleType, true) ::///los metadatos de este campo deben de conservarse durante la transformación si el contenido de la columna no se modifican.</br>
<br>StructField("Uno/cuatro", DoubleType, true) ::</br>
<br>StructField("Cero/dos",DoubleType, true) ::</br>
<br>StructField("Iris-setosa", StringType, true) :: Nil)</br>

<br><b> &bull;  En esta parte es donde se carga y se muestra el análisis del dataframe que esta completo y posteriormente listo para ser utilizado.</br></b>
<br>val dataset2 = spark.read.option("header", "false").schema(datasetSchema)csv("Iris.csv")
dataset2.columns</br>

<br><b> &bull; En esta parte aqui se muestra la lectura del dataset limpio.</br></b>
<br><b> creación de nuestra etiqueta con la que se trabajara en este algoritmo.</br></b>

 <br><b> &bull;  En esta se muestra las características con las que cuenta nuestra dataset en cual será un nuevo vector Assembler el cual contendrá nuestra entrada de datos con un arreglo el cual contendrá nuestras características y tendremos una salida de "factures" características.</br></b>
 <br>val featureIndexer = new VectorAssembler().setInputCols(Array("Cinco/uno", "Tres/tres", "Uno/cuatro", "Cero/dos")).setOutputCol("features")</br>


<br><b> &bull;  En la función de entrenamiento será de 60% de entrenamiento y 40% de prueba con una profundidad o semilla de 1234L.</br></b>
<br>val splits = dataset2.randomSplit(Array(0.6, 0.4), seed = 1234L)</br>
    <br>val train = splits(0)</br>
    <br>val test = splits(1)</br>

<br><b> &bull; Aqui eas donde se muestra a detalle la capa de red neuronal de la siguiente manera:</br></b>
<br><b> &bull; En la la capa de entrada será de tamaño con 4 características, dos capas oculta de tamaño 5 y 4, y así tendremos una salida de tamaño de 3 clases.</br></b>
<br>val layers = Array[Int](4, 5, 4, 3)</br>

<br><b> &bull; creamos el entrenador multilayerPercptronClassifier y establecimiento de sus parámetros.</br></b>
<br><b>val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("label").setFeaturesCol("features").setPredictionCol("prediction").setBlockSize(128).setSeed(1234L).setMaxIter(100)</br></b>

<br><b> &bull; e.- Explique la función de error que utilizó para el resultado final
val pipeline = new Pipeline().setStages(Array(labelIndexer,featureIndexer,trainer))</br></b>

<br><b> &bull;  En esta parte es donde entrenamos el modelo de clasificación de perceptrón multicapa utilizando el estimador anterior.</br></b>
<br>val model = pipeline.fit(train)</br>

<br><b> &bull;  Aqui es donde se calcula la predicción con la muestra del conjunto de prueba.</br></b>
<br>val result = model.transform(test)</br>

<br><b> &bull;  Aqui se imprime los resultados del cálculo del conjunto de prueba con un result.show().
result.show()</br></b>

<br><b> &bull;  Este es la evaluación del modelo para el rendimiento de predicción del algoritmo.</br></b>
<br>val predictionAndLabels = result.select("prediction", "label")</br>
<br>predictionAndLabels.show()</br>

<br><b> &bull;  Este es el evaluador de nuestra predicción.</br></b>
<br>val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")</br>
<br><b>Salida de la prueba de predicion.</br></b>
<br>println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")</br>

<br><b> &bull;  Aqui se muestra la salida de la prueba de la predicción total.</br></b>
<br>println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")</br>