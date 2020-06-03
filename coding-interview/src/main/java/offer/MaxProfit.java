package offer;

/**
 *  股票的最大利润
 */
public class MaxProfit {

    public int maxProfit(int []prices){
        if(prices==null||prices.length==0){
            return 0;
        }
        int min = prices[0];
        int maxProfit = 0;
        for(int i=1;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            maxProfit= Math.max(maxProfit,prices[i]-min);
        }
        return maxProfit;
    }
}
