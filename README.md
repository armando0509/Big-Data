**INSTITUTO TECNOLÓGICO DE TIJUANA**
**SUBDIRECCIÓN ACADÉMICA**

DEPARTAMENTO DE SISTEMAS Y COMPUTACIÓN
SEMESTRE AGOSTO- DICIEMBRE 2019

**CARRERA:**

INGENIERÍA INFORMÁTICA

**MATERIA Y SERIE:**

**DATOS MASIVOS** BDD-1704 IF9A

**UNIDAD POR EVALUAR:**
Proyecto Final


**NOMBRE DEL ALUMNO // NO. DE CONTROL**
Ocegueda Meraz Armando # 14212337


**NOMBRE DEL MAESTRO:**
Dr. JOSE CHRISTIAN ROMERO HERNANDEZ



####Índice	2
####Introducción	3
####Marco teórico de los algoritmos	4
####Kmeans	4
####Decision Tree	10
####Multilayer Perceptron	19
####Implementación	27
####Por qué programar spark con scala?	28
####KMeans	30
####Multilayer Perceptron	31
####Decision Tree	32
####Resultados	33
####KMeans	33
####Multilayer Perceptron	34
####Decision Tree	34
####Conclusiones	35
####Referencias






 ##Introducción


En esta última unidad de la materia de datos masivos se creó un proyecto final  basado en los temas de machine learning vistos en clase durante el curso  utilizando el  lenguaje de programación  Scala, este lenguaje nos permite trabajar con diferentes modelos y estructuras de programación como orientado a objetos y nos permite utilizar diferentes funciones los cuales se pueden incorporar y realizar  algún modelo matemático, para el uso en este proyecto se utilizaron  3 diferentes tipos de algoritmos que se estudiaron durante el curso y los cuales se seleccionaron los siguientes y se  mencionan a continuación,  Kmeans, Decision Three y Multilayer Perceptron estos algoritmos  serán necesarios para realizar este proyecto junto con un dataset llamado Bank Marketing el cual este contendra la informacion necesaria para trabajar con los algoritmos. 

 la finalidad de este trabajo  es el de poder   analizar a detalle los datos en forma estructurada y organizada y además de encontrar una solución más óptima al momento de tomar decisiones a corto y a largo plazo  en este caso en la vida real, en donde se requiere tener un perspectiva de los datos importante con ,los que se están trabajando  y la ayuda del lenguaje scala junto con la aplicación de estos algoritmos implementando las metodologías, funciones y operaciones en un ámbito en donde se tiene una  gran cantidad de información valiosa, nosotros podremos seleccionar, contar, establecer, convertir, agrupar,  eliminar, o incluso a predecir el comportamiento de los datos con los que estamos trabajando y al aplicar  algún algoritmo  matemático nos permitirá obtener mejores resultados y para encontrar una solución efectiva a un problema en general  en el cual el estudio detallado de una información almacenada en una gran base de datos es unda,ental para llevar a cabo una solución eficaz.


##Marco teórico de los algoritmos
###Kmeans

El algoritmo K-means es el algoritmo de clustering más conocido y utilizado ya que es de muy simple aplicaci´on y eficaz. Sigue un procedimiento simple de clasificaci´on de un conjunto de objetos en un determinado n´umero K de clusters, K determinado a priori. 

El nombre de K-means viene porque representa cada uno de los clusters por la media (o media ponderada) de sus puntos, es decir, por su centroide. La representación mediante centroides tiene la ventaja de que tiene un significado gráfico y estad´ıstico inmediato.Cada cluster por tanto es caracterizado por su centro o centroide que se encuentra en el centro o el medio de los elementos que componen el cluster. K Means es traducido como K-medias.[1]


O un conjunto de objetos Dn = (x1, x2..., xn), para todo el i, xi reales y k, ν1, los centros de los K cluster. El algoritmo del K-means se realiza en 4 etapas:

**Etapa 1:** Elegir aleatoriamente K objetos que forman así los K clusters iniciales. Para cada cluster k, el valor inicial del centro es = xi, con los xi únicos objetos de Dn pertenecientes al cluster.

**Etapa 2:** Reasigna los objetos del cluster. Para cada objeto x, el prototipo que se le asigna es el que es m´as pr´oximo al objeto, según una medida de distancia, (habitualmente la medida euclidiana).

**Etapa 3:** Una vez que todos los objetos son colocados, recalcular los centros de K cluster. (los baricentros). 



**Etapa 4:** Repetir las etapas 2 y 3 hasta que no se hagan más reasignaciones. Aunque el algoritmo termina siempre, no se garantiza el obtener la soluci´on ´optima. En efecto, el algoritmo es muy sensible a la elección aleatoria de los K centros iniciales. Esta es la razón por la que, se utiliza el algoritmo del K-means numerosas veces sobre un mismo conjunto de datos para intentar minimizar este efecto, sabiendo que a centros iniciales lo más espaciados posibles dan mejores resultados.

![Texto alternativo](/home/armando/Escritorio/1.jpg "Figura 1 .representación gráfica de los clusters fijado con sus respectivos centroide.")
