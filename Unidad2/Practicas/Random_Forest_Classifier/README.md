// Tema

//  Clasificador Random Forest

// Que es el algoritmo Clasificador Random Forest?

Consiste en una gran cantidad de arboles de decision individual que operan como un conjunto. Cada arbol individual en el bosque aleatorio escupe una prediccion de clase y la clase con mas votos se convertira en la prediccion de nuestro modelo.

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

indice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el indice. 
val labelIndexer = new StringIndexer () .setInputCol ("etiqueta") .setOutputCol ("indexedLabel") .fit (datos)

Identifique automaticamente las caracteristicas categoricas y las indexa, Se estableceran las categorias maximas para las entidades con> 4 valores distintos se traten como continuas. 
* val featureIndexer = new VectorIndexer () .setInputCol ("features") .setOutputCol ("indexedFeatures") .setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento). 
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrena un modelo RandomForest. 
* val rf = new RandomForestClassifier () .setLabelCol ("indexedLabel") .setFeaturesCol ("indexedFeatures") .setNumTrees (10)

Convierte las etiquetas indexadas de nuevo a etiquetas originales. 
* val labelConverter = nuevo IndexToString () .setInputCol ("prediccion") .setOutputCol ("predicttedLabel") .setLabels (labelIndexer.labels)

Indicadores de cadena y bosque en una tuberia. 
* val pipeline = new Pipeline () .setStages (Array (labelIndexer, featureIndexer, rf, labelConverter))

Modelo de tren. Esto tambien ejecuta los indexadores.
* modelo val = pipeline.fit (trainingData)

Las tuberias proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las practicas de aprendizaje automatico.
MLlib estandariza las API para algoritmos de aprendizaje automatico para facilitar la combinacion de multiples algoritmos en una sola tuberia o flujo de trabajo. Esta seccion cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuberia se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje automatico, es comun ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de caracteristicas numericas.
* Aprenda un modelo de prediccion utilizando los vectores de caracteristicas y las etiquetas.
Como funciona
A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () metodo se llama en DataFrame. Por Estimatoretapas, el fit () metodo se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () metodo se llama en el DataFrame.

Hacer predicciones. 
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar. 
* predictions.select ("predicttedLabel", "label", "features") .show (5)

Seleccione (prediccion, etiqueta verdadera) y calcule el error de prueba. 
* val evaluator = new MulticlassClassificationEvaluator () .setLabelCol ("indexedLabel") .setPredictionCol ("prediction") .setMetricName ("precision")
*    val precision = evaluator.evaluate (predicciones)
println (s "Error de prueba = $ {(1.0 - precision)}")

* val rfModel = model.stages (2). asInstanceOf [RandomForestClassificationModel]
println (s "Modelo de bosque de clasificacion aprendida: \ n $ {rfModel.toDebugString}")


                Modelo de bosque de clasificacion aprendido:
                RandomForestClassificationModel (uid = rfc_a746584832a2) con 10 arboles
                arbol 0 (peso 1.0):
                    Si (caracteristica 412 <= 8.0)
                        Si (caracteristica 454 <= 12.5)
                            redict: 0.0
                    De lo contrario (funcion 454> 12.5)
                        Predecir: 1.0
                    De lo contrario (funcion 412> 8.0)
                    P redict: 1.0
                arbol 1 (peso 1.0):
                    Si (funcion 463 <= 2.0)
                        Si (funcion 317 <= 8.0)
                            Si (funcion 216 <= 44.0)
                                Predecir: 0.0
                    De lo contrario (funcion 216> 44.0)
                        Predecir: 1.0
                    De lo contrario (funcion 317> 8.0)
                        Predecir: 1.0
                    De lo contrario (funcion 463> 2.0)
                        Predecir: 0.0
                arbol 2 (peso 1.0):
                    Si (caracteristica 540 <= 87.0)
                        Si (funcion 578 <= 9.0)
                            Predecir: 0.0
                    De lo contrario (funcion 578> 9.0)
                        Si (funcion 550 <= 170.0)
                        Predecir: 1.0
                    De lo contrario (funcion 550> 170.0)
                        Predecir: 0.0
                    De lo contrario (funcion 540> 87.0)
                        Predecir: 1.0
                arbol 3 (peso 1.0):
                    Si (funcion 518 <= 21.0)
                        Si (funcion 601 <= 27.0)
                            Si (funcion 605 <= 4.0)
                                Predecir: 0.0
                    De lo contrario (funcion 605> 4.0)
                        Predecir: 1.0
                    De lo contrario (funcion 601> 27.0)
                        Predecir: 1.0
                    De lo contrario (funcion 518> 21.0)
                        Si (funcion 261 <= 1.0)
                            Predecir: 0.0
                    De lo contrario (funcion 261> 1.0)
                        Predecir: 1.0
                arbol 4 (peso 1.0):
                    Si (funcion 429 <= 7.0)
                        Si (funcion 358 <= 10.5)
                            Predecir: 0.0
                    De lo contrario (funcion 358> 10.5)
                        Predecir: 1.0
                    De lo contrario (funcion 429> 7.0)
                        Predecir: 1.0
                arbol 5 (peso 1.0):
                    Si (caracteristica 462 <= 62.5)
                        Predecir: 1.0
                    De lo contrario (funcion 462> 62.5)
                        Predecir: 0.0
                arbol 6 (peso 1.0):
                    Si (funcion 385 <= 4.0)
                        Si (caracteristica 545 <= 3.0)
                        Si (funcion 600 <= 2.5)
                            Predecir: 0.0
                    De lo contrario (funcion 600> 2.5)
                        Predecir: 1.0
                    De lo contrario (funcion 545> 3.0)
                        Predecir: 0.0
                    De lo contrario (funcion 385> 4.0)
                        Predecir: 1.0
                arbol 7 (peso 1.0):
                    Si (funcion 512 <= 1.5)
                        Si (funcion 510 <= 2.5)
                            Predecir: 0.0
                    De lo contrario (funcion 510> 2.5)
                        Predecir: 1.0
                    De lo contrario (funcion 512> 1.5)
                        Predecir: 1.0
                arbol 8 (peso 1.0):
                    Si (caracteristica 462 <= 62.5)
                        Predecir: 1.0
                    De lo contrario (funcion 462> 62.5)
                        Predecir: 0.0
                arbol 9 (peso 1.0):
                    Si (funcion 301 <= 27.0)
                        Si (funcion 517 <= 22.5)
                            Si (caracteristica 183 <= 3.0)
                                Predecir: 0.0
                    De lo contrario (funcion 183> 3.0)
                        Predecir: 1.0
                    De lo contrario (funcion 517> 22.5)
                        Predecir: 0.0
                    De lo contrario (funcion 301> 27.0)
                        Predecir: 1.0

// Ocegueda Meraz Armando # 14212337

// Tema:  Clasificador Random Forest