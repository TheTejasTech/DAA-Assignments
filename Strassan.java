public class Strassan {

    public static void main(String[] args) {
    int[][] A = {
            { 1, 2, 3, 4 },
            { 3, 4, 5, 6 },
            { 1, 2, 3, 4 },
            { 3, 4, 5, 6 }
    };

    int[][] B = {
            { 5, 6, 7, 8 },
            { 7, 8, 9, 10 },
            { 1, 2, 3, 4 },
            { 3, 4, 5, 6 }
    };

    System.out.println("Matrix A:");
    printf(A);
    System.out.println("Matrix B:");
    printf(B);

    // ⏱ Start time
    long startTime = System.nanoTime();

    int[][] result = strassan(A, B);

    // ⏱ End time
    long endTime = System.nanoTime();

    System.out.println("Product of A and B using Strassen's algorithm:");
    printf(result);

    // ⏱ Execution time
    long durationNano = endTime - startTime;
    double durationMillis = durationNano / 1_000_000.0;

    System.out.println("Execution Time:");
    // System.out.println("Nanoseconds: " + durationNano);
    System.out.println("Milliseconds: " + durationMillis + " ms");
}
// 
    // public static void main(String[] args) {
    //     int[][] A = {
    //             { 1, 2, 3, 4 },
    //             { 3, 4, 5, 6 },
    //             { 1, 2, 3, 4 },
    //             { 3, 4, 5, 6 }
    //     };

    //     int[][] B = {
    //             { 5, 6, 7, 8 },
    //             { 7, 8, 9, 10 },
    //             { 1, 2, 3, 4 },
    //             { 3, 4, 5, 6 } 
    //     };
    //     // Display input matrices
    //     System.out.println("Matrix A:");
    //     printf(A);
    //     System.out.println("Matrix B:");
    //     printf(B);

    //     int[][] result = strassan(A, B);

    //     System.out.println("Product of A and B using Strassen's algorithm:");
    //     printf(result);
    // }

    // Strassen recursive multiplication
    public static int[][] strassan(int[][] A, int[][] B) {
        int n = A.length;
        int a = A[0].length;
        int b = B[0].length;
        if (a != b) {
            System.out.println("'matrix multiplication is not possible'");
            System.exit(0);
        }
        // Base case: 1x1 matrix
        int a1 = A[0].length;
        int b1 = B.length;
        if (a1 != b1) {
            System.out.println("'matrix multiplication is not possible'");
            System.exit(0);
        }
        if (n == 1) {
            int[][] ans = new int[1][1];
            ans[0][0] = A[0][0] * B[0][0];
            return ans;
        }

        int m = n / 2;

        // Create submatrices
        int[][] A11 = new int[m][m];
        int[][] A12 = new int[m][m];
        int[][] A21 = new int[m][m];
        int[][] A22 = new int[m][m];

        int[][] B11 = new int[m][m];
        int[][] B12 = new int[m][m];
        int[][] B21 = new int[m][m];
        int[][] B22 = new int[m][m];

        // Split matrices into quadrants
        split(A, A11, 0, 0); // top-left
        split(A, A12, 0, m); // top-right
        split(A, A21, m, 0); // bottom-left
        split(A, A22, m, m); // bottom-right

        split(B, B11, 0, 0);
        split(B, B12, 0, m);
        split(B, B21, m, 0);
        split(B, B22, m, m);

        // Compute 7 products recursively using original names
        int[][] p = strassan(add(A11, A22), add(B11, B22));
        int[][] q = strassan(add(A21, A22), B11);
        int[][] r = strassan(A11, sub(B12, B22));
        int[][] s = strassan(A22, sub(B21, B11));
        int[][] t = strassan(add(A11, A12), B22);
        int[][] u = strassan(sub(A21, A11), add(B11, B12));
        int[][] v = strassan(sub(A12, A22), add(B21, B22));

        // Combine the 7 products to get submatrices of result
        int[][] C11 = sub(add(p, s), add(t, v));
        int[][] C12 = add(r, t);
        int[][] C21 = add(q, s);
        int[][] C22 = sub(add(p, r), add(q, u));

        // Join submatrices into final result
        int[][] ans = new int[n][n];
        join(C11, ans, 0, 0);
        join(C12, ans, 0, m);
        join(C21, ans, m, 0);
        join(C22, ans, m, m);

        return ans;
    }

    // Add two matrices
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // Subtract two matrices
    public static int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    // Split parent matrix into child matrix
    public static void split(int[][] parent, int[][] child, int iB, int jB) {
        for (int i = 0; i < child.length; i++)
            for (int j = 0; j < child.length; j++)
                child[i][j] = parent[i + iB][j + jB];
    }

    // Join child matrix into parent matrix
    public static void join(int[][] child, int[][] parent, int iB, int jB) {
        for (int i = 0; i < child.length; i++)
            for (int j = 0; j < child.length; j++)
                parent[i + iB][j + jB] = child[i][j];
    }

    // Print a matrix
    public static void printf(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
