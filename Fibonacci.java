public class Fibonacci {
    
    /**
     * Función recursiva que calcula el n-ésimo número de Fibonacci
     * n - la posición en la serie de Fibonacci (debe ser >= 0)
     * Devuelve el n-ésimo número de Fibonacci
     */
    public static int fibonacci(int n) {
        // Casos base: F(0) = 0, F(1) = 1
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        // Caso recursivo: F(n) = F(n-1) + F(n-2)
        // La función se llama a sí misma con valores menores
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /**
     * Función recursiva para imprimir la serie de Fibonacci
     * inicio - Valor inicial de la serie a imprimir
     * fin - Valor final de la serie a imprimir
     */
    public static void imprimirFibonacci(int inicio, int fin) {
        // Caso base: se alcanzó el límite superior
        if (inicio > fin) {
            return;
        }
        
        // Imprimir el número de Fibonacci actual
        System.out.println("F(" + inicio + ") = " + fibonacci(inicio));
        
        // Llamada recursiva para el siguiente número
        imprimirFibonacci(inicio + 1, fin);
    }
    
    public static void main(String[] args) {
        // Ejemplos de uso: mostrar los primeros 10 números de Fibonacci
        System.out.println("Serie de Fibonacci (primeros 10 números):");
        imprimirFibonacci(1, 10);
        
    }
}
