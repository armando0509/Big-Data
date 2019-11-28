</b></br> Tema:</b></br> 

</b></br>   Naive Bayes</b></br> 
</b></br>  Que es el algoritmo Naive Bayes?</b></br> 

<br> Los clasificadores de Naive Bayes son una familia de clasificadores probabilIsticos simples y multiclase especIficos en la aplicaciOn del teorema de Bayes con fuertes supuestos de independencia (ingenuos) entre cada par de caracteristicas.</br> 

<br> Los ingenuos Bayes pueden ser entrenados de manera muy eficiente. Con un solo paso sobre los datos de entrenamiento, calcule la distribucion de probabilidad condicional de cada caracteristica dada cada etiqueta. Para la prediccion, la aplicacion del teorema de Bayes para calcular la distribucion de probabilidad condicional de cada etiqueta dada una observacion.</br> 

<b><br>  &bull;  Como funciona:</b></br> 
<br>  &bull; Importamos las bibliotecas y paquetes necesarios para cargar el programa.</br> 
* <br>  &bull; importar org.apache.spark.ml.classification.NaiveBayes</br> 
* <br>  &bull; import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator</br> 
* <br>  &bull; importar org.apache.spark.sql.SparkSession</br> 

</b></br> &bull;  Creamos una instancia de la sesion de spark</b></br> 
*<br>  val spark = SparkSession.builder.appName ("LinearSVCExample"). getOrCreate ()</br> 

</b></br>  &bull; conjunto de datos de caraga de nuestro</b></br> 
* <br> val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")</br> 

<br>  &bull; Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:</br> 
 <br>  &bull; division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento).</br> 
*<br>  Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3), seed = 1234L)</br> 

<br>  &bull; Entrena un modelo NaiveBayes asiendo un ajuste a los mismos. </br> 
* <br> modelo val = new NaiveBayes (). fit (trainingData)</br> 

<br>  &bull; Seleccion de filas de ejemplo para mostrar las cuales fueron transformadas del conjunto de datos.</br> 
* <br> predicciones val = model.transform (testData)</br> 
* <br> predictions.show () -------------> Muestra de las prediciones</br> 

<br>  &bull; generamos el evaluador de multiclassclassficationevaluator el cual contendra las columnas de las etiquetas, la prediccion de las columnas y el nombre de la metrica.</br> 
* <br> val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("label"). </br>
<br>setPredictionCol ("prediction"). setMetricName ("precision")</br> 

<br>  &bull; creacion de la prediccion variable </br> 
* <br>  &bull; val precision = evaluator.evaluate (predicciones)</br> 
<br> Impresion de los resultados /br> 
* <br>  &bull; println (s "Exactitud del conjunto de prueba = $ precision")</br> 

               </b></br>  precision: doble = 0.8</b></br> 
                </b></br> Prueba de precision del conjunto = 0.8</b></br> 

<b><br>  Ocegueda Meraz Armando #14212337</b></br> 

<b><br>  Tema: Naive_Bayes</b></br> 