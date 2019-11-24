
#### Tema

####  Clasificador One-vs-Rest

###  Que es el algoritmo Clasificador One-vs-Rest?

OneVsRest es un ejemplo de una reducci�n de aprendizaje autom�tico para realizar una clasificaci�n multiclase dado un clasificador base que puede realizar la clasificaci�n binaria de manera eficiente. Tambi�n se conoce como "Uno contra todos".
La clasificaci�n uno contra todos es un m�todo que involucra entrenamiento con N clasificadores binarios distintos, cada uno dise�ado para reconocer una clase particular. One-vs-All es derivado de una reducci�n de aprendizaje automatizado para poder realizar una clasificaci�n multiclase dado un clasificador base que puede realizar la clasificaci�n binaria de manera eficiente.
OneVsRestse implementa como un Estimator. Para el clasificador base, toma instancias de clasificaci�n crea un problema de clasificaci�n binaria para cada una de las k clases. El clasificador para la clase i est� entrenado para predecir si la etiqueta es io no, distinguiendo la clase i de todas las dem�s clases.

Las predicciones se realizan evaluando cada clasificador binario y el �ndice del clasificador m�s seguro se genera como etiqueta.

### Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.classification. {LogisticRegression, OneVsRest}
* import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("OneVsRestExample"). getOrCreate ()

Conjunto de datos de carga de nuestro 

* val Data = spark.read.format ("libsvm"). load ("sample_multiclass_classification_data.txt")
Muestra de datos()

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 divisi�n de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (20% para pruebas y 80% de entrenamiento).
* Val Array (tren, prueba) = inputData.randomSplit (Array (0.8, 0.2))

Instanciamos la base del clasificador el cual contenbra la m�xima de interacciones la tolerancia y el ajuste de intercepciones.
*   val clasificador = new LogisticRegression (). setMaxIter (10) .setTol (1E-6) .setFitIntercept (true)

Generamos las instancias de OneVsRest el cual nos va a traer el clasificador
* val ovr = nuevo OneVsRest (). setClassifier (clasificador)

Entrenamiento del modelo multiclases generando un ajuste a los datos de entrenamiento
* val ovrModel = ovr.fit (tren)

Transformamos los datos de prueba en el m�todo de predicci�n
* predicciones val = ovrModel.transform (prueba)

generamos el evaluador el cual traeremos la instancia nombre de la metrica
* val evaluator = new MulticlassClassificationEvaluator (). setMetricName ("precision")

calcular el error de clasificaci�n en los datos de prueba.
* val precision = evaluator.evaluate (predicciones)
* println (s "Error de prueba = $ {1 - precisi�n}")
                precisi�n: Doble = 0.9642857142857143
                Error de prueba = 0.0357142857142857 // el error de predicci�n es muy bajo por lo que se puede decir que la predicci�n que el algoritmo hace es muy buena.

// Autores: Ocegueda Meraz Armando # 14212337
