import java.io.*;
import java.util.*;

public class KthOrderMaximum {
    public static void FirstKelements(int[] arr,int size,int k){
        //Creating a Min Heap for the given array with only k elements, Creating minheap using Priority Queue
        PriorityQueue<Integer>minHeap=new PriorityQueue<>();
        for(int i=0;i<k;i++){
            minHeap.add(arr[i]);
        }
        //Loop for each element in array after the k th element
        for(int i=k;i<size;i++){
            //if current element is smaller than minimum(top element of minHeap) element, do nothing and continue to next element
            if(minHeap.peek()>arr[i])
                continue;
            //otherwise Change minimum element(topElement of the minHeap) to current element by polling out the top element of minHeap
            else {
                minHeap.poll();
                minHeap.add(arr[i]);
            }
        }

        //Now min heap contains k maximum elements,Iterate and print
            Iterator iterator=minHeap.iterator();

            while(iterator.hasNext()){
                System.out.println(iterator.next()+" ");
            }
    }

    //picks up last element between start and end
    public static int findPivot(int a[],int start,int end){
        int pivot=a[end];
        //Initially partitioning index will be at starting
        int pIndex=start;
        for(int i=start;i<end;i++){
            //If an element is lesser than pivot,swap it
            if(a[i]<pivot){
                int temp=a[i];
                a[i]=a[pIndex];
                a[pIndex]=temp;

                //Increment pIndex for further swapping
                pIndex++;
            }
        }
        //Lastly swapping for the correct position of pivot
        int temp=a[pIndex];
        a[pIndex]=a[end];
        a[end]=temp;
        return pIndex;
    }

    //Picks up random pivot element from start to end

    public static int findRandomPivot(int arr[],int start,int end){
        int n=end-start+1;
        //selecting the random pivot index
        int pivotInd=(int)((Math.random()*1000000)%n);

        //swapping end and pivotInd
        int temp=arr[end];
        arr[end]=arr[start+pivotInd];
        arr[start+pivotInd]=temp;

        int pivot=arr[end];
        //Initialising pivoting point to start index;
        pivotInd=start;

        for(int i=start;i<end;i++){
            if(arr[i]<=pivot){
                int temp1=arr[i];
                arr[i]=arr[pivotInd];
                arr[pivotInd]=temp1;

                //Increment pivot index for further swapping
                pivotInd++;
            }
        }
        //Lastly swapping for the correct position of pivot
        int temp2=arr[pivotInd];
        arr[pivotInd]=arr[end];
        arr[end]=temp2;
        return pivotInd;
    }

    public static void SmallestLargest(int a[],int low,int high,int k,int n){
        if(low==high)
            return;
        else{
            int pivotIndex=findRandomPivot(a,low,high);

            if(k==pivotIndex){
                System.out.println(k+" smallest elements are: ");

                for(int i=0;i<pivotIndex;i++)
                    System.out.print(a[i]+" ");
                System.out.println();

                System.out.println(k+" largest elements are: ");
                for(int i=n-pivotIndex;i<n;i++)
                    System.out.print(a[i]+" ");
                System.out.println();
            } else if (k<pivotIndex) {
                SmallestLargest(a,low,pivotIndex-1,k,n);
            } else if (k>pivotIndex) {
                SmallestLargest(a,pivotIndex+1,high,k,n);
            }
        }
    }



    public static void main(String[] args) {
        int arr[]={11,2,3,4,1,7};
        int size=arr.length;
        int k=3;
        FirstKelements(arr,size,k);

        SmallestLargest(arr,0,size-1,k,size);
    }

}
