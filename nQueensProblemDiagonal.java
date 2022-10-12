import  java.util.Arrays;
import java.util.Scanner;

class nQueensClass{
    public static int count=0;
    // Function to check if two queens threaten each other or not
    private static boolean isSafe(char[][] Board, int row, int col) {
        // return false if two queens share the same column
        for (int i = 0; i < row; i++)
        {
            if (Board[i][col] == 'Q') {
                return false;
            }
        }

        // return false if two queens share the same `\` diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
        {
            if (Board[i][j] == 'Q') {
                return false;
            }
        }

        // return false if two queens share the same `/` diagonal
        for (int i = row, j = col; i >= 0 && j < Board.length; i--, j++)
        {
            if (Board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private static boolean isSafe2(char[][] Board, int row, int col) {
        // return false if two queens share the same column
        for (int i = 0; i < row; i++)
        {
            if (Board[i][col] == 'Q') {
                return false;
            }
        }
        return true;
    }

    protected static void printSolution(char[][] Board){
        for(char[] chars: Board){
            //while printing arrays directly, it places comma s- so to get rid of them we use replace
            System.out.println(Arrays.toString(chars).replace(",",""));
        }
        System.out.println();
    }

    protected static void nQueen(char[][] Board,int row){
        //if 'N' queens are placed successfully, print the solutions
        if(row>=Board.length){
            printSolution(Board);
            count++;
            return;
        }
        //place queen at every square in the current row 'r' and recur for each valid move
        for(int i=0;i<Board.length;i++){
            //If no two queens threaten each other
            if(isSafe2(Board,row,i)){
                //place queen at the current square
                Board[row][i]='Q';

                //recur for the next row
                nQueen(Board,row+1);

                //backtrack and remove the queen from the current square
                Board[row][i]='x';

            }
        }

    }

    protected static boolean nQueenOneSolution(char[][]Board,int row){
        if(row>=Board.length){
            printSolution(Board);
            count=1;
            return true;}
        for(int col=0;col<Board.length;col++){
            if(isSafe(Board,row,col)){
                Board[row][col]='Q';
                if(nQueenOneSolution(Board,row+1)){
                    return true;
                }
                Board[row][col]='x';
            }
        }
        return false;
    }
}

public class nQueensProblemDiagonal {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of Chess Board");
        int n=sc.nextInt();
        char[][] Board=new char[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(Board[i],'x');
        }

        //one nQueens solution
        //--->nQueensClass.nQueenOneSolution(Board,0);

        //multiple nQueens solutions
        nQueensClass.nQueen(Board,0);
        if(nQueensClass.count==0){
            System.out.println("No solution exists!");
        }


    }
}
