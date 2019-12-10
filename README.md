![Texto alternativo](/home/armando/Escritorio/03.jpg )
~~~
             
                  INSTITUTO TECNOLÓGICO DE TIJUANA 

                        SUBDIRECCIÓN ACADÉMICA

                DEPARTAMENTO DE SISTEMAS Y COMPUTACIÓN

                      SEMESTRE AGOSTO- DICIEMBRE 2019

                                CARRERA:

                         INGENIERÍA INFORMÁTICA

                            MATERIA Y SERIE: 

                      DATOS MASIVOS BDD-1704 IF9A

                           UNIDAD POR EVALUAR:

                             Proyecto Final


                    NOMBRE DEL ALUMNO // NO. DE CONTROL

                      Ocegueda Meraz Armando # 14212337


                              NOMBRE DEL MAESTRO:

                     Dr. JOSE CHRISTIAN ROMERO HERNANDEZ

~~~


**Índice	2**

**Introducción	3**

**Marco teórico de los algoritmos	4**

Kmeans	4

Decision Tree	10

Multilayer Perceptron	19

**Implementación	27**

**Por qué programar spark con scala?	28**

KMeans	30

Multilayer Perceptron	31

Decision Tree	32

**Resultados	33**

KMeans	33

Multilayer Perceptron	34

Decision Tree	34

**Conclusiones	35**

**Referencias**




 ## Introducción


En esta última unidad de la materia de datos masivos se creó un proyecto final  basado en los temas de machine learning vistos en clase durante el curso  utilizando el  lenguaje de programación  Scala, este lenguaje nos permite trabajar con diferentes modelos y estructuras de programación como orientado a objetos y nos permite utilizar diferentes funciones los cuales se pueden incorporar y realizar  algún modelo matemático, para el uso en este proyecto se utilizaron  3 diferentes tipos de algoritmos que se estudiaron durante el curso y los cuales se seleccionaron los siguientes y se  mencionan a continuación,  Kmeans, Decision Three y Multilayer Perceptron estos algoritmos  serán necesarios para realizar este proyecto junto con un dataset llamado Bank Marketing el cual este contendra la informacion necesaria para trabajar con los algoritmos. 

 la finalidad de este trabajo  es el de poder   analizar a detalle los datos en forma estructurada y organizada y además de encontrar una solución más óptima al momento de tomar decisiones a corto y a largo plazo  en este caso en la vida real, en donde se requiere tener un perspectiva de los datos importante con ,los que se están trabajando  y la ayuda del lenguaje scala junto con la aplicación de estos algoritmos implementando las metodologías, funciones y operaciones en un ámbito en donde se tiene una  gran cantidad de información valiosa, nosotros podremos seleccionar, contar, establecer, convertir, agrupar,  eliminar, o incluso a predecir el comportamiento de los datos con los que estamos trabajando y al aplicar  algún algoritmo  matemático nos permitirá obtener mejores resultados y para encontrar una solución efectiva a un problema en general  en el cual el estudio detallado de una información almacenada en una gran base de datos es unda,ental para llevar a cabo una solución eficaz.


## Marco teórico de los algoritmos
### Kmeans

El algoritmo K-means es el algoritmo de clustering más conocido y utilizado ya que es de muy simple aplicaci´on y eficaz. Sigue un procedimiento simple de clasificaci´on de un conjunto de objetos en un determinado n´umero K de clusters, K determinado a priori. 

El nombre de K-means viene porque representa cada uno de los clusters por la media (o media ponderada) de sus puntos, es decir, por su centroide. La representación mediante centroides tiene la ventaja de que tiene un significado gráfico y estad´ıstico inmediato.Cada cluster por tanto es caracterizado por su centro o centroide que se encuentra en el centro o el medio de los elementos que componen el cluster. K Means es traducido como K-medias.[1]


O un conjunto de objetos Dn = (x1, x2..., xn), para todo el i, xi reales y k, ν1, los centros de los K cluster. El algoritmo del K-means se realiza en 4 etapas:

**Etapa 1:** Elegir aleatoriamente K objetos que forman así los K clusters iniciales. Para cada cluster k, el valor inicial del centro es = xi, con los xi únicos objetos de Dn pertenecientes al cluster.

**Etapa 2:** Reasigna los objetos del cluster. Para cada objeto x, el prototipo que se le asigna es el que es m´as pr´oximo al objeto, según una medida de distancia, (habitualmente la medida euclidiana).

**Etapa 3:** Una vez que todos los objetos son colocados, recalcular los centros de K cluster. (los baricentros). 



**Etapa 4:** Repetir las etapas 2 y 3 hasta que no se hagan más reasignaciones. Aunque el algoritmo termina siempre, no se garantiza el obtener la soluci´on ´optima. En efecto, el algoritmo es muy sensible a la elección aleatoria de los K centros iniciales. Esta es la razón por la que, se utiliza el algoritmo del K-means numerosas veces sobre un mismo conjunto de datos para intentar minimizar este efecto, sabiendo que a centros iniciales lo más espaciados posibles dan mejores resultados.


![Texto alternativo](/home/armando/Escritorio/1.jpg "Figura 1 .representación gráfica de los clusters fijado con sus respectivos centroide..")


###### Figura 1 .representación gráfica de los clusters fijado con sus respectivos centroide. ######



![Texto alternativo](/home/armando/Escritorio/2.jpg "Figura 2 .representación gráfica con 3 grupos de clusters fijado con 3 centroides.")


###### Figura 2 .representación gráfica con 3 grupos de clusters fijado con 3 centroides. ######




**Complejidad**

**n =** número de puntos,

 **k =** número de clusters, 

**I =** número iteraciones,

 **d =** número de atributos 
 

Primero se debe de fijar K para empezar con las iteraciones respectivas, y asi para recalcular los nuevos centroides en cada iteración.


**¿Cómo se recalculan los centroides?**  

Partiendo de k grupos de puntos, se utiliza la distancia euclídea y  cada grupo determinará un nuevo centroide 

**Ejemplo de Kmeans**

Se agruparon 8 puntos de la figura en 3 clusters usando el algoritmo de las K medias.

**Centroidesiniciales:** A1, A7 y A8      **Métricas de  distancia:** Distancia euclídea 


![Texto alternativo](/home/armando/Escritorio/3.jpg "Figura 3 .representación gráfica de 8 puntos y 3 centroides..")

###### Figura 3 .representación gráfica de 8 puntos y 3 centroides. ######

A los resultados obtenidos con la distancia euclidiana le sacamos la raíz cuadrada y ese sera la distancia de cada punto.



![Texto alternativo](/home/armando/Escritorio/4.jpg " Figura 4. Resultados obtenidos con la distancia euclidiana...")

   ###### Figura 4.  Resultados obtenidos con la distancia euclidiana. ###### 

Representación gráfica de las iteraciones con los nuevos centroides


![Texto alternativo](/home/armando/Escritorio/5.jpg "  Figura 5. Representación gráfica de la primera y segunda iteración..")


 ###### Figura 5. Representación gráfica de la primera y segunda iteración. ###### 

![Texto alternativo](/home/armando/Escritorio/6.jpg " Figura 6. Representación gráfica de la tercera  y la configuración final de los centroides....")


  ###### Figura 6. Representación gráfica de la tercera  y la configuración final de los centroides. ###### 


Cuando usamos la distancia euclídea, el centroide determinado en cada iteración por el vector de medias garantiza la mejor solución con respecto a la distancia, pero considerando:

    • Un valor de k fijo.
    • Los centroides dados por la iteración anterior. 


En concreto el uso de K Means es una manera eficaz de clasificar un conjunto de grupos la cual nos permite ir haciendo iteraciones para fijar a qué grupo pertenece cada dato. 

## Decision Tree


Un árbol de decisión es un sistema de soporte de decisiones que utiliza decisiones de gráfico tipo árbol y sus posibles efectos posteriores, que incluyen resultados de eventos fortuitos, costos de recursos y utilidad. Un árbol de decisión, o un árbol de clasificación, se utiliza para aprender una clasificación. Función que concluye el valor de un atributo dependiente (variable) dados los valores de la independiente (entrada) atributos (variables).

 Esto verifica un problema conocido como clasificación supervisada porque el atributo dependiente se dan recuentos de clases (valores) [2]. Los árboles de decisión son los enfoques más poderosos en el descubrimiento de conocimiento además qué  incluye en la  tecnología de investigación de gran y complejo volumen de datos para descubrir patrones útiles. Esta idea es muy importante porque permite el modelado y la extracción de conocimiento del grueso de los datos disponibles. Todos teóricos y especialistas. continuamente están buscando técnicas para hacer que el proceso sea más eficiente, rentable y preciso. Árboles de decisión son herramientas altamente efectivas en muchas áreas, como minería de datos y texto, extracción de información, aprendizaje automático y reconocimiento de patrones[6].

 El árbol de decisión ofrece muchos beneficios para la toma de decisiones , algunos son los siguientes:

• Es fácil de entender por el usuario final.

• Puede manejar una variedad de datos de entrada: Nominal, Numérico y Textual.

• Capaz de procesar conjuntos de datos erróneos o valores perdidos

• Alto rendimiento con un pequeño número de esfuerzos.

• Esto se puede implementar paquetes de minería de datos en una variedad de plataformas .

**Un árbol incluye:** - Un nodo raíz, nodos hoja que representan cualquier clase, nodos internos que representan condiciones de prueba (aplicado
en atributos)


![Texto alternativo](/home/armando/Escritorio/7.jpg " Figura 7.  Representación de la metodología de árboles de decisión......")
        

###### Figura 7.  Representación de la metodología de árboles de decisión. ###### 



## Tamaño del árbol
 

Básicamente, los tomadores de decisiones prefieren un árbol de decisiones porque no es complejo ni fácil de entender. Complejidad del árbol tiene su efecto en su precisión. Por lo general, la complejidad del árbol se puede medir mediante una métrica que contiene: el número total de nodos, número total de hojas, profundidad del árbol y número de atributos utilizados en la construcción del árbol. El tamaño del árbol debe ser relativamente pequeño que se puede controlar mediante el uso de una técnica llamada poda

## Regla de inducción en árboles

La inducción del árbol de decisión está estrechamente relacionada con la inducción de la regla. Cada ruta que comienza desde la raíz de un árbol de decisión y termina en uno de sus permisos representa una regla. Estas reglas se pueden generar muy fácilmente.



## ENFOQUES DEL ÁRBOL DE DECISIÓN


Hay dos enfoques para el árbol de decisión como: 
 
**1) Árbol de decisión univariante**
 
En esta técnica, la división se realiza mediante el uso de un atributo en los nodos internos. Por ej. X <2, y> = 10 etc. Hay muchos algoritmos para crear árboles como ID3, c4.5 (j48 en weka), etc. Este documento ilustra cómo implementar j48 algoritmo y análisis de su resultado. Este algoritmo es una extensión del algoritmo ID3 y posiblemente crea un pequeño árbol. 

**A. construcción**
 


    • A continuación se dan algunos pasos básicos para construir el árbol:


Primero, verifique si todos los casos pertenecen a la misma clase, luego el árbol es una hoja y está etiquetado con esa clase.

 Para cada atributo, calcule la información y la ganancia de información.
 Encuentre el mejor atributo de división (según el criterio de selección actual)

**B. Contar ganancia de información**
 
La "entropía" se utiliza en este proceso. La entropía es una medida del desorden de los datos. La entropía se mide en bits, nats o prohibiciones. Esta también se llama medición de la incertidumbre en cualquier variable aleatoria. Solo suponga que hay una moneda justa, si un solo lanzamiento es
realizado en esa moneda que su entropía será un bit. Una serie de dos lanzamientos de monedas justos tendrá una entropía de dos bits.
Ahora, si la moneda no es justa, entonces hay incertidumbre y esto proporciona una tasa de entropía más baja.


La entropía para cualquier P se puede calcular como:

La entropía condicional es:

![Texto alternativo](/home/armando/Escritorio/8.jpg )



        
Si la base es 2 para el logaritmo, entonces la unidad de medida de entropía estará en bits, si la base es 10, la unidad es dits. La ganancia de información es utilizado para medir la asociación entre entradas y salidas. Es un cambio de estado a estado en la entropía de la información, finalmente la ganancia de información se puede calcular como: -

![Texto alternativo](/home/armando/Escritorio/9.jpg )

**C. poda**
 


La poda es una técnica muy importante para ser utilizada en la creación de árboles debido a los valores atípicos. También aborda el sobreajuste. Conjuntos de datos puede contener pequeños subconjuntos de instancias que no están bien definidos. Para clasificarlos correctamente, se puede utilizar la poda. Hay dos tipos de poda:



1. Poda posterior (realizada después de la creación del árbol)

2. Poda en línea (realizada durante la creación del árbol)

**Algoritmos de poda**
 

Separar y conquistar el algoritmo de aprendizaje de reglas es la base para podar cualquier árbol [7]. Existen varios esquemas de aprendizaje de reglas. Todos los esquemas de aprendizaje de reglas de separar y conquistar son modificaciones del mismo algoritmo simple que comienza con un conjunto vacío de reglas y el conjunto completo de instancias de entrenamiento. Este algoritmo simple de Separar y conquistar se proporciona a continuación:

**Procedimiento** 
 
    • R: = conjunto de reglas vacío
 
    • mientras no D está vacío
 
 
    • r: = mejor regla individual para D
 
 
    • R: = agregar r a R
 
 
    • eliminar esas instancias de D que están cubiertas por r devolver R
 
 
**Algunos esquemas de aprendizaje de reglas de Separar y conquistar son:** -
 
 
    • Poda de error reducido para reglas
 
    • Poda incremental de error reducido
 
 
    • Poda incremental basada en árboles.
 
 **2) Árbol de decisión multivariante**
 
 
La DT multivariante puede generalizarse bien cuando se trata de la correlación de atributos y su resultado también es fácil para los humanos.
Cuando trabajan con Univariate DT, prueban un solo atributo más de una vez que puede resultar en un árbol ineficiente en algunos situaciones El DT multivariante realiza diferentes pruebas con los datos mediante el uso de más de un atributo en las hojas de prueba. Prueba La condición en estos árboles puede ser como x + y> 10. Esta técnica es una combinación no lineal de atributos en cada nodo de prueba

 Cada nodo de prueba seguirá la forma:

![Texto alternativo](/home/armando/Escritorio/10.jpg )



Donde wi son coeficientes de valor real, yi son atributos yn es el total de atributos en una instancia. Las Figuras 3 y 4 muestran la diferencia entre el particionamiento de espacio univariado y multivariado y también representa como prueba multivariable las condiciones se colocan en nodos internos

![Texto alternativo](/home/armando/Escritorio/11.jpg "Figura 7.  partición de espacio multivariante" )


###### Figura 8.  partición de espacio multivariante ######


![Texto alternativo](/home/armando/Escritorio/12.jpg "Figura 8.  condiciones de decisión multivariadas" )

###### Figura 9.  condiciones de decisión multivariadas ###### 

**Construcción**

En primer lugar, deberíamos tener un conjunto de instancias de entrenamiento. Utiliza un algoritmo de árbol de decisión de arriba hacia abajo y una selección de méritos. Criterios para elegir el mejor atributo de división para crear una rama. Así tenemos dos particiones. Algoritmo se aplicará igual análisis de arriba hacia abajo para hacer más particiones. Uno de los criterios de detención es cuando todos los valores del atributo pertenecen a una sola clase. Solo hay diferencia en los criterios de división si se hace una comparación entre un árbol múltiple y univariado construcción. DT multivariante utiliza LM (máquina lineal).



-Ejemplo mostrando los resultados del enfoque de árbol de decisión univariante
 

-Pasos para crear un árbol en weka

1 Cree conjuntos de datos en MS Excel, MS Access o cualquier otro y guárdelos en formato .CSV.
2 Inicie el weka Explorer.

3 Abra el archivo .CSV y guárdelo en formato .ARFF.
4 Haga clic en la pestaña Clasificar y seleccione J48 del botón Elegir.
5 Seleccione cualquier opción de prueba adecuada.
6 Haga clic en el botón Inicio y se mostrará el resultado.


![Texto alternativo](/home/armando/Escritorio/13.jpg "Figura 9. Datos para crear árbol" )

###### Figura 9. Datos para crear árbol ###### 


El resultado se muestra en la ventana de salida del clasificador. El resultado se puede ver en una ventana separada. Para hacer esto, haga clic derecho en lista de resultados Para ver el árbol en forma gráfica, haga clic en la opción "visualizar árbol" en el menú emergente. Se muestra un árbol de decisión en la figura 10 que incluye la categoría, el resultado, el salario como raíz y los nodos internos y las clases para los nodos hoja son aprobado, reprobado, HOD, maestro y empleado.

![Texto alternativo](/home/armando/Escritorio/14.jpg "Figura 11. Visualización del árbol de decisiones" )


###### Figura 11. Visualización del árbol de decisiones ###### 

 ## Resultado del enfoque de árbol de decisión multivariante
 


En el programa Weka nos  proporciona un algoritmo, llamado M5P, que se utiliza para crear un árbol de clasificación y regresión con un lineal multivariado.

modelo de regresión donde p significa primo. Este algoritmo proporciona un modelo lineal como clases con un porcentaje de errores aproximados La aplicación de este algoritmo en el conjunto de datos es igual que la aplicación del algoritmo J48. Resultado producido por M5P se muestra en la figura 12 como se muestra a continuación:

![Texto alternativo](/home/armando/Escritorio/15.jpg "Figura 12 Resultado de M5P" )

###### Figura 12 Resultado de M5P ###### 


En este ejemplo se ve la implementación de árboles de decisión utilizando el programa weka en donde la posibilidad de aplicar este modelo de predicción de algoritmo de árboles nos proporciona una visión más general del momento de clasificar un gran conjunto de datos y determinar las variantes más importantes en la toma de decisiones. El árbol es una técnica popular para la clasificación supervisada, especialmente cuando los resultados son interpretados por humanos. El árbol de decisión multivariante utiliza el concepto de correlación de atributos y proporciona la mejor manera de realizar condicional pruebas en comparación con el enfoque univariante. El estudio de investigación concluye que el enfoque multivariante es mucho mejor qué enfoque univariante mientras nos permite tratar con gran cantidad de datos.

  ## Multilayer Perceptron

Se construye la red neuronal de percepción multicapa de componentes simples. Al principio, lo haremos describir una sola neurona de entrada que luego será extendido a múltiples entradas. A continuación, ampliaremos estos neuronas juntas para producir capas [4]. Finalmente, las capas se conectan en cascada para formar la red.

![Texto alternativo](/home/armando/Escritorio/16.jpg "Fig. 13: Red neuronal como aproximador de funciones" )

###### Fig. 13: Red neuronal como aproximador de funciones ###### 


Neurona de entrada única: se muestra una neurona de entrada única en la Fig. 14. la entrada escalar p se multiplica por el escalar ponderar W para formar Wp, uno de los términos que se envía a el verano. La otra entrada, 1, se multiplica por un sesgo b y luego pasó al verano. La salida de verano a menudo referido como la entrada neta, entra en una transferencia función f que produce la salida de la neurona escalar a (a veces se utiliza "función de activación" en lugar de función de transferencia y desplazamiento en lugar de sesgo).

![Texto alternativo](/home/armando/Escritorio/17.jpg "Fig. 14: neurona de entrada única " )


###### Fig. 14: neurona de entrada única ###### 


función de respuesta y desplazamiento en lugar de sesgo). De la Fig. 14, ambos w y b son ambos ajustables parámetros escalares de la neurona. Típicamente la transferencia la función es elegida por el diseñador y luego el los parámetros w y b serán ajustados por algún aprendizaje regla para que la relación de entrada / salida de neurona cumplir algún objetivo específico. La función de transferencia en
La figura 14 puede ser una función lineal o no lineal de n. Una se elige una función de transferencia particular para satisfacer algunas especificación del problema de que la neurona es intentando resolver Uno de los más utilizados. funciones es la función de transferencia log-sigmoide, que es mostrado en la Fig. 15.

![Texto alternativo](/home/armando/Escritorio/18.jpg "Fig. 15: Función de transferencia de log-sigmiod" )


###### Figura 15. Función de transferencia de log-sigmiod ###### 

Neurona de entrada múltiple: por lo general, una neurona tiene más de una entrada, una neurona con entradas R se muestra en la  Fig. 16.

![Texto alternativo](/home/armando/Escritorio/19.jpg "Fig. 16: neurona de entrada múltiple" )


###### Fig. 16: neurona de entrada múltiple ######

![Texto alternativo](/home/armando/Escritorio/20.jpg "Fig. 17: Neurona con entradas R, anotaciones abreviadas" )
 

 ###### 17. Neurona con entradas R, anotaciones abreviadas ###### 

 ponderado por los elementos correspondientes W1,1 W1,2,… .W1, R de la matriz de peso W. La neurona tiene un sesgo b, que se suma con el entradas de peso para formar la entrada neta n:


**Esta expresión se puede escribir en forma de matriz como:**
 
 ![Texto alternativo](/home/armando/Escritorio/21.jpg  )

 Donde la matriz W para el caso de neurona individual tiene solo una fila. Ahora la salida de neuronas se puede escribir como:

  ![Texto alternativo](/home/armando/Escritorio/22.jpg  )


## PERCEPTRON MULTILAYER
## ARQUITECTURAS DE RED


Comúnmente una neurona, incluso con muchas entradas, puede no ser suficiente Podríamos necesitar cinco o diez operando en paralelo, en lo que llamaremos una "capa". El concepto de una capa se analiza a continuación.

Una capa de neuronas: una red de una sola capa de S es se muestra en la Fig. 18. Tenga en cuenta que cada una de las entradas R es conectado a cada una de las neuronas y que el peso matriz ahora tiene s filas.

La capa incluye la matriz de peso, los veranos, el vector de sesgo b, los cuadros de función de transferencia y el vector de salida a. Cada elemento del vector de entrada p es conectado a cada neurona a través de la matriz de peso W.
Cada neurona tiene un sesgo bi, un verano, una función de transferencia y una salida ai. En conjunto, las salidas forman el vector de salida a. Es común para la cantidad de entradas para que una capa sea diferente del número de neuronas (es decir, R ≠ S). Los elementos del vector de entrada ingresan a la red a través de la matriz de peso W:

  ![Texto alternativo](/home/armando/Escritorio/23.jpg "Fig. 18: Capa de neuronas S" )

  ###### Fig. 18: Capa de neuronas S ###### 

   ![Texto alternativo](/home/armando/Escritorio/24.jpg "Fig. 19: Capa de neuronas S, notación abreviada" )


###### Fig. 19: Capa de neuronas S, notación abreviada ###### 

Los índices de fila de los elementos de la matriz W indicar la neurona de destino asociada con eso peso, mientras que los índices de columna indican la fuente de la entrada para ese peso. Por lo tanto, los índices en W3,2 dicen que este peso representa la conexión con el tercero neurona de la segunda fuente. La neurona S, entrada R, la red de una capa también se puede dibujar en forma abreviada notación como se muestra en la Fig.19.

Aquí nuevamente, los símbolos debajo de las variables dicen que para esta capa, p es un vector de longitud R, W es un S * R matriz a y b son vectores de longitud S. Como se definió anteriormente, la capa incluye el matriz de peso, suma y multiplicación operaciones, el vector de sesgo b, los cuadros de función de transferencia y el vector de salida.

**Múltiples capas de neuronas:** ahora considere una red con varias capas que se ha implementado en este papel para analizar el esquisto bituminoso de Jordania propiedades. En esta red, cada capa tiene su propia matriz de peso W, su propio vector de polarización b, una entrada neta vector ny un vector de salida a. Algunos adicionales Se debe introducir la notación para distinguir entre estas capas. El número de la capa como superíndice es agregado a los nombres de cada una de estas variables. Por lo tanto, la matriz de peso para la segunda capa se escribe como W2. Esta notación se utiliza en la red de tres capas. mostrado en la Fig. 20.

Como se muestra en la Fig. 20, hay entradas R, S1 neuronas en la primera capa, S2 neuronas en la segunda capa, etc. La salida de las capas uno y dos son las entradas para capas dos y tres. Por lo tanto, la capa 2 puede verse como un red de una capa con entradas R = S1, S = S2 neuronas y un S1 * S2 matriz de peso W2. La entrada a la capa 2 es a1 y la salida es un 2. Una capa cuya salida es la salida de red se denomina capa de salida. El otro las capas se llaman capas ocultas. La red que se muestra en la figura 8 tiene una capa de salida (capa3) y dos ocultas capas (capas 1 y 2).

## ESTRUCTURA Y OPERACIÓN DE
## PERCEPTRON MULTILAYER
## RED NEURAL (MLP)

Las redes neuronales MLP consisten en unidades dispuestas en capas. Cada capa está compuesta de nodos y en el red totalmente conectada considerada aquí cada nodo se conecta a cada nodo en las capas posteriores. Cada MLP está compuesto por un mínimo de tres capas que consta de una capa de entrada, una o más capas ocultas y una salida capa. La capa de entrada distribuye las entradas a capas posteriores. Los nodos de entrada tienen activación de línea funciones y sin umbrales. Cada nodo de unidad oculto y cada nodo de salida tiene umbrales asociados con ellos además de los pesos.

 ![Texto alternativo](/home/armando/Escritorio/25.jpg "Fig. 22: Red neuronal perceptrónica multicapa de tres capas típica" )

###### Fig. 22: Red neuronal perceptrónica multicapa de tres capas típic ######


Los nodos de la unidad oculta tienen funciones de activación no lineales y las salidas tienen funciones de activación lineal. Por lo tanto, cada señal de alimentación en el ánodo en una capa posterior tiene la entrada original
multiplicado por un peso con un umbral agregado y luego se pasa a través de una función de activación que puede ser lineal o no lineal (unidades ocultas). Un típico de tres capas la red se muestra en la Fig. 22. Solo las MLP de tres capas ser considerado en este trabajo ya que estas redes tienen se ha demostrado que aproximar cualquier función continua [6-8]. Para el MLP real de tres capas, todas las entradas también están conectados directamente a todas las salidas

## CONCLUSIONES MLP
Cuando los investigadores diseñan redes neuronales para el aplicación presentada en este documento, hay muchas maneras usado para investigar los efectos de la estructura de la red que se refiere a la especificación del tamaño de la red (es decir, número de unidades ocultas) cuando el número de entradas y las salidas son fijas (5) lo que a su vez afecta a la o / p Parámetros predichos. Una bien organizada tal vez basado en el diseño de un conjunto de redes de diferentes tamaños en un moda ordinaria, cada una con una o más unidades ocultas que el anterior Este enfoque puede ser designado como metodología de diseño uno (DM-1). Alternativamente, el segundo se basa en diseñar diferentes tamaños redes en ningún orden particular. Este enfoque puede ser designado como metodología de diseño dos (DM-2). Estos dos enfoques de diseño son significativamente diferente y el enfoque elegido a menudo se basa en el objetivos establecidos para el diseño de la red y los asociados actuación. En general, el enfoque más completo a menudo usado para DM-1 puede tomar más tiempo desarrollar el red seleccionada ya que podemos estar interesados ​​en tratar de lograr una compensación entre el rendimiento de la red y tamaño de la red Sin embargo, el enfoque DM-1 utilizado en este el trabajo puede producir un diseño superior ya que el diseño puede ser perseguido hasta que estemos satisfechos con ese futuro el aumento en el tamaño de la red produce rendimientos decrecientes
en términos de disminución del tiempo de entrenamiento o errores de prueba.
Cuando diseñamos la red MLP para analizar el propiedades del esquisto bituminoso de Jordania, llegamos a lo siguiente hechos: MLP con muchas capas ocultas (3) realiza más mejor que con una o dos capas ocultas especialmente con respecto a los parámetros de rendimiento de salida. También, cuando comparamos entre el o / p de MLP y el fórmula matemática, encontramos que nuestra o / p el parámetro de rendimiento se adapta mejor con el experimental. Como trabajo futuro, recomendamos usar enfoques híbridos, que son una combinación de ANN con otras técnicas como sistemas expertos, Fuzzy lógica y Algoritmo Genético (GA) para hacer tal análisis de las propiedades del esquisto bituminoso de Jordania.

## Implementación


En el desarrollo de este proyecto se utilizaron las herramientas de spark-2.4.4-bin-hadoop2.7 con scala en la cual  en estas herramientas fue en donde se realizaron los algoritmos matemáticos y lo métodos correspondientes, el lenguaje de programación scala es un lenguaje de programación escalable y está diseñado para realizar diferentes tareas de programación desde realizar scripts hasta construir un conjunto de sistemas más complejos, para la utilización de scala esta se ejecuta en la plataforma de java que interopera perfectamente con las bibliotecas necesarias que scala necesita para utilizar, este es una excelente herramienta porque nos permite reunir diferentes componentes de java y nos da la posibilidad de desarrollar aún más las fortalezas en cuanto estamos haciendo scripts y generando codigos para sistemas más completos.

Scala contiene una estructura y combinación de conceptos que es orientada a objetos y es además un lenguaje de tipo estático. la funcionalidad de scala es que al realizar la construcción de la programación está facilitan la creación en forma rápida 
y eficaz en el momento de trabajar con estructuras y códigos muy complejos. Su integridad tiene una manera fácil de estructurar los sistemas que son especialmente grandes y tiene la capacidad de  adaptarlos a las nuevas demandas y además genera nuevos tipos de patrones de programación y la abstracción de componentes.

## Por qué programar spark con scala? 

Programar Spark con  Scala puede ser una manera más eficaz, rápida y elegante al momento de generar diferentes scripts, y  además de la eficiencia de programar en scala la forma de programar tiene una gran usabilidad uno de los aspectos importantes con las que cuenta  son, la compatibilidad, es breve, es de alto nivel y su estructura estática.

La utilización de spark es una plataforma de big data que nos ayuda en el diseño y implementación de algoritmos iterativos que se encuentran especialmente en el aprendizaje automático, spark en si esta escrito en scala, los trabajos de spark se pueden escribir en scala, spark contiene bibliotecas importantes sobre las cuales se trabajan, como las de machine learning que son de gran utilidad al momento de programar algoritmos matemáticos.

Dentro de las funciones de spark, en la ejecución de los programas se utiliza el llamado “spark-shell que este apoya al desarrollo iterativo del programa, y una vez inicializado podemos ejecutar las las sentencias que le asignemos  
una de las ventajas de spark es que tiene una gran velocidad en sus procesos y puede ser una buena alternativa en caso de llevar a cabo la automatización y la agilidad en la que trabaja en una gran cantidad de datos así permitiendo la combinación de spark con scala nos dará una mejor y exitosa manera de programar sistemas en big data para tareas y procesos complejos.


En el desarrollo del proyecto se importaron las siguientes librerías para utilizar los algoritmo de K Means, Multilayer Perceptron Y Decision tree a continuación se muestra el código de cada algoritmo.

Primero importamos las librerias necesarias  para empezar a trabajar con los algoritmos

![Texto alternativo](/home/armando/Escritorio/26.jpg )

Después se cargan todos los datos del dataset bank-full y posteriormente se realiza la limpieza, una vez cargado y realizado la limpieza los dato son convertidos de int a double.

![Texto alternativo](/home/armando/Escritorio/27.jpg )


![Texto alternativo](/home/armando/Escritorio/28.jpg )

## KMeans

 **Aquí se importó la librería para utilizar el algoritmo K-means**

`import org.apache.spark.ml.clustering.KMeans`

**Se declararon 3 variables una llamada kmeans donde se fija el número de clusters y posteriormente se hace el modelo para ejecutar el dataframe**

`val kmeans = new KMeans().setK(7).setSeed(1L).setPredictionCo("prediction"`

`val model = kmeans.fit(df)`

`val WSSE = model.computeCost(df)`

Una vez creado las variables y el modelo se declara una variable llamada categorías que transforma los datos del modelo, después nos muestra los resultados  y se imprimen los datos con los clusters del dataframe.

`println("Cluster Centers: ")`

`model.clusterCenters.foreach(println)`

`val categories = model.transform(testData)`

`val displaydoresult = categories.select($"age",$"balance",$"prediction").groupBy("age","balance","prediction").agg(count("prediction")).orderBy("age","balance","prediction")`

`displaydoresult.show(5)`

`categories.show(5)`

## Multilayer Perceptron

 Para el algoritmo Multilayer Perceptron se importan las librerías siguientes:

 ![Texto alternativo](/home/armando/Escritorio/29.jpg )

Se declaran las siguientes variables para el uso del algoritmo, dentro de los principales están los de division prueba y entrenamiento, también se declara una variable  evaluador qué es el que nos muestra los datos del dataset con exactitud, y posteriormente se imprime el test de los datos de exactitud. 

 ![Texto alternativo](/home/armando/Escritorio/30.jpg )

 ## Decision Tree 

Para el algoritmo Decision Tree se importaron las siguientes librerías

**Decision Tree**

`import org.apache.spark.ml.Pipeline`

`import org.apache.spark.ml.evaluation.RegressionEvaluator`

`import org.apache.spark.ml.regression.DecisionTreeRegressionModel`

`import org.apache.spark.ml.regression.DecisionTreeRegressor`

En esta parte en la variable feature Indexer  convierte los datos en Vectores, después  se mostraran estos como categorías y se almacenarán en un arreglo el cual se almacena en la variable pipeline el cual se transforman con el modelo y nos mostrar la predicción de los datos. 

`val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(c55)`

`val Array(trainingData, testData) = c55.randomSplit(Array(0.7, 0.3))`

`val dt = new DecisionTreeRegressor().setLabelCol("label").setFeaturesCol("indexedFeatures")`

`val pipeline = new Pipeline().setStages(Array(featureIndexer, dt))`

`val model43 = pipeline.fit(trainingData)`

`val predictions = model.transform(testData)`

`predictions.select("prediction", "label", "features").show(5)`

 ## Resultados 
### KMeans

## Tabla de Resultados 

En esta tabla de resultados de KMean nos muestra las columnas con los atributos que se calcularon con el algoritmo la cual está la edad, balance, predicción y la predicción de conteo.

WSSE(Suma de cuadrados dentro del grupo)= Double = 2.9713032022240818E10 

Resultado de los clusters obtenidos con el algoritmo de kmeans

**Cluster Centers:** 

[259.94106295476115,15.715374881366657,251.92847200253084,39.294495412844036,0.5361910787725404]
[41614.181818181816,15.681818181818182,282.54545454545456,32.13636363636364,0.3181818181818182]
[20444.568965517243,16.32758620689655,255.05862068965516,31.682758620689654,0.5689655172413793]
[4951.1686082158185,16.205088902513793,276.95922746781116,39.541079092581235,0.7210300429184548]
[1986.0305111461382,15.953614050889438,271.24757937401483,43.787773024093674,0.678225624859266]
[10036.326457399104,15.954260089686098,275.50313901345294,41.592825112107626,0.6529147982062781]
[78982.125,10.75,263.125,50.125,0.625]

## Multilayer Perceptron

En la tabla de resultados de multilayer perceptron nos muestra la predicción de los datos de las columnas del dataset que es de 1.0

**Test set accuracy = 1.0**

## Decision Tree 

Resultados de predicción, etiqueta y características del algoritmo árboles de decisión

![Texto alternativo](/home/armando/Escritorio/31.jpg )


rmse(mide la la cantidad de error qué hay entre dos conjuntos de datos): Double = 1.9747637354826524  

Root Mean Squared Error (RMSE) on test data = 1.9747637354826524

**tabla de comparación**

~~~


label
balance
predicción
features
KMeans

0

76
0
0
Multilayer Perceptron

1
0
1
0
Decision Tree 
1.6
0
0
101
~~~

## Conclusiones

Basado en los resultados de los algoritmos el Decision tree tiene un mejor rendimiento al momento de aplicarlo en el dataset en segundo lugar está multilayer perceptron que se enfoca más en las predicciones en el momento de trabajar con los datos de prueba y entrenamiento, y por ultimo esta el algoritmo  K Means que nos muestra como resultado el balance y el conteo de predicción además muestra los datos agrupados a cada cluster, en general cada algoritmo  funciona dependiendo para qué propósito o qué resultado se desea tener, y saber cuales son las características de los puntos importantes en donde se quiere aplicar los algoritmos, las funciones matemáticas y las metodologías aplicadas en general nos permiten tener de forma rápida y mejores resultados.

## Referencias



[1] Bhargava, N., Sharma, G., Bhargava, R., & Mathuria, M. (2013). Decision tree analysis on j48 algorithm for data mining. Proceedings of International Journal of Advanced Research in Computer Science and Software Engineering, 3(6).

[2] Cambronero, C. G., & Moreno, I. G. (2006). Algoritmos de aprendizaje: knn & kmeans. Intelgencia en Redes de Comunicación, Universidad Carlos III de Madrid.

[3] Hamerly, G., & Elkan, C. (2004). Learning the k in k-means. In Advances in neural information processing systems (pp. 281-288).

[4] Murtagh, F. (1991). Multilayer perceptrons for classification and regression. Neurocomputing, 2(5-6), 183-197.

[5] Quinlan, J. R. (1986). Induction of decision trees. Machine learning, 1(1), 81-106.

[6]  Utgoff, P. E. (1989). Incremental induction of decision trees.Machine learning, 4(2), 161-186.

[7] Wagstaff, K., Cardie, C., Rogers, S., & Schrödl, S. (2001, June). Constrained k-means clustering with background knowledge. In Icml (Vol. 1, pp. 577-584).

