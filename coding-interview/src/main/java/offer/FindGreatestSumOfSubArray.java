package offer;

/**
 * 连续子数组的最大和
 */
public class FindGreatestSumOfSubArray {


    public int solution(int[] array) {
        if (array == null | array.length == 0) {
            return -1;
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int a : array) {
            sum = sum <=0 ? a : sum + a;
            max = Math.max(max,sum);
        }
        return max;
    }
}
