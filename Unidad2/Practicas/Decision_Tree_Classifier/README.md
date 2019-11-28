<b><br> Clasificador de Arbol de decision </b></br>    <b> Ocegueda Meraz Armando # 14212337</b> 


<b><br>Que es el algoritmo Clasificador de arbol de decision? </b></br>

<br>Los arboles de decision son una familia popular de metodos de clasificacion y regresion. spark.mlPuede encontrar mas informacion sobre la implementacion en la seccion sobre arboles de decision.</br>

<b><br> &bull; Como funciona:</b></br>

<b><br> &bull; Importamos las bibliotecas y paquetes necesarios para cargar el programa.</b></br>

 <br>&bull; importar org.apache.spark.ml.Pipeline</br>
<br> &bull; importar org.apache.spark.ml.classification.DecisionTreeClassificationModel</br>
 <br>&bull; importar org.apache.spark.ml.classification.DecisionTreeClassifier</br>
<br> &bull; import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator</br>
<br> &bull; import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}</br>
<br>&bull;  importar org.apache.spark.sql.SparkSession</br>

<b><br>Creamos una instancia de la sesion de spark</br></b>
 <br>val spark = SparkSession.builder.appName ("DecisionTreeClassificationExample"). </br>
<br>getOrCreate ()</br>

<br>cargan un conjunto de datos en formato LibSVM, lo dividen en conjuntos de entrenamiento y prueba, entrenan en el primer conjunto de datos y luego evaluan en el conjunto de prueba extendido. Necesite dos transformadores de caracteristicas para preparar los datos; Estas categorias de indice de ayuda para la etiqueta y las caracteristicas categoricas, aceptan metadatos a los DataFrameque el algoritmo del arbol de decision puede reconocer.</br>

<br> &bull; val data = spark.read.format ("libsvm"). load ("spark / data / mllib / sample_libsvm_data.txt")</br>

<br> &bull; indice de etiquetas, agregando metadatos a la columna de etiquetas. Tambien se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el indice.</br>

<br> &bull; val labelIndexer = new StringIndexer (). setInputCol ("label"). setOutputCol ("indexedLabel"). fit (data)</br>

<br> &bull;Identifica automaticamente las caracteristicas categoricas e indicazalas.</br>
*<br> val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)</br>

<br> &bull; Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas).</br>
* <br>Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))</br>

<br>Entrenamos el modelo de arboles de desicion el cual contendra las etiquetas del indice y las caracteristicas indexadas.</br>
* <br>val dt = new DecisionTreeClassifier (). setLabelCol ("indexedLabel"). setFeaturesCol ("indexedFeatures")</br>
 
<br> &bull; Se crea la conversion de las etiquetas indexadas a etiquetas originales.</br>
* <br>val labelConverter = new IndexToString (). setInputCol ("prediction"). setOutputCol ("predicttedLabel"). setLabels (labelIndexer.labels)</br>

<br> &bull; Cadena de indexadores y arbol en una tuberia.</br>
* val pipeline = new Pipeline (). setStages (Array (labelIndexer, featureIndexer, dt, labelConverter))

<br> Las tuberias proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las practicas de aprendizaje automatico.
MLlib estandariza las API para algoritmos de aprendizaje automatico para facilitar la combinacion de multiples algoritmos en una sola tuberia o flujo de trabajo. Esta seccion cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuberia se inspira principalmente en el proyecto scikit-learn.</br>

<br>En el aprendizaje automatico, es comun ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:</br>

<br>&bull;Divide el texto de cada documento en palabras.</br>
 <br>&bull;Convierta las palabras de cada documento en un vector de caracteristicas numericas.</br>
 <br>&bull;Aprenda un modelo de prediccion utilizando los vectores de caracteristicas y las etiquetas.</br>
<br>&bull;Modelo de entrenamiento Esto tambien realiza los indexadores.</br>
<br>&bull;modelo val = pipeline.fit (trainingData)</br>

<br>&bull;Generamos la prediccion con la siguiente variable</br>
* <br>predicciones val = model.transform (testData)</br>

<br>Seleccione filas de ejemplo para mostrar</br>
* <br>predictions.select ("predicttedLabel", "label", "features"). show (5)</br>

<br>creacion del evaluador de clasificacion que contendra las etiquetas indexadas la prediccion, Seleccionar (prediccion, etiqueta verdadera) y calcular error de prueba.</br>
* <br>val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("indexedLabel").</br>
<br> setPredictionCol ("prediction"). setMetricName ("precision")</br>
* <br>val precision = evaluator.evaluate (predicciones) -----> nombre de la metrica de precision<br>
* <br>println (s "Error de prueba = $ {(1.0 - precision)}") -----------> prueba del error en este algoritmo entre mas cerca de este mas de 1 precision.</br>
