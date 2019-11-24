// Tema

//  Clasificador Random Forest

// Que es el algoritmo Clasificador Random Forest?

Consiste en una gran cantidad de árboles de decisión individual que operan como un conjunto. Cada árbol individual en el bosque aleatorio escupe una predicción de clase y la clase con más votos se convertirá en la predicción de nuestro modelo.

// Como funciona:
Importamos las bibliotecas y paquetes necesarios para cargar el programa.
* importar org.apache.spark.ml.Pipeline
* import org.apache.spark.ml.classification. {RandomForestClassificationModel, RandomForestClassifier}
* import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
* import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}
* importar org.apache.spark.sql.SparkSession

Creamos una instancia de la sesion de spark
* val spark = SparkSession.builder.appName ("RandomForestClassifierExample"). getOrCreate ()

Conjunto de datos de carga de nuestro 

* val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")
Muestra de datos()

Índice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el índice. 
val labelIndexer = new StringIndexer () .setInputCol ("etiqueta") .setOutputCol ("indexedLabel") .fit (datos)

Identifique automáticamente las características categóricas y las indexa, Se establecerán las categorías máximas para las entidades con> 4 valores distintos se traten como continuas. 
* val featureIndexer = new VectorIndexer () .setInputCol ("features") .setOutputCol ("indexedFeatures") .setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 división de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento). 
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrena un modelo RandomForest. 
* val rf = new RandomForestClassifier () .setLabelCol ("indexedLabel") .setFeaturesCol ("indexedFeatures") .setNumTrees (10)

Convierte las etiquetas indexadas de nuevo a etiquetas originales. 
* val labelConverter = nuevo IndexToString () .setInputCol ("predicción") .setOutputCol ("predicttedLabel") .setLabels (labelIndexer.labels)

Indicadores de cadena y bosque en una tubería. 
* val pipeline = new Pipeline () .setStages (Array (labelIndexer, featureIndexer, rf, labelConverter))

Modelo de tren. Esto también ejecuta los indexadores.
* modelo val = pipeline.fit (trainingData)

Las tuberías proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las prácticas de aprendizaje automático.
MLlib estandariza las API para algoritmos de aprendizaje automático para facilitar la combinación de múltiples algoritmos en una sola tubería o flujo de trabajo. Esta sección cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tubería se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje automático, es común ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de características numéricas.
* Aprenda un modelo de predicción utilizando los vectores de características y las etiquetas.
Cómo funciona
A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () método se llama en DataFrame. Por Estimatoretapas, el fit () método se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () método se llama en el DataFrame.

Hacer predicciones. 
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar. 
* predictions.select ("predicttedLabel", "label", "features") .show (5)

Seleccione (predicción, etiqueta verdadera) y calcule el error de prueba. 
* val evaluator = new MulticlassClassificationEvaluator () .setLabelCol ("indexedLabel") .setPredictionCol ("prediction") .setMetricName ("precision")
*    val precision = evaluator.evaluate (predicciones)
println (s "Error de prueba = $ {(1.0 - precisión)}")

* val rfModel = model.stages (2). asInstanceOf [RandomForestClassificationModel]
println (s "Modelo de bosque de clasificación aprendida: \ n $ {rfModel.toDebugString}")


                Modelo de bosque de clasificación aprendido:
                RandomForestClassificationModel (uid = rfc_a746584832a2) con 10 árboles
                Árbol 0 (peso 1.0):
                    Si (característica 412 <= 8.0)
                        Si (característica 454 <= 12.5)
                            redict: 0.0
                    De lo contrario (función 454> 12.5)
                        Predecir: 1.0
                    De lo contrario (función 412> 8.0)
                    P redict: 1.0
                Árbol 1 (peso 1.0):
                    Si (función 463 <= 2.0)
                        Si (función 317 <= 8.0)
                            Si (función 216 <= 44.0)
                                Predecir: 0.0
                    De lo contrario (función 216> 44.0)
                        Predecir: 1.0
                    De lo contrario (función 317> 8.0)
                        Predecir: 1.0
                    De lo contrario (función 463> 2.0)
                        Predecir: 0.0
                Árbol 2 (peso 1.0):
                    Si (característica 540 <= 87.0)
                        Si (función 578 <= 9.0)
                            Predecir: 0.0
                    De lo contrario (función 578> 9.0)
                        Si (función 550 <= 170.0)
                        Predecir: 1.0
                    De lo contrario (función 550> 170.0)
                        Predecir: 0.0
                    De lo contrario (función 540> 87.0)
                        Predecir: 1.0
                Árbol 3 (peso 1.0):
                    Si (función 518 <= 21.0)
                        Si (función 601 <= 27.0)
                            Si (función 605 <= 4.0)
                                Predecir: 0.0
                    De lo contrario (función 605> 4.0)
                        Predecir: 1.0
                    De lo contrario (función 601> 27.0)
                        Predecir: 1.0
                    De lo contrario (función 518> 21.0)
                        Si (función 261 <= 1.0)
                            Predecir: 0.0
                    De lo contrario (función 261> 1.0)
                        Predecir: 1.0
                Árbol 4 (peso 1.0):
                    Si (función 429 <= 7.0)
                        Si (función 358 <= 10.5)
                            Predecir: 0.0
                    De lo contrario (función 358> 10.5)
                        Predecir: 1.0
                    De lo contrario (función 429> 7.0)
                        Predecir: 1.0
                Árbol 5 (peso 1.0):
                    Si (característica 462 <= 62.5)
                        Predecir: 1.0
                    De lo contrario (función 462> 62.5)
                        Predecir: 0.0
                Árbol 6 (peso 1.0):
                    Si (función 385 <= 4.0)
                        Si (característica 545 <= 3.0)
                        Si (función 600 <= 2.5)
                            Predecir: 0.0
                    De lo contrario (función 600> 2.5)
                        Predecir: 1.0
                    De lo contrario (función 545> 3.0)
                        Predecir: 0.0
                    De lo contrario (función 385> 4.0)
                        Predecir: 1.0
                Árbol 7 (peso 1.0):
                    Si (función 512 <= 1.5)
                        Si (función 510 <= 2.5)
                            Predecir: 0.0
                    De lo contrario (función 510> 2.5)
                        Predecir: 1.0
                    De lo contrario (función 512> 1.5)
                        Predecir: 1.0
                Árbol 8 (peso 1.0):
                    Si (característica 462 <= 62.5)
                        Predecir: 1.0
                    De lo contrario (función 462> 62.5)
                        Predecir: 0.0
                Árbol 9 (peso 1.0):
                    Si (función 301 <= 27.0)
                        Si (función 517 <= 22.5)
                            Si (característica 183 <= 3.0)
                                Predecir: 0.0
                    De lo contrario (función 183> 3.0)
                        Predecir: 1.0
                    De lo contrario (función 517> 22.5)
                        Predecir: 0.0
                    De lo contrario (función 301> 27.0)
                        Predecir: 1.0

// Ocegueda Meraz Armando # 14212337