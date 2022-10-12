public class InsertionSort {
    public static void insertionSort(int [] A){
        int i,j,key;
        for(j=2;j<A.length;j++){
            key=A[j];
            //Insert A[j] in the sorted sequence A[1...j-1]
            i=j-1;
            while(i>=0 && A[i]>key){
                A[i+1]=A[i];
                i--;
            }
            A[i+1]=key;
        }
    }
    public static void main(String[] args) {
        int[] A={5,8,3,1,6,2,3,4,7,8,9,6,3,1,3,5,7,8,9,0,3,4,5,6,7,8,9,2,1,4,6,7,8,9,0,4,3,2,4,5,6,7,7,8,9,9};
        insertionSort(A);
        for(int i=0;i<A.length;i++){
            System.out.print(A[i]+" ");
        }


    }
}
