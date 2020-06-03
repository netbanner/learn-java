package offer;

import java.text.DecimalFormat;

/**
 * n 个骰子的点数
 * 把n个骰子仍在地上，所有骰子朝上一面的点数之和为s，输入n，打印出s的所有可能的值出现的概率。
 */
public class DicesSum {

    public static void print(int n){
        if(n<1){
            return;
        }
        int maxSum = 6*n;
        int [][]probabilities = new int[2][maxSum+1];

        int flag = 0;
        //初始化第一个骰子
        for(int i=1;i<=6;i++){
            probabilities[flag][i] =1;
        }
        //第二个骰子到第n个骰子
        for(int k=2;k<=n;k++){
            flag = 1- flag;
            // 当骰子数为k，那么sum的范围为k到k*g_maxValue
            for(int i=1;i<k;i++){
                probabilities[flag][i] = 0;
            }
            //k个骰子的最小和为k，k之前的数设为0
            for(int i=k;i<=6*k;i++){
                int count =1;
                probabilities[flag][i] = 0;
                while (i-count>0&&count<=6){
                    probabilities[flag][i] +=probabilities[i-flag][i-count];
                    count++;
                }
            }
        }
        double total = Math.pow((double)6,(double)n);
        DecimalFormat df = new DecimalFormat("0.000");
        for(int i=n;i<=maxSum;i++){
            System.out.print(df.format(probabilities[flag][i] / total) + " ");
        }


    }
}
