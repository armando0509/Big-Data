// Tema:

// -  Maquina lineal de vectores de soporte


// Que es el algoritmo Maquina lineal de vectores de soporte?

son modelos de aprendizaje supervisados ??con algoritmos de aprendizaje asociados que analizan los datos utilizados para el analisis de clasificacion y regresion 

### Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.classification.LinearSVC
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("LinearSVCExample"). getOrCreate ()

Una maquina de vectores de soporte construido en un hiperplano o un conjunto de hiperplanos en un espacio de dimension alta o infinita, que puede ser utilizado para clasificacion, regresion u otras tareas. Intuitivamente, se logra una buena separacion mediante el hiperplano que tiene la mayor distancia a los puntos de datos de entrenamiento mas cercano de cualquier clase (denominado margen funcional), ya que en general cuanto mayor es el margen, menor es el error de generalizacion del clasificador.

* val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")

LinearSVC en Spark ML admite la clasificacion binaria con SVM lineal. Internamente, optimiza la perdida de la bisagra utilizando el optimizador OWLQN.Donde se va traer el maximo de interacciones las cuales seran 10 interaciones y un parametro de 1.
*    val lsvc = new LinearSVC (). setMaxIter (10) .setRegParam (0.1)

Modelo de ajuste de entrenamiento.
* val lsvcModel = lsvc.fit (entrenamiento)

Imprimimos los coeficientes e intercepta para svc. 
* println (s "Coeficientes: $ {lsvcModel.coefficients} Intercepcion: $ {lsvcModel.intercept}")

// Ocegueda Meraz Armando # 14212337
