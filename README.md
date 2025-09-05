# Actividad 3
Actividad 3

Este repositorio contiene implementaciones en Java de diferentes problemas que se pueden resolver utilizando recursividad y técnicas de backtracking.

Archivos del Proyecto

1. Fibonacci.java

Calcula el enésimo número de la serie de Fibonacci mediante recursividad.
Cada número se obtiene sumando los dos anteriores.

Casos base: F(0)=0, F(1)=1.

Ejemplo: Entrada n=6 → Salida 8.

Bueno para comprender la recursividad básica.

2. SubsetSum.java

Resuelve el problema de la suma de subconjuntos.
Dado un conjunto de enteros y un valor objetivo, determina si es posible formar ese valor sumando algunos elementos del conjunto.

Explora dos opciones recursivamente:

Incluir el número actual en la suma.

Excluirlo y seguir con los demás.

Ejemplo: Conjunto {3, 34, 4, 12, 5, 2}, Objetivo 9 → Existe el subconjunto {4, 5}.

Ejemplo clásico de divide y vencerás aplicado con recursividad.

3. Sudoku.java

Soluciona un Sudoku usando backtracking.
El algoritmo llena las casillas vacías probando valores del 1 al 9 y retrocede si encuentra una contradicción.

Reglas del Sudoku:

No repetir números en filas.

No repetir números en columnas.

No repetir números en cada subcuadro 3x3.

El backtracking prueba posibilidades y corrige errores volviendo atrás.

Ejemplo práctico de cómo los algoritmos exploran soluciones paso a paso.

- Requisitos

Tener instalado Java JDK 8 o superior.

Un editor de texto o IDE (ej. VS Code, Eclipse o IntelliJ).


▶ Cómo ejecutar los programas

Compilar:

javac Fibonnaci.java
javac SubsetSum.java
javac Sudoku.java


Ejecutar:

java Fibonnaci
java SubsetSum
java Sudoku

Alternativa:
Si no puede ejecutarlo descargue los archivos, pongalos en una carpeta y ejecutelos en vscode c: