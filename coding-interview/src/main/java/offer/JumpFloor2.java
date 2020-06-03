package offer;

import java.util.Arrays;

public class JumpFloor2 {

    public int solution(int n){
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                dp[i] +=dp[j];
            }
        }
        return dp[n-1];
    }

    public int math(int n){
        return (int)Math.pow(2,n-1);
    }
}
