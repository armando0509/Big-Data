// Tema

//  Clasificador Random Forest

// Que es el algoritmo Clasificador Random Forest?

Consiste en una gran cantidad de �rboles de decisi�n individual que operan como un conjunto. Cada �rbol individual en el bosque aleatorio escupe una predicci�n de clase y la clase con m�s votos se convertir� en la predicci�n de nuestro modelo.

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

�ndice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el �ndice. 
val labelIndexer = new StringIndexer () .setInputCol ("etiqueta") .setOutputCol ("indexedLabel") .fit (datos)

Identifique autom�ticamente las caracter�sticas categ�ricas y las indexa, Se establecer�n las categor�as m�ximas para las entidades con> 4 valores distintos se traten como continuas. 
* val featureIndexer = new VectorIndexer () .setInputCol ("features") .setOutputCol ("indexedFeatures") .setMaxCategories (4) .fit (datos)

Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:
 divisi�n de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento). 
* Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))

Entrena un modelo RandomForest. 
* val rf = new RandomForestClassifier () .setLabelCol ("indexedLabel") .setFeaturesCol ("indexedFeatures") .setNumTrees (10)

Convierte las etiquetas indexadas de nuevo a etiquetas originales. 
* val labelConverter = nuevo IndexToString () .setInputCol ("predicci�n") .setOutputCol ("predicttedLabel") .setLabels (labelIndexer.labels)

Indicadores de cadena y bosque en una tuber�a. 
* val pipeline = new Pipeline () .setStages (Array (labelIndexer, featureIndexer, rf, labelConverter))

Modelo de tren. Esto tambi�n ejecuta los indexadores.
* modelo val = pipeline.fit (trainingData)

Las tuber�as proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las pr�cticas de aprendizaje autom�tico.
MLlib estandariza las API para algoritmos de aprendizaje autom�tico para facilitar la combinaci�n de m�ltiples algoritmos en una sola tuber�a o flujo de trabajo. Esta secci�n cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuber�a se inspira principalmente en el proyecto scikit-learn.

En el aprendizaje autom�tico, es com�n ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:

* Divide el texto de cada documento en palabras.
* Convierta las palabras de cada documento en un vector de caracter�sticas num�ricas.
* Aprenda un modelo de predicci�n utilizando los vectores de caracter�sticas y las etiquetas.
C�mo funciona
A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () m�todo se llama en DataFrame. Por Estimatoretapas, el fit () m�todo se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () m�todo se llama en el DataFrame.

Hacer predicciones. 
* predicciones val = model.transform (testData)

Seleccione filas de ejemplo para mostrar. 
* predictions.select ("predicttedLabel", "label", "features") .show (5)

Seleccione (predicci�n, etiqueta verdadera) y calcule el error de prueba. 
* val evaluator = new MulticlassClassificationEvaluator () .setLabelCol ("indexedLabel") .setPredictionCol ("prediction") .setMetricName ("precision")
*    val precision = evaluator.evaluate (predicciones)
println (s "Error de prueba = $ {(1.0 - precisi�n)}")

* val rfModel = model.stages (2). asInstanceOf [RandomForestClassificationModel]
println (s "Modelo de bosque de clasificaci�n aprendida: \ n $ {rfModel.toDebugString}")


                Modelo de bosque de clasificaci�n aprendido:
                RandomForestClassificationModel (uid = rfc_a746584832a2) con 10 �rboles
                �rbol 0 (peso 1.0):
                    Si (caracter�stica 412 <= 8.0)
                        Si (caracter�stica 454 <= 12.5)
                            redict: 0.0
                    De lo contrario (funci�n 454> 12.5)
                        Predecir: 1.0
                    De lo contrario (funci�n 412> 8.0)
                    P redict: 1.0
                �rbol 1 (peso 1.0):
                    Si (funci�n 463 <= 2.0)
                        Si (funci�n 317 <= 8.0)
                            Si (funci�n 216 <= 44.0)
                                Predecir: 0.0
                    De lo contrario (funci�n 216> 44.0)
                        Predecir: 1.0
                    De lo contrario (funci�n 317> 8.0)
                        Predecir: 1.0
                    De lo contrario (funci�n 463> 2.0)
                        Predecir: 0.0
                �rbol 2 (peso 1.0):
                    Si (caracter�stica 540 <= 87.0)
                        Si (funci�n 578 <= 9.0)
                            Predecir: 0.0
                    De lo contrario (funci�n 578> 9.0)
                        Si (funci�n 550 <= 170.0)
                        Predecir: 1.0
                    De lo contrario (funci�n 550> 170.0)
                        Predecir: 0.0
                    De lo contrario (funci�n 540> 87.0)
                        Predecir: 1.0
                �rbol 3 (peso 1.0):
                    Si (funci�n 518 <= 21.0)
                        Si (funci�n 601 <= 27.0)
                            Si (funci�n 605 <= 4.0)
                                Predecir: 0.0
                    De lo contrario (funci�n 605> 4.0)
                        Predecir: 1.0
                    De lo contrario (funci�n 601> 27.0)
                        Predecir: 1.0
                    De lo contrario (funci�n 518> 21.0)
                        Si (funci�n 261 <= 1.0)
                            Predecir: 0.0
                    De lo contrario (funci�n 261> 1.0)
                        Predecir: 1.0
                �rbol 4 (peso 1.0):
                    Si (funci�n 429 <= 7.0)
                        Si (funci�n 358 <= 10.5)
                            Predecir: 0.0
                    De lo contrario (funci�n 358> 10.5)
                        Predecir: 1.0
                    De lo contrario (funci�n 429> 7.0)
                        Predecir: 1.0
                �rbol 5 (peso 1.0):
                    Si (caracter�stica 462 <= 62.5)
                        Predecir: 1.0
                    De lo contrario (funci�n 462> 62.5)
                        Predecir: 0.0
                �rbol 6 (peso 1.0):
                    Si (funci�n 385 <= 4.0)
                        Si (caracter�stica 545 <= 3.0)
                        Si (funci�n 600 <= 2.5)
                            Predecir: 0.0
                    De lo contrario (funci�n 600> 2.5)
                        Predecir: 1.0
                    De lo contrario (funci�n 545> 3.0)
                        Predecir: 0.0
                    De lo contrario (funci�n 385> 4.0)
                        Predecir: 1.0
                �rbol 7 (peso 1.0):
                    Si (funci�n 512 <= 1.5)
                        Si (funci�n 510 <= 2.5)
                            Predecir: 0.0
                    De lo contrario (funci�n 510> 2.5)
                        Predecir: 1.0
                    De lo contrario (funci�n 512> 1.5)
                        Predecir: 1.0
                �rbol 8 (peso 1.0):
                    Si (caracter�stica 462 <= 62.5)
                        Predecir: 1.0
                    De lo contrario (funci�n 462> 62.5)
                        Predecir: 0.0
                �rbol 9 (peso 1.0):
                    Si (funci�n 301 <= 27.0)
                        Si (funci�n 517 <= 22.5)
                            Si (caracter�stica 183 <= 3.0)
                                Predecir: 0.0
                    De lo contrario (funci�n 183> 3.0)
                        Predecir: 1.0
                    De lo contrario (funci�n 517> 22.5)
                        Predecir: 0.0
                    De lo contrario (funci�n 301> 27.0)
                        Predecir: 1.0

// Ocegueda Meraz Armando # 14212337