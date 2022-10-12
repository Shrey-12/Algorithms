public class MinimumCoins {

    public static int findMinCoins(int[] coins, int value,int[] ans){
        int i, count=0;
        for(i=coins.length-1;i>0;i--){
            //take maximum value from coins[i]
            while(value>=coins[i] && count <ans.length){
                value-=coins[i];
                ans[count]=coins[i];
                count++;
            }
            if(value==0)
                break;
        }
        return count;
    }

    public static void main(String[] args) {
        int coins[]={1,5,10,20,50};
        int value=105;
        int ans[]=new int[100];

        int count=findMinCoins(coins,value,ans);
        for(int i=0;i<count;i++){
            System.out.print(ans[i]+" ");
        }

    }
}
