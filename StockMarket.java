public class StockMarket {

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
        for (int i = m; i < h; i++) {
            sum = sum + arr[i];
            if (sum > right_sum)
                right_sum = sum;
            right = i;
        }


        int cross_sum = left_sum + right_sum - arr[m];
        int[] result;
        if (cross_sum > left_sum && cross_sum > right_sum) {
            result=new int[]{cross_sum, left, right};
        }
        else if(left_sum >cross_sum && left_sum > right_sum){
            result = new int[]{left_sum, left, m};
        }
        else{
            result = new int[]{right_sum, m, right};
        }

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

    public static int[] changeArray(int[] profit){
        int[] change=new int[profit.length];
        change[0]=0;
        for(int i=1;i<profit.length;i++){
            change[i]=profit[i]-profit[i-1];
        }
        return change;
    }

    public static void main(String[] args) {
        int[] profit={9,1,2,5,2};
        int[] change=changeArray(profit);
        int[] ans=maxSubArraySum(change,0,change.length-1);
        ans[2]++;

        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+" ");
        }

        for(int i=0;i<change.length;i++){
            System.out.print(change[i]+" ");
        }
    }
}
