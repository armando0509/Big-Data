  </b></br> Tema</b></br> 

</b></br> Clasificador Random Forest</b></br> 

</b></br>  Que es el algoritmo Clasificador Random Forest?</b></br> 

<br> Consiste en una gran cantidad de arboles de decision individual que operan como un conjunto. Cada arbol individual en el bosque aleatorio escupe una prediccion de clase y la clase con mas votos se convertira en la prediccion de nuestro modelo.</br> 

</b></br>  Como funciona:</b></br> 
</b></br> Importamos las bibliotecas y paquetes necesarios para cargar el programa.</b></br> 
*<br>  importar org.apache.spark.ml.Pipeline</br> 
* <br> import org.apache.spark.ml.classification. {RandomForestClassificationModel, RandomForestClassifier}</br> 
* <br> import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator</b></br> 
*<br> import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}</br> 
* <br> importar org.apache.spark.sql.SparkSession</br> 

</b></br> Creamos una instancia de la sesion de spark</b></br> 
* <br> val spark = SparkSession.builder.appName ("RandomForestClassifierExample"). </br> 
<br> getOrCreate ()</br> 

</b></br> Conjunto de datos de carga de nuestro </b></br> 

* <br> val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")</br> 
</b></br> Muestra de datos()</b></br> 

<br> indice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el indice. </br> 
<br> val labelIndexer = new StringIndexer () .setInputCol ("etiqueta") .setOutputCol ("indexedLabel") .fit (datos)</br> 

<br> Identifique automaticamente las caracteristicas categoricas y las indexa, Se estableceran las categorias maximas para las entidades con> 4 valores distintos se traten como continuas. </br> 
*<br>  val featureIndexer = new VectorIndexer () .setInputCol ("features") .setOutputCol ("indexedFeatures") .setMaxCategories (4) .fit (datos)</br> 

</b></br> Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:</b></br> 
 <br> division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento). </br> 
* <br> Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))</br> 

</b></br> Entrena un modelo RandomForest. </b></br> 
* <br> val rf = new RandomForestClassifier () .setLabelCol ("indexedLabel") .setFeaturesCol ("indexedFeatures") .setNumTrees (10)</br> 

</b></br> Convierte las etiquetas indexadas de nuevo a etiquetas originales. </b></br> 
* <br> val labelConverter = nuevo IndexToString () .setInputCol ("prediccion") .setOutputCol ("predicttedLabel") .setLabels (labelIndexer.labels)</br> 

</b></br> Indicadores de cadena y bosque en una tuberia. </b></br> 
* <br> val pipeline = new Pipeline () .setStages (Array (labelIndexer, featureIndexer, rf, labelConverter))</br> 

<br> Modelo de tren. Esto tambien ejecuta los indexadores.</br> 
* <br> modelo val = pipeline.fit (trainingData)</br> 

<br> Las tuberias proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las practicas de aprendizaje automatico.
MLlib estandariza las API para algoritmos de aprendizaje automatico para facilitar la combinacion de multiples algoritmos en una sola tuberia o flujo de trabajo. Esta seccion cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuberia se inspira principalmente en el proyecto scikit-learn.</br> 

<br> En el aprendizaje automatico, es comun ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:</br> 

* <br> Divide el texto de cada documento en palabras.</b></br> 
* <br> Convierta las palabras de cada documento en un vector de caracteristicas numericas.</br> 
* <br> Aprenda un modelo de prediccion utilizando los vectores de caracteristicas y las etiquetas.</br> 
</b></br> Como funciona</b></br> 
<br> A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () metodo se llama en DataFrame. Por Estimatoretapas, el fit () metodo se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () metodo se llama en el DataFrame.</br> 

</b></br> Hacer predicciones. </b></br> 
* <br> predicciones val = model.transform (testData)</br> 

</b></br> Seleccione filas de ejemplo para mostrar. </b></br> 
*<br>  predictions.select ("predicttedLabel", "label", "features") .show (5)</br> 

</b></br> Seleccione (prediccion, etiqueta verdadera) y calcule el error de prueba. </b></br> 
*<br>  val evaluator = new MulticlassClassificationEvaluator () .setLabelCol ("indexedLabel") .setPredictionCol ("prediction") .setMetricName ("precision")</br> 
*    <br> val precision = evaluator.evaluate (predicciones)</br> 
<br> println (s "Error de prueba = $ {(1.0 - precision)}")</br> 

* <br> val rfModel = model.stages (2). asInstanceOf [RandomForestClassificationModel]</br> 
<br> println (s "Modelo de bosque de clasificacion aprendida: \ n $ {rfModel.toDebugString}")</br> 


                <b> <br> Modelo de bosque de clasificacion aprendido:</br></br>  
               <br>  RandomForestClassificationModel (uid = rfc_a746584832a2) con 10 arboles</br> 
                <br> arbol 0 (peso 1.0):</br> 
                    <br> Si (caracteristica 412 <= 8.0)</br> 
                       <br>  Si (caracteristica 454 <= 12.5)</br> 
                          <br>   redict: 0.0</br> 
                    <br> De lo contrario (funcion 454> 12.5)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br> De lo contrario (funcion 412> 8.0)</br> 
                    <br> P redict: 1.0</br> 
               <br>  arbol 1 (peso 1.0):</br> 
                      <br>   Si (funcion 317 <= 8.0)</br> 
                           <br>  Si (funcion 216 <= 44.0)</br> 
                              <br>   Predecir: 0.0</br> 
                   <br>  De lo contrario (funcion 216> 44.0)</br> 
                        <br> Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 317> 8.0)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 463> 2.0)</br> 
                       <br>  Predecir: 0.0</br> 
                <br> arbol 2 (peso 1.0):</br> 
                   <br>  Si (caracteristica 540 <= 87.0)</br> 
                        <br> Si (funcion 578 <= 9.0)</br> 
                          <br>   Predecir: 0.0</br> 
                    <br> De lo contrario (funcion 578> 9.0)</br> 
                       <br>  Si (funcion 550 <= 170.0)</br> 
                       <br>  Predecir: 1.0</br> 
                    <br> De lo contrario (funcion 550> 170.0)</br> 
                       <br>  Predecir: 0.0</br> 
                   <br>  De lo contrario (funcion 540> 87.0)</br> 
                       <br>  Predecir: 1.0</br> 
                <br> arbol 3 (peso 1.0):</br> 
                    <br> Si (funcion 518 <= 21.0)</br> 
                       <br>  Si (funcion 601 <= 27.0)</br> 
                        <br>     Si (funcion 605 <= 4.0)</br> 
                           <br>      Predecir: 0.0</br> 
                   <br>  De lo contrario (funcion 605> 4.0)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 601> 27.0)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 518> 21.0)</br> 
                       <br>  Si (funcion 261 <= 1.0)</br> 
                         <br>    Predecir: 0.0</br> 
                    <br> De lo contrario (funcion 261> 1.0)</br> 
                        <br> Predecir: 1.0</br> 
               <br>  arbol 4 (peso 1.0):</br> 
                   <br>  Si (funcion 429 <= 7.0)</br> 
                       <br>  Si (funcion 358 <= 10.5)</br> 
                         <br>    Predecir: 0.0</br> 
                    <br> De lo contrario (funcion 358> 10.5)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 429> 7.0)</br> 
                       <br>  Predecir: 1.0</br> 
               <br>  arbol 5 (peso 1.0):</br> 
                   <br>  Si (caracteristica 462 <= 62.5)</br> 
                    <br>     Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 462> 62.5)</br> 
                     <br>    Predecir: 0.0</br> 
               <br>  arbol 6 (peso 1.0):</br> 
                   <br>  Si (funcion 385 <= 4.0)</br> 
                     <br>    Si (caracteristica 545 <= 3.0)</br> 
                      <br>   Si (funcion 600 <= 2.5)</br> 
                      <br>       Predecir: 0.0</br> 
                   <br>  De lo contrario (funcion 600> 2.5)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 545> 3.0)</br> 
                       <br>  Predecir: 0.0</br> 
                   <br>  De lo contrario (funcion 385> 4.0)</br> 
                       <br>  Predecir: 1.0</br> 
               <br>  arbol 7 (peso 1.0):</br> 
                    <br> Si (funcion 512 <= 1.5)</br> 
                       <br>  Si (funcion 510 <= 2.5)</br> 
                        <br>     Predecir: 0.0</br> 
                   <br>  De lo contrario (funcion 510> 2.5)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 512> 1.5)</br> 
                       <br>  Predecir: 1.0</br> 
                <br> arbol 8 (peso 1.0):</br> 
                    <br> Si (caracteristica 462 <= 62.5)</br> 
                       <br>  Predecir: 1.0</br> 
                   <br>  De lo contrario (funcion 462> 62.5)</br> 
                      <br>   Predecir: 0.0</br> 
                <br> arbol 9 (peso 1.0):</br> 
                   <br>  Si (funcion 301 <= 27.0)</br> 
                     <br>    Si (funcion 517 <= 22.5)</br> 
                        <br>     Si (caracteristica 183 <= 3.0)</br> 
                           <br>      Predecir: 0.0</br> 
                    <br> De lo contrario (funcion 183> 3.0)</br> 
                       <br>  Predecir: 1.0</br> 
                    <br> De lo contrario (funcion 517> 22.5)</br> 
                       <br>  Predecir: 0.0</br> 
                   <br>  De lo contrario (funcion 301> 27.0)</br> 
                       <br>  Predecir: 1.0</br> 

<b><br> Ocegueda Meraz Armando # 14212337 </br></br>

<b><br> Tema:  Clasificador Random Forest</br></br>