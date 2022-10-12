import java.util.Scanner;
public class MatrixMultiplication {
    public static int[][] MatrixMultiplicationNaive(int[][]A, int[][]B) {
        int r1 = A.length;
        int c1 = A[0].length;
        int r2 = B.length;
        int c2 = B[0].length;
        int C[][] = new int[r1][c2];

            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C[0].length; j++) {
                    for (int k = 0; k < r2; k++) {
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        return C;
    }

    public static int[][] matrixMultiplicationFinal(int[][] A, int[][] B){
        return  matrixMultiplication(A, B, 0, 0, 0,0, A.length);
    }


    public static int[][] matrixMultiplication(int[][] A, int[][] B, int rowA, int colA, int rowB, int colB, int size){
        //new matrix
        int[][] C= new int[size][size];
        //when there is only one element present
        if(size==1)
            C[0][0]= A[rowA][colA]*B[rowB][colB];
        else{
            int newSize= size/2;
            //C11=(A11,B11)+(A12,B21)
            sumMatrix(C, matrixMultiplication(A, B, rowA, colA, rowB, colB, newSize), matrixMultiplication(A, B, rowA, colA+newSize, rowB+ newSize, colB, newSize), 0, 0);
            sumMatrix(C, matrixMultiplication(A, B, rowA, colA, rowB, colB + newSize, newSize), matrixMultiplication(A, B, rowA, colA+newSize, rowB+ newSize, colB+newSize, newSize), 0, newSize);
            sumMatrix(C, matrixMultiplication(A, B, rowA+ newSize, colA, rowB, colB, newSize), matrixMultiplication(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB, newSize), newSize, 0);
            sumMatrix(C, matrixMultiplication(A, B, rowA+ newSize, colA, rowB, colB+newSize, newSize), matrixMultiplication(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB+newSize, newSize), newSize, newSize);
        }
        return C;
    }
    private static void sumMatrix(int[][] C, int[][]A, int[][]B,int rowC, int colC){
        int n=A.length;
        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++)
                C[i+rowC][j+colC]=A[i][j]+B[i][j];
        }
    }



    public static void main(String[] args) {
        System.out.println("Enter the number n: nxn matrix");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int A[][]=new int[n][n];
        int B[][]=new int[n][n];

        System.out.println("Enter Matrix A: \n");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                A[i][j]=sc.nextInt();
            }
        }

        System.out.println("Enter Matrix B: \n");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                B[i][j]=sc.nextInt();
            }
        }

        int [][] C= matrixMultiplicationFinal(A,B);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(C[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();


        C=MatrixMultiplicationNaive(A,B);
        for(int i=0;i<C.length;i++){
            for(int j=0;j<C[0].length;j++){
                System.out.print(C[i][j]+" ");
            }
            System.out.println();
        }
    }
}
