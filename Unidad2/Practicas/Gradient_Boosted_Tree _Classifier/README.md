<br>Ocegueda Meraz Armando # 14212337</br>

<b><br>  Arbol impulsado por gradiente</b></br> 
<b><br>  Que es el algoritmo Clasificador de arbol impulsado por gradiente?</b></br> 

<br> Los arboles impulsados ??por gradientes (GBT) son un metodo popular de clasificacion y regresion que utiliza conjuntos de arboles de decision. spark.ml</br> 

<b><br> Como funciona:</b></br> 
<br> &bull;Importamos las bibliotecas y paquetes necesarios para cargar el programa.</br> 
*<br>&bull;  importar org.apache.spark.ml.Pipeline</b></br> 
*<br> &bull;import org.apache.spark.ml.classification. {GBTClassificationModel, GBTClassifier}</b></br> 
*<br> &bull; import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator</b></br> 
* <br> &bull;import org.apache.spark.ml.feature. {IndexToString, StringIndexer, VectorIndexer}</b></br> 
*<br>  &bull;importar org.apache.spark.sql.SparkSession</br> 

<br> Creamos una instancia de la sesion de spark</br> 
* <br> val spark = SparkSession.builder.appName ("GradientBoostedTreeClassifierExample"). getOrCreate ()</br> 

<br> cargan un conjunto de datos en formato LibSVM, lo dividen en conjuntos de entrenamiento y prueba, entrenan en el primer conjunto de datos y luego evaluan en el conjunto de prueba extendido. Necesite dos transformadores de caracteristicas para preparar los datos; Estas categorias de indice de ayuda para la etiqueta y las caracteristicas categoricas, aceptan metadatos a los DataFrameque el algoritmo del arbol de decision puede reconocer.</br> 

* <br> val data = spark.read.format ("libsvm"). load ("sample_libsvm_data.txt")</br> 

<br> Creacion del indexador de etiquetas El indice de etiquetas, agregando metadatos a la columna de etiquetas y Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el indice.</br> 
*   <br>   val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)</br> 

<br> Identifique automaticamente caracteristicas categoricas e indicacelas y Establezca maxCategorias para las entidades con> 4 valores distintos se traten como continuas.</br> 
*<br>  val featureIndexer = new VectorIndexer (). setInputCol ("features"). setOutputCol ("indexedFeatures"). setMaxCategories (4) .fit (datos)</br> 

<br>&bull; Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente mandera:</br> 
 <br> division de datos en conjuntos de entrenamiento y prueba por medio de un arreglo (30% para pruebas y 70% de entrenamiento).</br> 
*<br>  Val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3))</br> 

<br> Entrenamos el modelo de clasificador de arbol impulsado por gradiente el cual contendra las etiquetas del indice y las caracteristicas indexadas.</br> 
* <br> val gbt = new GBTClassifier (). setLabelCol ("indexedLabel"). setFeaturesCol ("indexedFeatures"). setMaxIter (10) .setFeatureSubsetStrategy ("auto")</br> 

<br> Se crea la conversion de las etiquetas indexadas a etiquetas originales.</br> 
* <br> val labelConverter = new IndexToString (). setInputCol ("prediction"). setOutputCol ("predicttedLabel"). setLabels (labelIndexer.labels)</br> 

<br> &bull;  Cadena de indexadores y arbol en una tuberia.</br> 
*<br>  val pipeline = new Pipeline (). setStages (Array (labelIndexer, featureIndexer, gbt, labelConverter))</br> 

<br> Las tuberias proporcionan un conjunto uniforme de API de alto nivel creado sobre DataFrames que ayudan a los usuarios a crear y ajustar las practicas de aprendizaje automatico.
MLlib estandariza las API para algoritmos de aprendizaje automatico para facilitar la combinacion de multiples algoritmos en una sola tuberia o flujo de trabajo. Esta seccion cubre los conceptos clave introducidos por la API de Pipelines, donde el concepto de tuberia se inspira principalmente en el proyecto scikit-learn.</br> 

<br> En el aprendizaje automatico, es comun ejecutar una secuencia de algoritmos para procesar y aprender de los datos. Por ejemplo, un flujo de trabajo de procesamiento de documentos de texto simple puede incluir varias etapas:</br> 

* <br> &bull;Divide el texto de cada documento en palabras.</br> 
* <br> &bull;Convierta las palabras de cada documento en un vector de caracteristicas numericas.</br> 
* <br> &bull;Aprenda un modelo de prediccion utilizando los vectores de caracteristicas y las etiquetas.</br> 
<b><br> &bull;Como funciona</b></br> 
<br> A Pipelinese especifica como una secuencia de etapas, y cada etapa es una Transformero una Estimator. Estas etapas se ejecutan en orden, y la entrada DataFramese transforma a medida que pasa por cada etapa. Para Transformeretapas, el transform () metodo se llama en DataFrame. Por Estimatoretapas, el fit () metodo se llama para producir una Transformer (que se convierte en parte de PipelineModel, o equipada Pipeline), y que Transformer's transform () metodo se llama en el DataFrame.<br> 

<br> &bull; Modelo de entrenamiento Esto tambien realiza los indexadores.</br> 
* <br> modelo val = pipeline.fit (trainingData)</br> 

<br> &bull;Generamos la prediccion con la siguiente variable donde se transforma la prueba de los datos</br> 
* <br> predicciones val = model.transform (testData)</br> 

<br> &bull;Seleccione filas de ejemplo para mostrar</br> 
*<br>  predictions.select ("predicttedLabel", "label", "features"). show (5)</br> 

<br> creacion del evaluador de clasificacion que contendra las etiquetas indexadas la prediccion, Seleccionar (prediccion, etiqueta verdadera) y calcular error de prueba.</br> 
* <br> val evaluator = new MulticlassClassificationEvaluator (). setLabelCol ("indexedLabel"). </br> 
<br> setPredictionCol ("prediction"). setMetricName ("precision")</br> 
* <br> val precision = evaluator.evaluate (predicciones) -----> nombre de la metrica de precision</br> 
*<br>  println (s "Error de prueba = $ {(1.0 - precision)}") -----------> prueba del error en este algoritmo entre mas cerca de este mas de 1 precision.</br> 

* <br> val gbtModel = model.stages (2) .asInstanceOf [GBTClassificationModel]</br> 
* <br> println (s "Modelo GBT de clasificacion aprendida: \ n $ {gbtModel.toDebugString}")</br> 
 <b><br>  Modelo de arbol de clasificacion aprendido:</b></br> 
         <br> GBTClassificationModel (uid = gbtc_59f66cfb58b0) con 10 arboles</br> 
        <br> arbol 0 (peso 1.0):</br> 
            <br> Si (caracteristica 406 <= 12.0)</br> 
                <br> Predecir: 1.0</br> 
           <br>  De lo contrario (funcion 406> 12.0)</br> 
                <br> Predecir: -1.0</br> 
        <br> arbol 1 (peso 0.1):</br> 
            <br> Si (caracteristica 406 <= 12.0)</br> 
                <br> Si (funcion 154 <= 165.5)</br> 
                  <br>   Predecir: 0.4768116880884702</br> 
            <br> De lo contrario (funcion 154> 165.5)</br> 
               <br>  Predecir: 0.47681168808847024</br> 
            <br> De lo contrario (funcion 406> 12.0)</br> 
                <br> Predecir: -0.4768116880884702</br> 
        <br> arbol 2 (peso 0.1):</br> 
           <br>  Si (caracteristica 406 <= 12.0)</br> 
                <br> Si (funcion 241 <= 91.0)</br> 
                   <br>  Predecir: 0.43819358104272055</br> 
           <br>  De lo contrario (funcion 241> 91.0)</br> 
                <br> Predecir: 0.43819358104272066</br> 
            <br> De lo contrario (funcion 406> 12.0)</br> 
               <br>  Si (funcion 350 <= 82.5)</br> 
                <br> Predecir: -0.4381935810427206</br> 
            <br> De lo contrario (funcion 350> 82.5)</br> 
               <br>  Predecir: -0.43819358104272066</br> 
        <br> arbol 3 (peso 0.1):</br> 
           <br>  Si (caracteristica 490 <= 43.0)</br> 
              <br>   Si (funcion 512 <= 39.0)</br> 
                   <br>  Si (caracteristica 153 <= 8.5)</br> 
                      <br>   Predecir: 0.4051496802845983</br> 
            <br> De lo contrario (funcion 153> 8.5)</br> 
               <br>  Predecir: 0.4051496802845984</br> 
            <br> De lo contrario (funcion 512> 39.0)</br> 
                <br> Si (funcion 512 <= 210.0)</br> 
                  <br>   Si (funcion 208 <= 241.0)</br> 
                       <br>  Si (funcion 124 <= 9.5)</br> 
                       <br>  Predecir: 0.4051496802845983</br> 
                <br> De lo contrario (funcion 124> 9.5)</br> 
                   <br>  Predecir: 0.4051496802845984</br> 
            <br> De lo contrario (funcion 208> 241.0)</br> 
                <br> Si (funcion 152 <= 2.5)</br> 
                   <br>  Predecir: 0.4051496802845983</br> 
               <br>  De lo contrario (funcion 152> 2.5)</br> 
                   <br>  Predecir: 0.40514968028459836</br> 
            <br> De lo contrario (funcion 512> 210.0)</br> 
               <br>  Predecir: 0.4051496802845984</br> 
            <br> De lo contrario (funcion 490> 43.0)</br> 
              <br>   Predecir: -0.40514968028459825</br> 
        <br> arbol 4 (peso 0.1):</br> 
           <br>  Si (caracteristica 490 <= 43.0)</br> 
              <br>   Si (funcion 267 <= 48.5)</br> 
                  <br>   Si (caracteristica 238 <= 17.5)</br> 
                   <br>  Predecir: 0.3765841318352991</br> 
           <br>  De lo contrario (funcion 238> 17.5)</br> 
                <br> Predecir: 0.37658413183529926</br> 
            <br> De lo contrario (funcion 267> 48.5)</br> 
               <br>  Predecir: 0.3765841318352994</br> 
            <br> De lo contrario (funcion 490> 43.0)</br> 
               <br>  Predecir: -0.3765841318352992</br> 
       <br>  arbol 5 (peso 0.1):</br> 
               <br>  Si (caracteristica 406 <= 12.0)</br> 
                   <br>  Si (funcion 272 <= 3.0)</br> 
                        <br> Predecir: 0.35166478958101005</br> 
            <br> De lo contrario (funcion 272> 3.0)</br> 
                <br> Predecir: 0.3516647895810101</br> 
            <br> De lo contrario (funcion 406> 12.0)</br> 
               <br>  Predecir: -0.35166478958101005</br> 
        <br> arbol 6 (peso 0.1):</br> 
            <br> Si (caracteristica 462 <= 62.5)</br> 
               <br>  Si (caracteristica 241 <= 27.5)</br> 
                 <br>   Predecir: 0.32974984655529926</br> 
            <br> De lo contrario (funcion 241> 27.5)</br> 
                </r> Predecir: 0.3297498465552993</br> 
            <br> De lo contrario (funcion 462> 62.5)</br> 
               <br>  Si (funcion 267 <= 82.0)</br> 
                  <br>   Predecir: -0.32974984655529926</br> 
            <br> De lo contrario (funcion 267> 82.0)</br> 
               <br>  Predecir: -0.3297498465552993</br> 
        <br> arbol 7 (peso 0.1):</br> 
            <br> Si (caracteristica 406 <= 12.0)</br> 
               <br>  Si (funcion 272 <= 80.0)</br> 
                   <br>  Predecir: 0.31033724551979563</br> 
            <br> De lo contrario (funcion 272> 80.0)</br> 
                    <br> Predecir: 0.3103372455197957</br> 
            <br> De lo contrario (funcion 406> 12.0)</br> 
                <br> Si (funcion 239 <= ??28.0)</br> 
                  <br>   f (caracteristica 377 <= 237.5)</br> 
                    <br>     Predecir: -0.3103372455197956</br> 
            <br> De lo contrario (funcion 377> 237.5)</br> 
               <br>  Predecir: -0.3103372455197957</br> 
            <br> De lo contrario (funcion 239> 28.0)</br> 
                <br> Predecir: -0.3103372455197957</br> 
        <br> arbol 8 (peso 0.1):</br> 
           <br>  Si (caracteristica 406 <= 12.0)</br> 
               <br>  Si (caracteristica 183 <= 163.0)</br> 
                  <br>   Predecir: 0.2930291649125433</br> 
            <br> De lo contrario (funcion 183> 163.0)</br> 
                <br> Predecir: 0.2930291649125434</br> 
            <br> De lo contrario (funcion 406> 12.0)</br> 
               <br>  Si (funcion 351 <= 102.0)</br> 
                <br>     Predecir: -0.2930291649125433</br> 
           <br>  De lo contrario (funcion 351> 102.0)</br> 
               <br>  Predecir: -0.2930291649125434</br> 
        <br> arbol 9 (peso 0.1):</br> 
           <br>  Si (caracteristica 406 <= 12.0)</br> 
              <br>   Si (funcion 127 <= 63.5)</br> 
                   <br>  Si (caracteristica 241 <= 27.5)</br> 
                   <br>      Predecir: 0.27750666438358246</br> 
            <br> De lo contrario (funcion 241> 27.5)</br> 
               <br>  Predecir: 0.2775066643835825</br> 
           <br>  De lo contrario (funcion 127> 63.5)</br> 
               <br>  Predecir: 0.27750666438358257</br> 
           <br> De lo contrario (funcion 406> 12.0)</br> 
              <br>   Si (funcion 266 <= 42.5)</br> 
                  <br>   Predecir: -0.2775066643835825</br> 
            <br> De lo contrario (funcion 266> 42.5)</br> 
                <br> Si (caracteristica 433 <= 173.5)</br> 
                  <br>   Si (funcion 155 <= 1.0)</br> 
                       <br>  Predecir: -0.27750666438358246</br> 
           <br>  De lo contrario (funcion 155> 1.0)</br> 
               <br>  Predecir: -0.2775066643835825</br> 
           <br>  De lo contrario (caracteristica 433> 173.5)</br> 
               <br>  Predecir: -0.27750666438358257 </br> 
