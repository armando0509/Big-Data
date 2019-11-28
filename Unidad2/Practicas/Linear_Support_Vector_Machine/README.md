<b><br> Tema: Maquina lineal de vectores de soporte</b></br>


<b><br> Que es el algoritmo Maquina lineal de vectores de soporte?</b></br>

<br>son modelos de aprendizaje supervisados ??con algoritmos de aprendizaje asociados que analizan los datos utilizados para el analisis de clasificacion y regresion </br>

<b><br>Como funciona:</b></br>
<b><br> &bull; Importamos las bibliotecas y paquetes necesarios para cargar el programa.</b></br>
* <b><br> &bull; importar org.apache.spark.ml.classification.LinearSVC</b></br>
* <b><br> &bull; importar org.apache.spark.sql.SparkSession</b></br>

<b><br> Creamos una instancia de la sesion de spark</b></br>
* <br>val spark = SparkSession.builder.appName ("LinearSVCExample"). getOrCreate ()</br>

<b><br>Una maquina de vectores de soporte construido en un hiperplano o un conjunto de hiperplanos en un espacio de dimension alta o infinita, que puede ser utilizado para clasificacion, regresion u otras tareas. Intuitivamente, se logra una buena separacion mediante el hiperplano que tiene la mayor distancia a los puntos de datos de entrenamiento mas cercano de cualquier clase (denominado margen funcional), ya que en general cuanto mayor es el margen, menor es el error de generalizacion del clasificador.</b></br>

*<b><br> &bull;  val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")</b></br>

<b><br>LinearSVC en Spark ML admite la clasificacion binaria con SVM lineal. Internamente, optimiza la perdida de la bisagra utilizando el optimizador OWLQN.Donde se va traer el maximo de interacciones las cuales seran 10 interaciones y un parametro de 1.</b></br>
*   <b><br> &bull; val lsvc = new LinearSVC (). setMaxIter (10) .setRegParam (0.1)</b></br>

<b><br>Modelo de ajuste de entrenamiento.</b></br>
* <b><br> &bull; val lsvcModel = lsvc.fit (entrenamiento)</b></br>

<b><br> &bull; Imprimimos los coeficientes e intercepta para svc. </b></br>
* <b><br> &bull; println (s "Coeficientes: $ {lsvcModel.coefficients} Intercepcion: $ </b></br>
<b><br> &bull; {lsvcModel.intercept}")</b></br>

<b><br>Ocegueda Meraz Armando # 14212337</b></br>
