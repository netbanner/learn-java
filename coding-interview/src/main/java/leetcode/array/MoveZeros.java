package leetcode.array;
/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 *
 * @author wenhao.zhu
 * @date 2020/4/23
 */
public class MoveZeros {

    public void moveZeros(int [] nums){
        if(nums==null){
            return;
        }

        int j=0;
        for(int i=0;i<nums.length;++i){
            //记录非零的个数j
            if(nums[i]!=0) {
               nums[j++] = nums[i];
            }
        }//将为零的数组值赋值
        for(int i= j;i<nums.length;++i){
            nums[i]=0;
        }
      print(nums);
    }


    public void moveZero2(int []nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int j = 0, i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
        print(nums);
    }

    public static void main(String []args){
        int []num = {1,0,2,4,0,3,5,6,4,0,0,12};
        MoveZeros moveZeros = new MoveZeros();
        moveZeros.moveZeros(num);
        System.out.println("---------");
        moveZeros.moveZero2(num);

    }

    private void print(int[]num){
        for(int i=0;i<num.length; i++){
            System.out.print(num[i]+",");
        }
    }
}
