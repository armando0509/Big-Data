///  Clasificador de �rbol de decisi�n ///   Ocegueda Meraz Armando # 14212337


/// Que es el algoritmo Clasificador de �rbol de decisi�n?

Los �rboles de decisi�n son una familia popular de m�todos de clasificaci�n y regresi�n. spark.mlPuede encontrar m�s informaci�n sobre la implementaci�n en la secci�n sobre �rboles de decisi�n.

// Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.Pipeline
* importar org.apache.spark.ml.classification.DecisionTreeClassificationModel
* importar org.apache.spark.ml.classification.DecisionTreeClassifier
* import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
* import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("DecisionTreeClassificationExample"). getOrCreate ()

cargan un conjunto de datos en formato LibSVM, lo dividen en conjuntos de entrenamiento y prueba, entrenan en el primer conjunto de datos y luego eval�an en el conjunto de prueba extendido. Necesite dos transformadores de caracter�sticas para preparar los datos; Estas categor�as de �ndice de ayuda para la etiqueta y las caracter�sticas categ�ricas, aceptan metadatos a los DataFrameque el algoritmo del �rbol de decisi�n puede reconocer.

* val data = spark.read.format ("libsvm"). load ("spark / data / mllib / sample_libsvm_data.txt")

�ndice de etiquetas, agregando metadatos a la columna de etiquetas asi�ticas Tambi�n se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el �ndice.
* val labelIndexer = new StringIndexer (). setInputCol ("label"). setOutputCol ("indexedLabel"). fit (data)

Identifica autom�ticamente las caracter�sticas categ�ricas e indicazalas.
* val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 divisi�n de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas).
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrenamos el modelo de arboles de desicion el cual contendra las etiquetas del indice y las caracteristicas indexadas.
* val dt = new DecisionTreeClassifier (). setLabelCol ("indexedLabel"). setFeaturesCol ("indexedFeatures")

Se crea la conversi�n de las etiquetas indexadas a etiquetas originales.
* val labelConverter = new IndexToString (). setInputCol ("prediction"). setOutputCol ("predicttedLabel"). setLabels (labelIndexer.labels)

Cadena de indexadores y �rbol en una tuber�a.
* val pipeline = new Pipeline (). setStages (Array (labelIndexer, featureIndexer, dt, labelConverter))

Las tuber�as proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las pr�cticas de aprendizaje autom�tico.
MLlib estandariza las API para algoritmos de aprendizaje autom�tico para facilitar la combinaci�n de m�ltiples algoritmos en una sola tuber�a o flujo de trabajo. Esta secci�n cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuber�a se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje autom�tico, es com�n ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de caracter�sticas num�ricas.
* Aprenda un modelo de predicci�n utilizando los vectores de caracter�sticas y las etiquetas.
Modelo de entrenamiento Esto tambi�n realiza los indexadores.
* modelo val = pipeline.fit (trainingData)

Generamos la predicci�n con la siguiente variable
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar
* predictions.select ("predicttedLabel", "label", "features"). show (5)

creaci�n del evaluador de clasificaci�n que contendra las etiquetas indexadas la predicci�n, Seleccionar (predicci�n, etiqueta verdadera) y calcular error de prueba.
* val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("indexedLabel"). setPredictionCol ("prediction"). setMetricName ("precision")
* val precision = evaluator.evaluate (predicciones) -----> nombre de la m�trica de precisi�n
* println (s "Error de prueba = $ {(1.0 - precisi�n)}") -----------> prueba del error en este algoritmo entre m�s cerca de este m�s de 1 precisi�n.
