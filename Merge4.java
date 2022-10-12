import java.util.Scanner;
public class Merge4 {
        static int min(int a, int b, int c, int d) {
            if (a <= b && a <= c && a <= d) {
                return a;
            }
            else if (b<=a && b<=c && b<=d) {
                return b;
            }
            else if (c <= a && c <= b && c <= d) {
                return c;
            }
            else
                return d;
        }

        static void Merge(int[] T,int l,int mid,int r){
            //finding midpoints
            int mid1=(l+mid)/2;
            int mid2=(mid+1+r)/2;

            //finding sizes with added intMAX
            int size1=mid1-l+1;
            int size2=mid-mid1;
            int size3=mid2-mid;
            int size4=r-mid2;

            int[] A=new int[size1+1];
            int[] B=new int[size2+1];
            int[] C=new int[size3+1];
            int[] D=new int[size4+1];

            int k=l;


            for(int i=0;i<size1;i++){
                A[i]=T[k];
                k++;
            }
            A[size1]=Integer.MAX_VALUE;

            for(int i=0;i<size2;i++){
                B[i]=T[k];
                k++;
            }
            B[size2]=Integer.MAX_VALUE;

            for(int i=0;i<size3;i++){
                C[i]=T[k];
                k++;
            }
            C[size3]=Integer.MAX_VALUE;

            for(int i=0;i<size4;i++){
                D[i]=T[k];
                k++;
            }
            D[size4]=Integer.MAX_VALUE;
            int t1=0, t2=0, t3=0, t4=0;
            k = l;
            while (t1!=size1||t2!=size2||t3!=size3||t4!=size4)
            {

                if (min(A[t1], B[t2], C[t3], D[t4]) == A[t1])
                {

                    T[k] = A[t1];
                    t1++;
                    k++;
                }
                else if (min(A[t1], B[t2], C[t3], D[t4]) == B[t2])
                {
                    T[k] = B[t2];
                    t2++;
                    k++;
                }
                else if (min(A[t1], B[t2], C[t3], D[t4]) == C[t3])
                {
                    T[k] = C[t3];
                    t3++;
                    k++;
                }
                else if (min(A[t1], B[t2], C[t3], D[t4]) == D[t4])
                {
                    T[k] = D[t4];
                    t4++;
                    k++;
                }
            }
        }

        static void mergeSort(int[] T, int l, int r){
            int mid;
            if(l<r){
                mid=(l+r)/2;
                mergeSort(T,l,mid);
                mergeSort(T,mid+1,r);
                Merge(T,l,mid,r);
            }
        }

        public static void main(String[] args) {
            System.out.println("Enter the total number of elements in the array ");
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int[] array=new int[n];

            for(int i=0;i<n;i++){
                array[i]=sc.nextInt();
            }
            mergeSort(array,0,n-1);
            for(int i=0;i<n;i++){
                System.out.print(array[i]+" ");
            }

        }
}
