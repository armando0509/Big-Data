///  Clasificador de Arbol de decision ///   Ocegueda Meraz Armando # 14212337


/// Que es el algoritmo Clasificador de arbol de decision?

Los arboles de decision son una familia popular de metodos de clasificacion y regresion. spark.mlPuede encontrar mas informacion sobre la implementacion en la seccion sobre arboles de decision.

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

cargan un conjunto de datos en formato LibSVM, lo dividen en conjuntos de entrenamiento y prueba, entrenan en el primer conjunto de datos y luego evaluan en el conjunto de prueba extendido. Necesite dos transformadores de caracteristicas para preparar los datos; Estas categorias de indice de ayuda para la etiqueta y las caracteristicas categoricas, aceptan metadatos a los DataFrameque el algoritmo del arbol de decision puede reconocer.

* val data = spark.read.format ("libsvm"). load ("spark / data / mllib / sample_libsvm_data.txt")

indice de etiquetas, agregando metadatos a la columna de etiquetas. Tambien se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el indice.
* val labelIndexer = new StringIndexer (). setInputCol ("label"). setOutputCol ("indexedLabel"). fit (data)

Identifica automaticamente las caracteristicas categoricas e indicazalas.
* val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas).
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrenamos el modelo de arboles de desicion el cual contendra las etiquetas del indice y las caracteristicas indexadas.
* val dt = new DecisionTreeClassifier (). setLabelCol ("indexedLabel"). setFeaturesCol ("indexedFeatures")

Se crea la conversion de las etiquetas indexadas a etiquetas originales.
* val labelConverter = new IndexToString (). setInputCol ("prediction"). setOutputCol ("predicttedLabel"). setLabels (labelIndexer.labels)

Cadena de indexadores y arbol en una tuberia.
* val pipeline = new Pipeline (). setStages (Array (labelIndexer, featureIndexer, dt, labelConverter))

Las tuberias proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las practicas de aprendizaje automatico.
MLlib estandariza las API para algoritmos de aprendizaje automatico para facilitar la combinacion de multiples algoritmos en una sola tuberia o flujo de trabajo. Esta seccion cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuberia se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje automatico, es comun ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de caracteristicas numericas.
* Aprenda un modelo de prediccion utilizando los vectores de caracteristicas y las etiquetas.
Modelo de entrenamiento Esto tambien realiza los indexadores.
* modelo val = pipeline.fit (trainingData)

Generamos la prediccion con la siguiente variable
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar
* predictions.select ("predicttedLabel", "label", "features"). show (5)

creacion del evaluador de clasificacion que contendra las etiquetas indexadas la prediccion, Seleccionar (prediccion, etiqueta verdadera) y calcular error de prueba.
* val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("indexedLabel"). setPredictionCol ("prediction"). setMetricName ("precision")
* val precision = evaluator.evaluate (predicciones) -----> nombre de la metrica de precision
* println (s "Error de prueba = $ {(1.0 - precision)}") -----------> prueba del error en este algoritmo entre mas cerca de este mas de 1 precision.
