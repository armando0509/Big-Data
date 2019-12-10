KMEANS

El algoritmo K-means es el algoritmo de clustering más conocido y utilizado ya que
es de muy simple aplicaci´on y eficaz. Sigue un procedimiento simple de
clasificaci´on de un conjunto de objetos en un determinado n´umero K de clusters, K
determinado a priori.

El nombre de K-means viene porque representa cada uno de los clusters por la
media (o media ponderada) de sus puntos, es decir, por su centroide. La
representación mediante centroides tiene la ventaja de que tiene un significado
gráfico y estad´ıstico inmediato.Cada cluster por tanto es caracterizado por su
centro o centroide que se encuentra en el centro o el medio de los elementos que
componen el cluster. K Means es traducido como K-medias.

O un conjunto de objetos Dn = (x1, x2..., xn), para todo el i, xi reales y k, ν1, los
centros de los K cluster. El algoritmo del K-means se realiza en 4 etapas:
Etapa 1: Elegir aleatoriamente K objetos que forman así los K clusters iniciales.
Para cada cluster k, el valor inicial del centro es = xi, con los xi únicos objetos de Dn
pertenecientes al cluster.
Etapa 2: Reasigna los objetos del cluster. Para cada objeto x, el prototipo que se le
asigna es el que es m´as pr´oximo al objeto, según una medida de distancia,
(habitualmente la medida euclidiana).
Etapa 3: Una vez que todos los objetos son colocados, recalcular los centros de K
cluster. (los baricentros).

Etapa 4: Repetir las etapas 2 y 3 hasta que no se hagan más reasignaciones.
Aunque el algoritmo termina siempre, no se garantiza el obtener la soluci´on
´optima. En efecto, el algoritmo es muy sensible a la elección aleatoria de los K
centros iniciales. Esta es la razón por la que, se utiliza el algoritmo del K-means

numerosas veces sobre un mismo conjunto de datos para intentar minimizar este
efecto, sabiendo que a centros iniciales lo más espaciados posibles dan mejores
resultados.