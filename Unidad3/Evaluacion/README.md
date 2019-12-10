


**Objetivo**: El objetivo de este examen es tratar de agrupar los clientes de regiones específicas de un distribuidor al mayoreo. Esto en base a las ventas de algunas categorías de productos. Las fuente de datos se encuentra en el repositorio: <https://github.com/jcromerohdz/BigData/blob/master/Spark_clustering/Whole sale customersdata.csv



<b><br> KMEANS</b></br>  

<b><br> &bull; Como funciona:</b></br>

 <br>  El algoritmo K-means es el algoritmo de clustering más conocido y utilizado ya que es de muy simple aplicaci´on y eficaz. Sigue un procedimiento simple de clasificacion de un conjunto de objetos en un determinado numero K de clusters, K determinado a priori.</br>  

<br> El nombre de K-means viene porque representa cada uno de los clusters por la
media (o media ponderada) de sus puntos, es decir, por su centroide. La
representación mediante centroides tiene la ventaja de que tiene un significado gráfico y estad´ıstico inmediato.Cada cluster por tanto es caracterizado por su centro o centroide que se encuentra en el centro o el medio de los elementos que componen el cluster. K Means es traducido como K-medias.</br> 

<br> O un conjunto de objetos Dn = (x1, x2..., xn), para todo el i, xi reales y k, ν1, los centros de los K cluster. El algoritmo del K-means se realiza en 4 etapas: </br>


<b>&bull; Etapa 1:</b> 
 <br>Elegir aleatoriamente K objetos que forman así los K clusters iniciales. Para cada cluster k, el valor inicial del centro es = xi, con los xi únicos objetos de Dn pertenecientes al cluster.</br>

<b>&bull;  Etapa 2: </b> 
<br>Reasigna los objetos del cluster. Para cada objeto x, el prototipo que se le asigna es el que es m´as pr´oximo al objeto, según una medida de distancia, (habitualmente la medida euclidiana).</br>

<b>&bull;Etapa 3: </b> 
 <br>Una vez que todos los objetos son colocados, recalcular los centros de K cluster. (los baricentros).</br>

<b>&bull;Etapa 4:</b> 
 <br>Repetir las etapas 2 y 3 hasta que no se hagan más reasignaciones.
Aunque el algoritmo termina siempre, no se garantiza el obtener la soluci´on
´optima. En efecto, el algoritmo es muy sensible a la elección aleatoria de los K centros iniciales. Esta es la razón por la que, se utiliza el algoritmo del K-means numerosas veces sobre un mismo conjunto de datos para intentar minimizar este efecto, sabiendo que a centros iniciales lo más espaciados posibles dan mejores resultados. </br>


1. **Importar una simple sesión Spark.**

import org.apache.spark.sql.SparkSession

2. **Utilice las lineas de código para minimizar errores**

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

3. **Cree una instancia de la sesión Spark**
val spark = SparkSession.builder().getOrCreate()


4. **Importar la librería de Kmeans para el algoritmo de agrupamiento.**

import org.apache.spark.ml.clustering.KMeans


5. **Carga el dataset de Wholesale Customers Data**

val dataset = spark.read.option("header","true").option("inferSchema","true").csv("/home/armando/Escritorio/Datos Masivos/Unidad3/Evaluacion/Wholesalecustomersdata.csv")

6. **Seleccione las siguientes columnas:** 

Fres, Milk, Grocery, Frozen, Detergents_Paper, Delicassen y llamar a este conjunto feature_datamval feature_data = dataset.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen")

-dataset.printSchema

7. **Importar Vector Assembler y Vector**

import org.apache.spark.ml.feature.{VectorAssembler,StringIndexer,VectorIndexer,OneHotEncoder} import org.apache.spark.ml.linalg.Vectors

8. **Crea un nuevo objeto Vector Assembler para las columnas de caracteristicas como un conjunto de entrada, recordando que no hay etiquetas**

val assembler = new VectorAssembler().setInputCols(Array("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features")

9. **Utilice el objeto assembler para transformar feature_data**

val training_data = assembler.transform(feature_data).select("features")

10. **Crear un modelo Kmeans con K=3**

val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(training_data)

11. **Evalúe los grupos utilizando**

val WSSSE = model.computeCost(training_data)
println("Within Set Sum of Squared Errors = WSSSE")

12. **¿Cuáles son los nombres de las columnas?**

println("Cluster Centers: ")
model.clusterCenters.foreach(println)


println("Channel,Region,Fresh,Milk,Grocery,Frozen,Detergents_Paper,Delicassen") 


<b><br>  Ocegueda Meraz Armando # 14212337     KMeans </b> </br>  
