<b><br>Multilayer Perceptor Classifier</b></br>

<b><br>Que es el algoritmo Multilayer Perceptor Classifier ? </b></br>

<b><br>Un perceptrón multicapa (MLP) es una clase de red neuronal artificial de alimentación directa (ANN). El término MLP se usa de manera ambigua, a veces de manera flexible para referirse a cualquier ANN anticipado, a veces estrictamente para referirse a redes compuestas de múltiples capas de perceptrones (con activación de umbral); ver § Terminología . Los perceptrones multicapa a veces se denominan coloquialmente redes neuronales "vainilla", especialmente cuando tienen una sola capa oculta.</b></br>


<br>Un MLP consta de al menos tres capas de nodos: una capa de entrada, una capa oculta y una capa de salida. Excepto por los nodos de entrada, cada nodo es una neurona que usa una función de activación no lineal . MLP utiliza una técnica de aprendizaje supervisado llamada retropropagación para capacitación.</br>

<br>Sus múltiples capas y activación no lineal distinguen a MLP de un perceptrón lineal . Puede distinguir datos que no son linealmente separables .</br>

<b><br>Función de activación</b></br>

<br>Si un perceptrón multicapa tiene una función de activación lineal en todas las neuronas, es decir, una función lineal que asigna las entradas ponderadas a la salida de cada neurona, entonces el álgebra lineal muestra que cualquier número de capas se puede reducir a una entrada de dos capas. modelo de salida. En las MLP, algunas neuronas usan una función de activación no lineal que se desarrolló para modelar la frecuencia de potenciales de acción , o activación, de las neuronas biológicas.</br>

<b><br>Capas </b></br>
<br>El MLP consta de tres o más capas (una capa de entrada y una de salida con una o más capas ocultas ) de nodos de activación no lineal. Como los MLP están completamente conectados, cada nodo en una capa se conecta con un cierto peso {\ displaystyle w_ {ij}} w_ {ij} a cada nodo en la siguiente capa.</br>

<b><br>Aplicaciones </b></br>

<br>Los MLP son útiles en la investigación por su capacidad para resolver problemas estocásticamente, lo que a menudo permite soluciones aproximadas para problemas extremadamente complejos como la aproximación de la aptitud .</br>

<br>Los MLP son aproximadores de funciones universales como se muestra en el teorema de Cybenko,  por lo que pueden usarse para crear modelos matemáticos mediante análisis de regresión. Como la clasificación es un caso particular de regresión cuando la variable de respuesta es categórica , los MLP son buenos algoritmos de clasificación.</br>


<br>Importamos las librerias necesarias para usar el algoritmo </br>
<br>import org.apache.spark.ml.classification.MultilayerPerceptronClassifier</br>
<br>import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator</br>
<br>$example off$</br>
<br>import org.apache.spark.sql.SparkSession</br>

</b></br> Cargamos el DataFrame.</b></br>
    <br>val data = spark.read.format("libsvm").load("/home/armando/Escritorio/spark-2.4.4-bin-hadoop2.7/data/mllib/sample_multiclass_classification_data.txt")</br>


   <br> Split the data into train and test</br>
     <br>val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)</br>
    <br>val train = splits(0)</br>
    <br>val test = splits(1)</br>

   <br>Se especifica el numero de rede de neuronas y se declara en un Array </br>
    val layers = Array[Int](4, 5, 4, 3)

   <br> Se crea la variable con el entrenador con el nuemero de iteraciones</br>
   <br> val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)</br>

    <br> Este el  modelo de entrenamiento </br>
    val model = trainer.fit(train)

    <br> Se hace el calculo y el procedimiento del test</br>
     <br>val result = model.transform(test)</br>
     <br>val predictionAndLabels = result.select("prediction", "label")</br>
     <br>val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")</br>

   <br> println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")</br>


   <b><br> Tema: Multilayer Perceptor Classifier </b> </br>   
   
<b><br>  Ocegueda Meraz Armando # 14212337</b> </br>   