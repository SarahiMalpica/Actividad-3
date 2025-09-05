import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    
    // Generador de números aleatorios para crear conjuntos y objetivos
    private static final Random random = new Random();
    
    /**
     * Función principal que verifica si existe un subconjunto que sume el objetivo
     * conjunto - Array de números enteros
     * objetivo - Valor que debe sumar el subconjunto
     * Devuelve true si existe un subconjunto que suma exactamente el objetivo
     */
    public static boolean existeSubconjunto(int[] conjunto, int objetivo) {
        // Inicia la búsqueda recursiva desde el índice 0 con suma 0
        return buscarSubconjunto(conjunto, objetivo, 0, 0, new ArrayList<>());
    }
    
    /**
     * Función recursiva que realiza la búsqueda con backtracking
     * conjunto - Array de números
     * objetivo - Valor objetivo de la suma
     * indice - Índice actual en el conjunto
     * sumaActual - Suma acumulada hasta el momento
     * subconjuntoActual - Lista con los elementos del subconjunto actual
     * Devuelve true si se encuentra un subconjunto válido
     */
    private static boolean buscarSubconjunto(int[] conjunto, int objetivo, int indice, int sumaActual, List<Integer> subconjuntoActual) {
        // Caso base: se encontró una suma exacta
        if (sumaActual == objetivo && !subconjuntoActual.isEmpty()) {
            System.out.println("Subconjunto encontrado: " + subconjuntoActual + " = " + objetivo);
            return true;
        }
        // Caso base: se excedió el objetivo o se recorrió todo el conjunto
        if (indice >= conjunto.length || sumaActual > objetivo) return false;
        
        // Explorar la posibilidad de incluir el elemento actual
        subconjuntoActual.add(conjunto[indice]);
        boolean incluyendo = buscarSubconjunto(conjunto, objetivo, indice + 1, sumaActual + conjunto[indice], subconjuntoActual);
        
        // Explorar la posibilidad de no incluir el elemento actual
        subconjuntoActual.remove(subconjuntoActual.size() - 1);
        boolean excluyendo = buscarSubconjunto(conjunto, objetivo, indice + 1, sumaActual, subconjuntoActual);
        
        return incluyendo || excluyendo;
    }
    
    /**
     * Función alternativa que retorna el subconjunto encontrado
     * conjunto - Array de números enteros
     * objetivo - Valor que debe sumar el subconjunto
     * Devuelve la lista del subconjunto que suma el objetivo, o null si no existe
     */
    public static List<Integer> encontrarSubconjunto(int[] conjunto, int objetivo) {
        List<Integer> resultado = new ArrayList<>();
        boolean encontrado = buscarSubconjuntoConResultado(conjunto, objetivo, 0, 0, resultado);
        return encontrado ? resultado : null;
    }
    
    /**
     * Función recursiva que encuentra y retorna el subconjunto
     * conjunto - Array de números
     * objetivo - Valor objetivo de la suma
     * indice - Índice actual en el conjunto
     * sumaActual - Suma acumulada hasta el momento
     * resultado - Lista donde se almacena el subconjunto encontrado
     * Devuelve true si se encuentra un subconjunto válido
     */
    private static boolean buscarSubconjuntoConResultado(int[] conjunto, int objetivo, int indice, int sumaActual, List<Integer> resultado) {
        // Caso base: se encontró una suma exacta
        if (sumaActual == objetivo) return true;
        // Caso base: se excedió el objetivo o se recorrió todo el conjunto
        if (indice >= conjunto.length || sumaActual > objetivo) return false;
        
        // Intentar incluir el elemento actual
        resultado.add(conjunto[indice]);
        if (buscarSubconjuntoConResultado(conjunto, objetivo, indice + 1, sumaActual + conjunto[indice], resultado)) {
            return true;
        }
        
        // Quitar el elemento actual y probar sin él
        resultado.remove(resultado.size() - 1);
        return buscarSubconjuntoConResultado(conjunto, objetivo, indice + 1, sumaActual, resultado);
    }
    
    /**
     * Función recursiva para generar un conjunto aleatorio de números enteros positivos
     * conjunto - Array donde se almacenarán los números
     * indice - Índice actual en el array
     * Devuelve array con números aleatorios entre 1 y 50
     */
    private static int[] generarConjuntoRecursivo(int[] conjunto, int indice) {
        // Caso base: se completó el array
        if (indice >= conjunto.length) return conjunto;
        
        // Generar número aleatorio entre 1 y 50
        conjunto[indice] = random.nextInt(50) + 1;
        
        // Llamada recursiva para el siguiente índice
        return generarConjuntoRecursivo(conjunto, indice + 1);
    }
    
    /**
     * Genera un conjunto aleatorio de números enteros positivos
     * tamaño - Cantidad de elementos en el conjunto
     * Devuelve array con números aleatorios entre 1 y 50
     */
    public static int[] generarConjuntoAleatorio(int tamaño) {
        // Crear array y llenarlo recursivamente
        return generarConjuntoRecursivo(new int[tamaño], 0);
    }
    
    /**
     * Genera un objetivo aleatorio para la suma de subconjuntos
     * conjunto - Array de números (no se usa en esta implementación)
     * Devuelve número aleatorio entre 0 y 49
     */
    public static int generarObjetivoAleatorio(int[] conjunto) {
        return random.nextInt(50); // Números entre 0 y 49
    }
    
    /**
     * Calcula la suma de todos los elementos de un conjunto
     * conjunto - Array de números
     * Devuelve la suma total de los elementos
     */
    public static int sumaTotal(int[] conjunto) {
        return sumaTotalRecursiva(conjunto, 0, 0);
    }
    
    /**
     * Función recursiva para calcular la suma total
     * conjunto - Array de números
     * indice - Índice actual
     * suma - Suma acumulada
     * Devuelve la suma total
     */
    private static int sumaTotalRecursiva(int[] conjunto, int indice, int suma) {
        if (indice >= conjunto.length) return suma;
        return sumaTotalRecursiva(conjunto, indice + 1, suma + conjunto[indice]);
    }
    
    /**
     * Función recursiva para imprimir una lista
     * lista - Lista a imprimir
     * indice - Índice actual en la lista
     */
    private static void imprimirListaRecursiva(List<Integer> lista, int indice) {
        if (indice >= lista.size()) return;
        System.out.print(lista.get(indice));
        if (indice < lista.size() - 1) {
            System.out.print(" + ");
        }
        imprimirListaRecursiva(lista, indice + 1);
    }
    
    public static void main(String[] args) {
        // Generar conjunto y objetivo aleatorios
        int[] conjunto = generarConjuntoAleatorio(5);
        int objetivo = generarObjetivoAleatorio(conjunto);
        
        // Mostrar información del problema
        System.out.println("Conjunto: " + Arrays.toString(conjunto));
        System.out.println("Suma total: " + sumaTotal(conjunto));
        System.out.println("Objetivo: " + objetivo);
        System.out.println();
        
        // Verificar si existe subconjunto que sume el objetivo
        boolean resultado = existeSubconjunto(conjunto, objetivo);
        System.out.println("¿Existe subconjunto? " + resultado);
        
        // Mostrar también el subconjunto encontrado (si existe)
        System.out.println();
        List<Integer> subconjunto = encontrarSubconjunto(conjunto, objetivo);
        if (subconjunto != null) {
            System.out.print("Verificación: ");
            imprimirListaRecursiva(subconjunto, 0);
            System.out.println(" = " + objetivo);
        } else {
            System.out.println("No se encontró un subconjunto que sume exactamente " + objetivo);
        }
    }
}
