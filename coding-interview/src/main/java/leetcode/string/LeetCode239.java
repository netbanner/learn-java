package leetcode.string;

import java.util.ArrayDeque;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode239 {

    /**
     * 暴力破解法
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindwos(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return null;
        }

        int[] a = new int[nums.length - k + 1];
        int[] temp = new int[k];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                max = Math.max(max, nums[i + j]);
            }

            a[i] = max;
        }
        return a;
    }

    ArrayDeque<Integer> deq = new ArrayDeque<>();
    int[] nums;

    public void clean_deque(int i, int k) {
        if (!deq.isEmpty() && deq.getFirst() == i - k) {
            // remove indexes of elements not from sliding window
            deq.removeFirst();

            //remove from deq indexes of all elements
            //which are smaller than current element nums[i
            while ((!deq.isEmpty() && nums[i] > nums[deq.getLast()])) {
                deq.removeLast();
            }
        }
    }

    /**
     * 双向队列
     *
     * @param nums
     * @param k
     * @return
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            //compute max in nums[k;
            if (nums[i] > nums[max_idx]) {
                max_idx = i;
            }
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        //build output
        for (int i = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;

    }


    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] a = maxSlidingWindwos(nums, 3);
        System.out.print(a);
    }


}
