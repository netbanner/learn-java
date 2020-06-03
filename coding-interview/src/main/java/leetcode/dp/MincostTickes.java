package leetcode.dp;

public class MincostTickes {

        public static int mincostTickets(int[] days, int[] costs) {
            int dp[] =new int[days[days.length-1]+1];
            for(int i=0;i<days.length;i++){
                dp[days[i]] = -1;
            }
            System.out.println(dp);
            for(int i=1;i<dp.length;i++){
                if(dp[i]==0){
                    dp[i] =dp[i-1];
                }else{
                    dp[i] = Math.min(costs[0]+dp[i-1],Math.min(i>=7?costs[1]+dp[i-7]:costs[1],i>=30?costs[2]+dp[i-30]:costs[2]));
                }
                System.out.print("--");
                System.out.print(dp[i]);
            }

            return dp[dp.length-1];
        }


        public static void main(String []args){
            int []days = {1,4,6,7,9,20};
            int []costs = {2,7,15};
            mincostTickets(days,costs);
        }
}
