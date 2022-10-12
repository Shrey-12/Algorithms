import java.util.Arrays;
public class MatrixChainMultiplication {
    //exponential time complexity
        static int MatrixChainOrder(int p[], int i, int j,int[][] subproblems)
        {
            if (i == j)
                return 0;
            subproblems[i][j]++;


            int min = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                int count = MatrixChainOrder(p, i, k,subproblems) + MatrixChainOrder(p, k + 1, j,subproblems) + p[i - 1] * p[k] * p[j];
                if (count < min)
                    min = count;
            }
            return min;
        }

        // Recursive 2D array: Time complexity O(n^3)
        static int[][] mem = new int[100][100];
        static int storedRec(int[] matrices,int[][] subProblems,int i,int j) {
            if (i == j) {
                return 0;
            }

            if (mem[i][j] != -1) {
                return mem[i][j];
            }
            subProblems[i][j]++;
            int minCost = Integer.MAX_VALUE;

            for (int iter = i; iter < j; iter++) {
                int cost1, cost2;

                if (mem[i][iter] != -1) {
                    cost1 = mem[i][iter];
                } else {
                    cost1 = storedRec(matrices, subProblems, i, iter);
                }

                if (mem[iter + 1][j] != -1) {
                    cost2 = mem[iter + 1][j];
                } else {
                    cost2 = storedRec(matrices, subProblems, iter + 1, j);
                }

                int cost = cost1 + cost2 + matrices[i - 1] * matrices[iter] * matrices[j];

                if (cost < minCost) {
                    minCost = cost;
                }
            }

            mem[i][j] = minCost;
            return minCost;
        }

       //Iterative approach
    static int matrixMultiplicationIterative(int matrices[],int[][]subProblems){
        int n = matrices.length;
        int memIter[][] = new int[n][n];

        int i, j, k, chainLength, q;

        for (i = 1; i < n; i++)
            memIter[i][i] = 0;

        for (chainLength = 2; chainLength < n; chainLength++) {
            for (i = 1; i < n - chainLength + 1; i++) {
                j = i + chainLength - 1;
                if (j == n)
                    continue;
                subProblems[i][j]++;
                memIter[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++) {
                    q = memIter[i][k] + memIter[k + 1][j]
                            + matrices[i - 1] * matrices[k] * matrices[j];
                    if (q < memIter[i][j])
                        memIter[i][j] = q;
                }
            }
        }
        return memIter[1][n - 1];
    }


        static void print2Darray(int[][] arr){
            for(int i=1;i< arr.length;i++){
                for(int j=1;j<arr[1].length;j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
        }
        public static void main(String args[]) {
            int arr[] = new int[] { 1, 2, 3, 4, 3 };
            int N = arr.length;
            int[][] subproblems1=new int[N][N];
            int[][] subproblems2=new int[N][N];
            int[][] subproblems3=new int[N][N];


           System.out.println("Minimum number of multiplications is " + MatrixChainOrder(arr, 1, N - 1,subproblems1));
           print2Darray(subproblems1);

          for (int[] row : mem)
               Arrays.fill(row, -1);

           System.out.println("Minimum number of multiplications is " + storedRec(arr,subproblems2,1,N-1));
           print2Darray(subproblems2);

            System.out.println("Minimum number of multiplications is "+matrixMultiplicationIterative(arr,subproblems3));
            print2Darray(subproblems3);


        }
    }

