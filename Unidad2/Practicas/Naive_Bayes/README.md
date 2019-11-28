// Tema:

//  Naive Bayes
//  Que es el algoritmo Naive Bayes?

Los clasificadores de Naive Bayes son una familia de clasificadores probabilIsticos simples y multiclase especIficos en la aplicaciOn del teorema de Bayes con fuertes supuestos de independencia (ingenuos) entre cada par de caracteristicas.

Los ingenuos Bayes pueden ser entrenados de manera muy eficiente. Con un solo paso sobre los datos de entrenamiento, calcule la distribucion de probabilidad condicional de cada caracteristica dada cada etiqueta. Para la prediccion, la aplicacion del teorema de Bayes para calcular la distribucion de probabilidad condicional de cada etiqueta dada una observacion.

// Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.classification.NaiveBayes
* import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("LinearSVCExample"). getOrCreate ()

conjunto de datos de caraga de nuestro
* val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento).
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3), seed = 1234L)

Entrena un modelo NaiveBayes asiendo un ajuste a los mismos. 
* modelo val = new NaiveBayes (). fit (trainingData)

Seleccion de filas de ejemplo para mostrar las cuales fueron transformadas del conjunto de datos.
* predicciones val = model.transform (testData)
* predictions.show () -------------> Muestra de las prediciones

generamos el evaluador de multiclassclassficationevaluator el cual contendra las columnas de las etiquetas, la prediccion de las columnas y el nombre de la metrica.
* val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("label"). setPredictionCol ("prediction"). setMetricName ("precision")

creacion de la prediccion variable 
* val precision = evaluator.evaluate (predicciones)
Impresion de los resultados 
* println (s "Exactitud del conjunto de prueba = $ precision")

                precision: doble = 0.8
                Prueba de precision del conjunto = 0.8

// Ocegueda Meraz Armando #14212337

// Tema: Naive_Bayes