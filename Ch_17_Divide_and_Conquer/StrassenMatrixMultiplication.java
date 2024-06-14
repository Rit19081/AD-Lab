package Ch_17_Divide_and_Conquer;

import java.util.Arrays;

public class StrassenMatrixMultiplication {

    public static int[][] multiplication(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        if (n == 1) {

            result[0][0] = A[0][0] * B[0][0];
        } else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            // Divide matrices A and B into 4 sub-matrices  

            splitMatrix(A, A11, 0, 0);

            splitMatrix(A, A12, 0, n / 2);

            splitMatrix(A, A21, n / 2, 0);

            splitMatrix(A, A22, n / 2, n / 2);

            splitMatrix(B, B11, 0, 0);

            splitMatrix(B, B12, 0, n / 2);

            splitMatrix(B, B21, n / 2, 0);

            splitMatrix(B, B22, n / 2, n / 2);

            // Recursive steps for Strassen's algorithm  
            int[][] P1 = multiplication(A11, subtractMatrix(B12, B22));
            int[][] P2 = multiplication(addMatrix(A11, A12), B22);
            int[][] P3 = multiplication(addMatrix(A21, A22), B11);
            int[][] P4 = multiplication(A22, subtractMatrix(B21, B11));
            int[][] P5 = multiplication(addMatrix(A11, A22), addMatrix(B11, B22));
            int[][] P6 = multiplication(subtractMatrix(A12, A22), addMatrix(B21, B22));
            int[][] P7 = multiplication(subtractMatrix(A11, A21), addMatrix(B11, B12));

            // Calculate sub-matrices of the result  
            int[][] C11 = addMatrix(subtractMatrix(addMatrix(P5, P4), P2), P6);
            int[][] C12 = addMatrix(P1, P2);
            int[][] C21 = addMatrix(P3, P4);
            int[][] C22 = subtractMatrix(subtractMatrix(addMatrix(P5, P1), P3), P7);

            // Merge sub-matrices into the result matrix  

            mergeMatrix(C11, result, 0, 0);

            mergeMatrix(C12, result, 0, n / 2);

            mergeMatrix(C21, result, n / 2, 0);

            mergeMatrix(C22, result, n / 2, n / 2);
        }

        return result;
    }

    public static void splitMatrix(int[][] parent, int[][] child, int rowOffset, int colOffset) {
        for (int i = 0; i < child.length; i++) {
            for (int j = 0; j < child.length; j++) {

                child[i][j] = parent[i + rowOffset][j + colOffset];
            }
        }
    }

    public static void mergeMatrix(int[][] child, int[][] parent, int rowOffset, int colOffset) {
        for (int i = 0; i < child.length; i++) {
            for (int j = 0; j < child.length; j++) {

                parent[i + rowOffset][j + colOffset] = child[i][j];
            }
        }
    }

    public static int[][] addMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    public static int[][] subtractMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};

        int[][] result = multiplication(A, B);

        // Output  
        System.out.println("Matrix A:");

        printMatrix(A);

        System.out.println("Matrix B:");

        printMatrix(B);

        System.out.println("Resultant Matrix (A * B):");

        printMatrix(result);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {

            System.out.println(Arrays.toString(row));
        }

        System.out.println();
    }
}  