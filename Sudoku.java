import java.util.Random;

public class Sudoku {
    // Constantes para el tamaño del tablero y subcuadrículas
    private static final int SIZE = 9;          // Tamaño del tablero (9x9)
    private static final int BOX_SIZE = 3;      // Tamaño de subcuadrícula (3x3)
    private static final int EMPTY = 0;         // Valor que representa celda vacía
    private static final Random rand = new Random(); // Generador de números aleatorios

    public static void main(String[] args) {
        // Generar un Sudoku aleatorio con algunas celdas vacías
        int[][] board = new int[SIZE][SIZE];
        solveSudoku(board); // Genera una solución completa
        removeNumbersRecursive(board, 40, 0); // Elimina 40 números para crear el puzzle
        
        System.out.println("Sudoku generado:");
        printBoardRecursive(board, 0, 0);
        
        // Intentar resolver el Sudoku usando backtracking
        if (solveSudoku(board)) {
            System.out.println("\nSolución encontrada:");
            printBoardRecursive(board, 0, 0);
        } else {
            System.out.println("\nNo existe solución para este tablero.");
        }
    }

    /**
     * Elimina números del tablero recursivamente para crear el puzzle
     * board - Tablero de Sudoku completo
     * cellsToRemove - Cantidad total de celdas a vaciar
     * removed - Contador de celdas ya vaciadas
     */
    private static void removeNumbersRecursive(int[][] board, int cellsToRemove, int removed) {
        // Caso base: se alcanzó el número deseado de celdas vacías
        if (removed >= cellsToRemove) return;
        
        // Seleccionar posición aleatoria
        int row = rand.nextInt(SIZE);
        int col = rand.nextInt(SIZE);
        
        // Vaciar celda si no está vacía
        if (board[row][col] != EMPTY) {
            board[row][col] = EMPTY;
            removeNumbersRecursive(board, cellsToRemove, removed + 1);
        } else {
            // Intentar con otra celda si esta ya está vacía
            removeNumbersRecursive(board, cellsToRemove, removed);
        }
    }

    /**
     * Algoritmo de backtracking para resolver el Sudoku
     * board - Tablero de Sudoku a resolver
     * Devuelve true si se encontró una solución, false en caso contrario
     */
    private static boolean solveSudoku(int[][] board) {
        // Iniciar la resolución recursiva desde la posición (0,0)
        return solveSudokuRecursive(board, 0, 0);
    }

    /**
     * Función recursiva que resuelve el Sudoku celda por celda
     * board - Tablero de Sudoku
     * row - Fila actual
     * col - Columna actual
     * Devuelve true si se encontró una solución
     */
    private static boolean solveSudokuRecursive(int[][] board, int row, int col) {
        // Caso base: se completó todo el tablero
        if (row >= SIZE) return true;
        
        // Caso base: se completó la fila actual, pasar a la siguiente fila
        if (col >= SIZE) return solveSudokuRecursive(board, row + 1, 0);
        
        // Si la celda ya está llena, pasar a la siguiente
        if (board[row][col] != EMPTY) {
            return solveSudokuRecursive(board, row, col + 1);
        }
        
        // Celda vacía: probar números del 1 al 9
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArrayRecursive(numbers, numbers.length - 1); // Mezclar para aleatoriedad
        
        // Probar cada número posible en la celda vacía
        for (int num : numbers) {
            if (isValidRecursive(board, row, col, num, 0)) {
                board[row][col] = num;
                if (solveSudokuRecursive(board, row, col + 1)) return true; // Recursión
                board[row][col] = EMPTY; // Backtracking
            }
        }
        return false; // No se encontró número válido
    }

    /**
     * Mezcla un array recursivamente usando el algoritmo Fisher-Yates
     * array - Array a mezclar
     * index - Índice actual para el intercambio
     */
    private static void shuffleArrayRecursive(int[] array, int index) {
        // Caso base: se completó la mezcla
        if (index <= 0) return;
        
        // Intercambiar elemento en índice aleatorio
        int randomIndex = rand.nextInt(index + 1);
        int temp = array[randomIndex];
        array[randomIndex] = array[index];
        array[index] = temp;
        
        // Llamada recursiva para el siguiente índice
        shuffleArrayRecursive(array, index - 1);
    }

    /**
     * Verifica si es válido colocar un número en una posición específica
     * board - Tablero de Sudoku
     * row - Fila de la celda
     * col - Columna de la celda
     * num - Número a verificar
     * index - Índice actual para verificación
     * Devuelve true si el número puede colocarse, false en caso contrario
     */
    private static boolean isValidRecursive(int[][] board, int row, int col, int num, int index) {
        // Caso base: se verificaron todas las posiciones en fila/columna
        if (index >= SIZE) {
            // Verificar subcuadrícula 3x3
            int boxRow = row - row % BOX_SIZE; // Fila inicial del cuadro 3x3
            int boxCol = col - col % BOX_SIZE; // Columna inicial del cuadro 3x3
            return isValidBoxRecursive(board, boxRow, boxCol, num, 0, 0);
        }
        
        // Verificar fila: el número no debe repetirse en la misma fila
        if (board[row][index] == num) return false;
        
        // Verificar columna: el número no debe repetirse en la misma columna
        if (board[index][col] == num) return false;
        
        // Llamada recursiva para el siguiente índice
        return isValidRecursive(board, row, col, num, index + 1);
    }

    /**
     * Verifica si el número existe en la subcuadrícula 3x3
     * board - Tablero de Sudoku
     * startRow - Fila inicial del cuadro
     * startCol - Columna inicial del cuadro
     * num - Número a verificar
     * r - Fila relativa dentro del cuadro
     * c - Columna relativa dentro del cuadro
     * Devuelve false si el número ya existe en el cuadro
     */
    private static boolean isValidBoxRecursive(int[][] board, int startRow, int startCol, int num, int r, int c) {
        // Caso base: se completó la verificación del cuadro
        if (r >= BOX_SIZE) return true;
        
        // Caso base: se completó la fila actual del cuadro
        if (c >= BOX_SIZE) return isValidBoxRecursive(board, startRow, startCol, num, r + 1, 0);
        
        // Verificar si el número ya existe en esta posición del cuadro
        if (board[startRow + r][startCol + c] == num) return false;
        
        // Llamada recursiva para la siguiente columna
        return isValidBoxRecursive(board, startRow, startCol, num, r, c + 1);
    }

    /**
     * Imprime el tablero de Sudoku en formato legible de forma recursiva
     * board - Tablero de Sudoku a imprimir
     * row - Fila actual
     * col - Columna actual
     */
    private static void printBoardRecursive(int[][] board, int row, int col) {
        // Caso base: se completó todo el tablero
        if (row >= SIZE) return;
        
        // Caso base: se completó la fila actual
        if (col >= SIZE) {
            System.out.println();
            // Línea separadora cada 3 filas
            if ((row + 1) % BOX_SIZE == 0 && row + 1 < SIZE) {
                System.out.println("------+-------+------");
            }
            // Pasar a la siguiente fila
            printBoardRecursive(board, row + 1, 0);
            return;
        }
        
        // Separador vertical cada 3 columnas
        if (col % BOX_SIZE == 0 && col != 0) {
            System.out.print("| ");
        }
        
        // Imprimir número o punto para celdas vacías
        System.out.print(board[row][col] == EMPTY ? ". " : board[row][col] + " ");
        
        // Llamada recursiva para la siguiente columna
        printBoardRecursive(board, row, col + 1);
    }
}