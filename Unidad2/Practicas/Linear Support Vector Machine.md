// Tema:

// -  M�quina lineal de vectores de soporte


// Que es el algoritmo M�quina lineal de vectores de soporte?

son modelos de aprendizaje supervisados ??con algoritmos de aprendizaje asociados que analizan los datos utilizados para el an�lisis de clasificaci�n y regresi�n 

### Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.classification.LinearSVC
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("LinearSVCExample"). getOrCreate ()

Una m�quina de vectores de soporte construido en un hiperplano o un conjunto de hiperplanos en un espacio de dimensi�n alta o infinita, que puede ser utilizado para clasificaci�n, regresi�n u otras tareas. Intuitivamente, se logra una buena separaci�n mediante el hiperplano que tiene la mayor distancia a los puntos de datos de entrenamiento m�s cercano de cualquier clase (denominado margen funcional), ya que en general cuanto mayor es el margen, menor es el error de generalizaci�n del clasificador.

* val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")

LinearSVC en Spark ML admite la clasificaci�n binaria con SVM lineal. Internamente, optimiza la p�rdida de la bisagra utilizando el optimizador OWLQN.Donde se va traer el m�ximo de interacciones las cuales seran 10 interaciones y un par�metro de 1.
*    val lsvc = new LinearSVC (). setMaxIter (10) .setRegParam (0.1)

Modelo de ajuste de entrenamiento.
* val lsvcModel = lsvc.fit (entrenamiento)

Imprimimos los coeficientes e intercepta para svc. 
* println (s "Coeficientes: $ {lsvcModel.coefficients} Intercepci�n: $ {lsvcModel.intercept}")

// Ocegueda Meraz Armando # 14212337
