// Ocegueda Meraz Armando # 14212337

///   Arbol impulsado por gradiente
/// Que es el algoritmo Clasificador de arbol impulsado por gradiente?

Los arboles impulsados ??por gradientes (GBT) son un metodo popular de clasificacion y regresion que utiliza conjuntos de arboles de decision. spark.ml

/// Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.Pipeline
* import org.apache.spark.ml.classification. {GBTClassificationModel, GBTClassifier}
* import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
* import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("GradientBoostedTreeClassifierExample"). getOrCreate ()

cargan un conjunto de datos en formato LibSVM, lo dividen en conjuntos de entrenamiento y prueba, entrenan en el primer conjunto de datos y luego evaluan en el conjunto de prueba extendido. Necesite dos transformadores de caracteristicas para preparar los datos; Estas categorias de indice de ayuda para la etiqueta y las caracteristicas categoricas, aceptan metadatos a los DataFrameque el algoritmo del arbol de decision puede reconocer.

* val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")

Creacion del indexador de etiquetas El indice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el indice.
*     val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Identifique automaticamente caracteristicas categoricas e indicacelas y Establezca maxCategorias para las entidades con> 4 valores distintos se traten como continuas.
* val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento).
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrenamos el modelo de clasificador de arbol impulsado por gradiente el cual contendra las etiquetas del indice y las caracteristicas indexadas.
* val gbt = new GBTClassifier (). setLabelCol ("indexedLabel"). setFeaturesCol ("indexedFeatures"). setMaxIter (10) .setFeatureSubsetStrategy ("auto")

Se crea la conversion de las etiquetas indexadas a etiquetas originales.
* val labelConverter = new IndexToString (). setInputCol ("prediction"). setOutputCol ("predicttedLabel"). setLabels (labelIndexer.labels)

Cadena de indexadores y arbol en una tuberia.
* val pipeline = new Pipeline (). setStages (Array (labelIndexer, featureIndexer, gbt, labelConverter))

Las tuberias proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las practicas de aprendizaje automatico.
MLlib estandariza las API para algoritmos de aprendizaje automatico para facilitar la combinacion de multiples algoritmos en una sola tuberia o flujo de trabajo. Esta seccion cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuberia se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje automatico, es comun ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de caracteristicas numericas.
* Aprenda un modelo de prediccion utilizando los vectores de caracteristicas y las etiquetas.
Como funciona
A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () metodo se llama en DataFrame. Por Estimatoretapas, el fit () metodo se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () metodo se llama en el DataFrame.

Modelo de entrenamiento Esto tambien realiza los indexadores.
* modelo val = pipeline.fit (trainingData)

Generamos la prediccion con la siguiente variable donde se transforma la prueba de los datos
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar
* predictions.select ("predicttedLabel", "label", "features"). show (5)

creacion del evaluador de clasificacion que contendra las etiquetas indexadas la prediccion, Seleccionar (prediccion, etiqueta verdadera) y calcular error de prueba.
* val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("indexedLabel"). setPredictionCol ("prediction"). setMetricName ("precision")
* val precision = evaluator.evaluate (predicciones) -----> nombre de la metrica de precision
* println (s "Error de prueba = $ {(1.0 - precision)}") -----------> prueba del error en este algoritmo entre mas cerca de este mas de 1 precision.

* val gbtModel = model.stages (2) .asInstanceOf [GBTClassificationModel]
* println (s "Modelo GBT de clasificacion aprendida: \ n $ {gbtModel.toDebugString}")
#  Modelo de �rbol de clasificaci�n aprendido:
         GBTClassificationModel (uid = gbtc_59f66cfb58b0) con 10 arboles
        arbol 0 (peso 1.0):
            Si (caracteristica 406 <= 12.0)
                Predecir: 1.0
            De lo contrario (funcion 406> 12.0)
                Predecir: -1.0
        arbol 1 (peso 0.1):
            Si (caracteristica 406 <= 12.0)
                Si (funcion 154 <= 165.5)
                    Predecir: 0.4768116880884702
            De lo contrario (funcion 154> 165.5)
                Predecir: 0.47681168808847024
            De lo contrario (funcion 406> 12.0)
                Predecir: -0.4768116880884702
        arbol 2 (peso 0.1):
            Si (caracteristica 406 <= 12.0)
                Si (funcion 241 <= 91.0)
                    Predecir: 0.43819358104272055
            De lo contrario (funcion 241> 91.0)
                Predecir: 0.43819358104272066
            De lo contrario (funcion 406> 12.0)
                Si (funcion 350 <= 82.5)
                Predecir: -0.4381935810427206
            De lo contrario (funcion 350> 82.5)
                Predecir: -0.43819358104272066
        arbol 3 (peso 0.1):
            Si (caracteristica 490 <= 43.0)
                Si (funcion 512 <= 39.0)
                    Si (caracteristica 153 <= 8.5)
                        Predecir: 0.4051496802845983
            De lo contrario (funcion 153> 8.5)
                Predecir: 0.4051496802845984
            De lo contrario (funcion 512> 39.0)
                Si (funcion 512 <= 210.0)
                    Si (funcion 208 <= 241.0)
                        Si (funcion 124 <= 9.5)
                        Predecir: 0.4051496802845983
                De lo contrario (funcion 124> 9.5)
                    Predecir: 0.4051496802845984
            De lo contrario (funcion 208> 241.0)
                Si (funcion 152 <= 2.5)
                    Predecir: 0.4051496802845983
                De lo contrario (funcion 152> 2.5)
                    Predecir: 0.40514968028459836
            De lo contrario (funcion 512> 210.0)
                Predecir: 0.4051496802845984
            De lo contrario (funcion 490> 43.0)
                Predecir: -0.40514968028459825
        �rbol 4 (peso 0.1):
            Si (caracteristica 490 <= 43.0)
                Si (funcion 267 <= 48.5)
                    Si (caracteristica 238 <= 17.5)
                    Predecir: 0.3765841318352991
            De lo contrario (funcion 238> 17.5)
                Predecir: 0.37658413183529926
            De lo contrario (funcion 267> 48.5)
                Predecir: 0.3765841318352994
            De lo contrario (funcion 490> 43.0)
                Predecir: -0.3765841318352992
        arbol 5 (peso 0.1):
                Si (caracteristica 406 <= 12.0)
                    Si (funcion 272 <= 3.0)
                        Predecir: 0.35166478958101005
            De lo contrario (funcion 272> 3.0)
                Predecir: 0.3516647895810101
            De lo contrario (funcion 406> 12.0)
                Predecir: -0.35166478958101005
        arbol 6 (peso 0.1):
            Si (caracteristica 462 <= 62.5)
                Si (caracteristica 241 <= 27.5)
                    Predecir: 0.32974984655529926
            De lo contrario (funcion 241> 27.5)
                Predecir: 0.3297498465552993
            De lo contrario (funcion 462> 62.5)
                Si (funcion 267 <= 82.0)
                    Predecir: -0.32974984655529926
            De lo contrario (funcion 267> 82.0)
                Predecir: -0.3297498465552993
        arbol 7 (peso 0.1):
            Si (caracteristica 406 <= 12.0)
                Si (funcion 272 <= 80.0)
                    Predecir: 0.31033724551979563
            De lo contrario (funcion 272> 80.0)
                    Predecir: 0.3103372455197957
            De lo contrario (funcion 406> 12.0)
                Si (funcion 239 <= ??28.0)
                    f (caracteristica 377 <= 237.5)
                        Predecir: -0.3103372455197956
            De lo contrario (funcion 377> 237.5)
                Predecir: -0.3103372455197957
            De lo contrario (funcion 239> 28.0)
                Predecir: -0.3103372455197957
        �rbol 8 (peso 0.1):
            Si (caracteristica 406 <= 12.0)
                Si (caracteristica 183 <= 163.0)
                    Predecir: 0.2930291649125433
            De lo contrario (funcion 183> 163.0)
                Predecir: 0.2930291649125434
            De lo contrario (funcion 406> 12.0)
                Si (funcion 351 <= 102.0)
                    Predecir: -0.2930291649125433
            De lo contrario (funcion 351> 102.0)
                Predecir: -0.2930291649125434
        arbol 9 (peso 0.1):
            Si (caracteristica 406 <= 12.0)
                Si (funcion 127 <= 63.5)
                    Si (caracteristica 241 <= 27.5)
                        Predecir: 0.27750666438358246
            De lo contrario (funcion 241> 27.5)
                Predecir: 0.2775066643835825
            De lo contrario (funcion 127> 63.5)
                Predecir: 0.27750666438358257
            De lo contrario (funcion 406> 12.0)
                Si (funcion 266 <= 42.5)
                    Predecir: -0.2775066643835825
            De lo contrario (funcion 266> 42.5)
                Si (caracteristica 433 <= 173.5)
                    Si (funcion 155 <= 1.0)
                        Predecir: -0.27750666438358246
            De lo contrario (funcion 155> 1.0)
                Predecir: -0.2775066643835825
            De lo contrario (caracteristica 433> 173.5)
                Predecir: -0.27750666438358257 
