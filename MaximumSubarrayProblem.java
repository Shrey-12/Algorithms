//Brute force algorithm
import static java.lang.Math.max;

public class MaximumSubarrayProblem {

    //we calculate subarrays of same size together
    public static int MaximumSumSubarrayON3(int[] arr) {
        int ans=Integer.MIN_VALUE;
        for (int subArraySize = 1; subArraySize <= arr.length; subArraySize++) {
            for (int startIndex = 0; startIndex < arr.length; startIndex++) {
                if (startIndex + subArraySize > arr.length) //subarray exceeds bounds
                    break;
                int sum=0;
                for(int i=startIndex;i<startIndex+subArraySize;i++){
                    sum+=arr[i];
                }
                ans= max(ans,sum);
            }
        }
        return ans;
    }

    //size k -1 sum+ new element = size k sum
    public static int MaximumSubArrayON2(int [] arr){
        int ans=Integer.MIN_VALUE;
        for(int startIndex=0;startIndex<arr.length;startIndex++){
            int sum=0;
            for(int subArraySize=1;subArraySize<arr.length;subArraySize++){
                if (startIndex + subArraySize > arr.length) //subarray exceeds bounds
                    break;
                sum+=arr[startIndex+subArraySize-1]; //Last element of the new subarray
                ans=max(ans,sum);
            }
        }
        return ans;
    }

    //Divide and Conquer
    //Finds maximum possible sum in arr[], such that arr[m] is a part of it
    static int[] maxCrossingSum(int arr[], int l, int m,
                                int h) {
        int sum = 0;
        int left_sum = Integer.MIN_VALUE;
        int left = m;
        for (int i = m; i >= l; i--) {
            sum = sum + arr[i];
            if (sum > left_sum)
                left_sum = sum;
            left = i;
        }

        sum = 0;
        int right_sum = Integer.MIN_VALUE;
        int right = m;
        for (int i = m; i <= h; i++) {
            sum = sum + arr[i];
            if (sum > right_sum)
                right_sum = sum;
            right = i;
        }
        int max = Math.max(left_sum + right_sum - arr[m],
                Math.max(left_sum, right_sum));
        int[] result = { max, left, right };
        return result;
    }

    static int[] maxSubArraySum(int arr[], int l, int h) {
        if (l > h) {
            int[] result = { Integer.MIN_VALUE, 0, 0 };
            return result;
        }
        if (l == h) {
            int[] result = { arr[l], 0, 0 };
            return result;
        }

        int m = (l + h) / 2;
        int[] result;
        int[] left = maxSubArraySum(arr, l, m - 1);
        int[] right = maxSubArraySum(arr, m + 1, h);
        int[] cross = maxCrossingSum(arr, l, m, h);

        if (left[0] > right[0] && left[0] > cross[0]) {
            result = left;

        } else if (cross[0] > left[0] && cross[0] > right[0]) {
            result = cross;
        } else {
            result = right;
        }

        return result;
    }

    //Kadanes algorithm
    public static int MaximumSumSubarrayKadane(int arr[]){
        int ans=0, sum=0;
        for(int i=0;i<arr.length;i++){
            if(sum+arr[i]>0)
                sum+=arr[i];
            else sum=0;
            ans=max(ans,sum);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] A={1,2,3,-5};
        System.out.println(MaximumSumSubarrayON3(A));
        System.out.println(MaximumSubArrayON2(A));
        System.out.println(MaximumSumSubarrayKadane(A));
        int arr[] = { 2, 3, -4, 5, 7 };
        int n = arr.length;
        int[] max_sum = maxSubArraySum(arr, 0, n - 1);

        System.out.println("Maximum contiguous sum is "
                + max_sum[0] + " " + max_sum[1] + " " + max_sum[2]);
    }

}
