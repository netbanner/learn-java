package leetcode.dp;

public class EggDrop {

    public int superEggDrop(int k, int n) {
        return dp(k, n);
    }


    private int dp(int k, int n) {
        //第一步创建动态规划备忘录，也叫状态转移矩阵
        int[][] state = new int[k + 1][n + 1];

        //第二步 考虑边界
        for (int i = 0; i <= n; i++) {
            state[0][i] = 0;
            state[1][i] = i;
        }
        for (int i = 1; i <= k; i++) {
            state[i][0] = 0;
            state[i][1] = 1;
        }

        //第三步 状态方程式
        for (int j = 2; j <= k; j++) {
            for (int f = 2; f <= n; f++) {
                int result = Integer.MAX_VALUE;
                //在那一层扔鸡蛋
                for (int drop = 1; drop <= f; drop++) {
                    //碎了
                    int broken = state[j - 1][drop - 1];
                    //未碎
                    int unbroken = state[j][f - drop];

                    int condition = Math.max(broken, unbroken) + 1;
                    result = Math.min(result, condition);
                }
                state[j][f] = result;
            }
        }
        return state[k][n];

    }

    public static void main(String[] args) {
        EggDrop eggDrop = new EggDrop();
       int n = eggDrop.superEggDrop(2, 6);
       System.out.println(n);
    }

}