// Ocegueda Meraz Armando # 14212337

///   Arbol impulsado por gradiente
/// Que es el algoritmo Clasificador de �rbol impulsado por gradiente?

Los �rboles impulsados ??por gradientes (GBT) son un m�todo popular de clasificaci�n y regresi�n que utiliza conjuntos de �rboles de decisi�n. spark.ml

/// Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.Pipeline
* import org.apache.spark.ml.classification. {GBTClassificationModel, GBTClassifier}
* import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
* import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("GradientBoostedTreeClassifierExample"). getOrCreate ()

cargan un conjunto de datos en formato LibSVM, lo dividen en conjuntos de entrenamiento y prueba, entrenan en el primer conjunto de datos y luego eval�an en el conjunto de prueba extendido. Necesite dos transformadores de caracter�sticas para preparar los datos; Estas categor�as de �ndice de ayuda para la etiqueta y las caracter�sticas categ�ricas, aceptan metadatos a los DataFrameque el algoritmo del �rbol de decisi�n puede reconocer.

* val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")

Creaci�n del indexador de etiquetas El �ndice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el �ndice.
*     val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Identifique autom�ticamente caracter�sticas categ�ricas e indicacelas y Establezca maxCategor�as para las entidades con> 4 valores distintos se traten como continuas.
* val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 divisi�n de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento).
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrenamos el modelo de clasificador de �rbol impulsado por gradiente el cual contendra las etiquetas del indice y las caracteristicas indexadas.
* val gbt = new GBTClassifier (). setLabelCol ("indexedLabel"). setFeaturesCol ("indexedFeatures"). setMaxIter (10) .setFeatureSubsetStrategy ("auto")

Se crea la conversi�n de las etiquetas indexadas a etiquetas originales.
* val labelConverter = new IndexToString (). setInputCol ("prediction"). setOutputCol ("predicttedLabel"). setLabels (labelIndexer.labels)

Cadena de indexadores y �rbol en una tuber�a.
* val pipeline = new Pipeline (). setStages (Array (labelIndexer, featureIndexer, gbt, labelConverter))

Las tuber�as proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las pr�cticas de aprendizaje autom�tico.
MLlib estandariza las API para algoritmos de aprendizaje autom�tico para facilitar la combinaci�n de m�ltiples algoritmos en una sola tuber�a o flujo de trabajo. Esta secci�n cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuber�a se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje autom�tico, es com�n ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de caracter�sticas num�ricas.
* Aprenda un modelo de predicci�n utilizando los vectores de caracter�sticas y las etiquetas.
C�mo funciona
A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () m�todo se llama en DataFrame. Por Estimatoretapas, el fit () m�todo se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () m�todo se llama en el DataFrame.

Modelo de entrenamiento Esto tambi�n realiza los indexadores.
* modelo val = pipeline.fit (trainingData)

Generamos la predicci�n con la siguiente variable donde se transforma la prueba de los datos
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar
* predictions.select ("predicttedLabel", "label", "features"). show (5)

creaci�n del evaluador de clasificaci�n que contendra las etiquetas indexadas la predicci�n, Seleccionar (predicci�n, etiqueta verdadera) y calcular error de prueba.
* val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("indexedLabel"). setPredictionCol ("prediction"). setMetricName ("precision")
* val precision = evaluator.evaluate (predicciones) -----> nombre de la m�trica de precisi�n
* println (s "Error de prueba = $ {(1.0 - precisi�n)}") -----------> prueba del error en este algoritmo entre m�s cerca de este m�s de 1 precisi�n.

* val gbtModel = model.stages (2) .asInstanceOf [GBTClassificationModel]
* println (s "Modelo GBT de clasificaci�n aprendida: \ n $ {gbtModel.toDebugString}")
#  Modelo de �rbol de clasificaci�n aprendido:
         GBTClassificationModel (uid = gbtc_59f66cfb58b0) con 10 �rboles
        �rbol 0 (peso 1.0):
            Si (caracter�stica 406 <= 12.0)
                Predecir: 1.0
            De lo contrario (funci�n 406> 12.0)
                Predecir: -1.0
        �rbol 1 (peso 0.1):
            Si (caracter�stica 406 <= 12.0)
                Si (funci�n 154 <= 165.5)
                    Predecir: 0.4768116880884702
            De lo contrario (funci�n 154> 165.5)
                Predecir: 0.47681168808847024
            De lo contrario (funci�n 406> 12.0)
                Predecir: -0.4768116880884702
        �rbol 2 (peso 0.1):
            Si (caracter�stica 406 <= 12.0)
                Si (funci�n 241 <= 91.0)
                    Predecir: 0.43819358104272055
            De lo contrario (funci�n 241> 91.0)
                Predecir: 0.43819358104272066
            De lo contrario (funci�n 406> 12.0)
                Si (funci�n 350 <= 82.5)
                Predecir: -0.4381935810427206
            De lo contrario (funci�n 350> 82.5)
                Predecir: -0.43819358104272066
        �rbol 3 (peso 0.1):
            Si (caracter�stica 490 <= 43.0)
                Si (funci�n 512 <= 39.0)
                    Si (caracter�stica 153 <= 8.5)
                        Predecir: 0.4051496802845983
            De lo contrario (funci�n 153> 8.5)
                Predecir: 0.4051496802845984
            De lo contrario (funci�n 512> 39.0)
                Si (funci�n 512 <= 210.0)
                    Si (funci�n 208 <= 241.0)
                        Si (funci�n 124 <= 9.5)
                        Predecir: 0.4051496802845983
                De lo contrario (funci�n 124> 9.5)
                    Predecir: 0.4051496802845984
            De lo contrario (funci�n 208> 241.0)
                Si (funci�n 152 <= 2.5)
                    Predecir: 0.4051496802845983
                De lo contrario (funci�n 152> 2.5)
                    Predecir: 0.40514968028459836
            De lo contrario (funci�n 512> 210.0)
                Predecir: 0.4051496802845984
            De lo contrario (funci�n 490> 43.0)
                Predecir: -0.40514968028459825
        �rbol 4 (peso 0.1):
            Si (caracter�stica 490 <= 43.0)
                Si (funci�n 267 <= 48.5)
                    Si (caracter�stica 238 <= 17.5)
                    Predecir: 0.3765841318352991
            De lo contrario (funci�n 238> 17.5)
                Predecir: 0.37658413183529926
            De lo contrario (funci�n 267> 48.5)
                Predecir: 0.3765841318352994
            De lo contrario (funci�n 490> 43.0)
                Predecir: -0.3765841318352992
        �rbol 5 (peso 0.1):
                Si (caracter�stica 406 <= 12.0)
                    Si (funci�n 272 <= 3.0)
                        Predecir: 0.35166478958101005
            De lo contrario (funci�n 272> 3.0)
                Predecir: 0.3516647895810101
            De lo contrario (funci�n 406> 12.0)
                Predecir: -0.35166478958101005
        �rbol 6 (peso 0.1):
            Si (caracter�stica 462 <= 62.5)
                Si (caracter�stica 241 <= 27.5)
                    Predecir: 0.32974984655529926
            De lo contrario (funci�n 241> 27.5)
                Predecir: 0.3297498465552993
            De lo contrario (funci�n 462> 62.5)
                Si (funci�n 267 <= 82.0)
                    Predecir: -0.32974984655529926
            De lo contrario (funci�n 267> 82.0)
                Predecir: -0.3297498465552993
        �rbol 7 (peso 0.1):
            Si (caracter�stica 406 <= 12.0)
                Si (funci�n 272 <= 80.0)
                    Predecir: 0.31033724551979563
            De lo contrario (funci�n 272> 80.0)
                    Predecir: 0.3103372455197957
            De lo contrario (funci�n 406> 12.0)
                Si (funci�n 239 <= ??28.0)
                    f (caracter�stica 377 <= 237.5)
                        Predecir: -0.3103372455197956
            De lo contrario (funci�n 377> 237.5)
                Predecir: -0.3103372455197957
            De lo contrario (funci�n 239> 28.0)
                Predecir: -0.3103372455197957
        �rbol 8 (peso 0.1):
            Si (caracter�stica 406 <= 12.0)
                Si (caracter�stica 183 <= 163.0)
                    Predecir: 0.2930291649125433
            De lo contrario (funci�n 183> 163.0)
                Predecir: 0.2930291649125434
            De lo contrario (funci�n 406> 12.0)
                Si (funci�n 351 <= 102.0)
                    Predecir: -0.2930291649125433
            De lo contrario (funci�n 351> 102.0)
                Predecir: -0.2930291649125434
        �rbol 9 (peso 0.1):
            Si (caracter�stica 406 <= 12.0)
                Si (funci�n 127 <= 63.5)
                    Si (caracter�stica 241 <= 27.5)
                        Predecir: 0.27750666438358246
            De lo contrario (funci�n 241> 27.5)
                Predecir: 0.2775066643835825
            De lo contrario (funci�n 127> 63.5)
                Predecir: 0.27750666438358257
            De lo contrario (funci�n 406> 12.0)
                Si (funci�n 266 <= 42.5)
                    Predecir: -0.2775066643835825
            De lo contrario (funci�n 266> 42.5)
                Si (caracter�stica 433 <= 173.5)
                    Si (funci�n 155 <= 1.0)
                        Predecir: -0.27750666438358246
            De lo contrario (funci�n 155> 1.0)
                Predecir: -0.2775066643835825
            De lo contrario (caracter�stica 433> 173.5)
                Predecir: -0.27750666438358257 
