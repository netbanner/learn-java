package offer;

public class ReOrderArray {

    public void order(int[] array) {
        int count = 0;
        //奇数个数
        for (int a : array) {
            if (a % 2 != 0) {
                count++;
            }
        }
        int[] nums = array.clone();
        int i = 0, j = count;
        for (int a : array) {
            if (a % 2 == 0) {
                nums[i++] = a;
            } else {
                nums[j++] = a;
            }
        }
    }
}
