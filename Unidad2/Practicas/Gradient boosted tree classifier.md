// Ocegueda Meraz Armando # 14212337

///   Arbol impulsado por gradiente
/// Que es el algoritmo Clasificador de árbol impulsado por gradiente?

Los árboles impulsados ??por gradientes (GBT) son un método popular de clasificación y regresión que utiliza conjuntos de árboles de decisión. spark.ml

/// Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.Pipeline
* import org.apache.spark.ml.classification. {GBTClassificationModel, GBTClassifier}
* import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
* import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("GradientBoostedTreeClassifierExample"). getOrCreate ()

cargan un conjunto de datos en formato LibSVM, lo dividen en conjuntos de entrenamiento y prueba, entrenan en el primer conjunto de datos y luego evalúan en el conjunto de prueba extendido. Necesite dos transformadores de características para preparar los datos; Estas categorías de índice de ayuda para la etiqueta y las características categóricas, aceptan metadatos a los DataFrameque el algoritmo del Árbol de decisión puede reconocer.

* val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")

Creación del indexador de etiquetas El índice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el índice.
*     val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Identifique automáticamente características categóricas e indicacelas y Establezca maxCategorías para las entidades con> 4 valores distintos se traten como continuas.
* val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 división de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento).
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrenamos el modelo de clasificador de árbol impulsado por gradiente el cual contendra las etiquetas del indice y las caracteristicas indexadas.
* val gbt = new GBTClassifier (). setLabelCol ("indexedLabel"). setFeaturesCol ("indexedFeatures"). setMaxIter (10) .setFeatureSubsetStrategy ("auto")

Se crea la conversión de las etiquetas indexadas a etiquetas originales.
* val labelConverter = new IndexToString (). setInputCol ("prediction"). setOutputCol ("predicttedLabel"). setLabels (labelIndexer.labels)

Cadena de indexadores y árbol en una tubería.
* val pipeline = new Pipeline (). setStages (Array (labelIndexer, featureIndexer, gbt, labelConverter))

Las tuberías proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las prácticas de aprendizaje automático.
MLlib estandariza las API para algoritmos de aprendizaje automático para facilitar la combinación de múltiples algoritmos en una sola tubería o flujo de trabajo. Esta sección cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tubería se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje automático, es común ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de características numéricas.
* Aprenda un modelo de predicción utilizando los vectores de características y las etiquetas.
Cómo funciona
A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () método se llama en DataFrame. Por Estimatoretapas, el fit () método se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () método se llama en el DataFrame.

Modelo de entrenamiento Esto también realiza los indexadores.
* modelo val = pipeline.fit (trainingData)

Generamos la predicción con la siguiente variable donde se transforma la prueba de los datos
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar
* predictions.select ("predicttedLabel", "label", "features"). show (5)

creación del evaluador de clasificación que contendra las etiquetas indexadas la predicción, Seleccionar (predicción, etiqueta verdadera) y calcular error de prueba.
* val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("indexedLabel"). setPredictionCol ("prediction"). setMetricName ("precision")
* val precision = evaluator.evaluate (predicciones) -----> nombre de la métrica de precisión
* println (s "Error de prueba = $ {(1.0 - precisión)}") -----------> prueba del error en este algoritmo entre más cerca de este más de 1 precisión.

* val gbtModel = model.stages (2) .asInstanceOf [GBTClassificationModel]
* println (s "Modelo GBT de clasificación aprendida: \ n $ {gbtModel.toDebugString}")
#  Modelo de árbol de clasificación aprendido:
         GBTClassificationModel (uid = gbtc_59f66cfb58b0) con 10 árboles
        Árbol 0 (peso 1.0):
            Si (característica 406 <= 12.0)
                Predecir: 1.0
            De lo contrario (función 406> 12.0)
                Predecir: -1.0
        Árbol 1 (peso 0.1):
            Si (característica 406 <= 12.0)
                Si (función 154 <= 165.5)
                    Predecir: 0.4768116880884702
            De lo contrario (función 154> 165.5)
                Predecir: 0.47681168808847024
            De lo contrario (función 406> 12.0)
                Predecir: -0.4768116880884702
        Árbol 2 (peso 0.1):
            Si (característica 406 <= 12.0)
                Si (función 241 <= 91.0)
                    Predecir: 0.43819358104272055
            De lo contrario (función 241> 91.0)
                Predecir: 0.43819358104272066
            De lo contrario (función 406> 12.0)
                Si (función 350 <= 82.5)
                Predecir: -0.4381935810427206
            De lo contrario (función 350> 82.5)
                Predecir: -0.43819358104272066
        Árbol 3 (peso 0.1):
            Si (característica 490 <= 43.0)
                Si (función 512 <= 39.0)
                    Si (característica 153 <= 8.5)
                        Predecir: 0.4051496802845983
            De lo contrario (función 153> 8.5)
                Predecir: 0.4051496802845984
            De lo contrario (función 512> 39.0)
                Si (función 512 <= 210.0)
                    Si (función 208 <= 241.0)
                        Si (función 124 <= 9.5)
                        Predecir: 0.4051496802845983
                De lo contrario (función 124> 9.5)
                    Predecir: 0.4051496802845984
            De lo contrario (función 208> 241.0)
                Si (función 152 <= 2.5)
                    Predecir: 0.4051496802845983
                De lo contrario (función 152> 2.5)
                    Predecir: 0.40514968028459836
            De lo contrario (función 512> 210.0)
                Predecir: 0.4051496802845984
            De lo contrario (función 490> 43.0)
                Predecir: -0.40514968028459825
        Árbol 4 (peso 0.1):
            Si (característica 490 <= 43.0)
                Si (función 267 <= 48.5)
                    Si (característica 238 <= 17.5)
                    Predecir: 0.3765841318352991
            De lo contrario (función 238> 17.5)
                Predecir: 0.37658413183529926
            De lo contrario (función 267> 48.5)
                Predecir: 0.3765841318352994
            De lo contrario (función 490> 43.0)
                Predecir: -0.3765841318352992
        Árbol 5 (peso 0.1):
                Si (característica 406 <= 12.0)
                    Si (función 272 <= 3.0)
                        Predecir: 0.35166478958101005
            De lo contrario (función 272> 3.0)
                Predecir: 0.3516647895810101
            De lo contrario (función 406> 12.0)
                Predecir: -0.35166478958101005
        Árbol 6 (peso 0.1):
            Si (característica 462 <= 62.5)
                Si (característica 241 <= 27.5)
                    Predecir: 0.32974984655529926
            De lo contrario (función 241> 27.5)
                Predecir: 0.3297498465552993
            De lo contrario (función 462> 62.5)
                Si (función 267 <= 82.0)
                    Predecir: -0.32974984655529926
            De lo contrario (función 267> 82.0)
                Predecir: -0.3297498465552993
        Árbol 7 (peso 0.1):
            Si (característica 406 <= 12.0)
                Si (función 272 <= 80.0)
                    Predecir: 0.31033724551979563
            De lo contrario (función 272> 80.0)
                    Predecir: 0.3103372455197957
            De lo contrario (función 406> 12.0)
                Si (función 239 <= ??28.0)
                    f (característica 377 <= 237.5)
                        Predecir: -0.3103372455197956
            De lo contrario (función 377> 237.5)
                Predecir: -0.3103372455197957
            De lo contrario (función 239> 28.0)
                Predecir: -0.3103372455197957
        Árbol 8 (peso 0.1):
            Si (característica 406 <= 12.0)
                Si (característica 183 <= 163.0)
                    Predecir: 0.2930291649125433
            De lo contrario (función 183> 163.0)
                Predecir: 0.2930291649125434
            De lo contrario (función 406> 12.0)
                Si (función 351 <= 102.0)
                    Predecir: -0.2930291649125433
            De lo contrario (función 351> 102.0)
                Predecir: -0.2930291649125434
        Árbol 9 (peso 0.1):
            Si (característica 406 <= 12.0)
                Si (función 127 <= 63.5)
                    Si (característica 241 <= 27.5)
                        Predecir: 0.27750666438358246
            De lo contrario (función 241> 27.5)
                Predecir: 0.2775066643835825
            De lo contrario (función 127> 63.5)
                Predecir: 0.27750666438358257
            De lo contrario (función 406> 12.0)
                Si (función 266 <= 42.5)
                    Predecir: -0.2775066643835825
            De lo contrario (función 266> 42.5)
                Si (característica 433 <= 173.5)
                    Si (función 155 <= 1.0)
                        Predecir: -0.27750666438358246
            De lo contrario (función 155> 1.0)
                Predecir: -0.2775066643835825
            De lo contrario (característica 433> 173.5)
                Predecir: -0.27750666438358257 
