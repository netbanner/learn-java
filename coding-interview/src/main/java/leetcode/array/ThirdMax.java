package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * @date 2020/4/27
 */
public class ThirdMax {

    public int thirdMax(int []nums){
        Set set = new HashSet();
        //将数组从小到大排序
        Arrays.sort(nums);
        if(nums.length<3){
            return nums[nums.length-1];
        }
        int j=0,temp=0;
        for(int i=nums.length-1;i>0;i--){

            set.add(nums[i]);
            if(nums[i]>nums[i-1]){
                j++;
            }
            // 找出第三大的值，返回
            if(j==3){
                temp = i;
                break;
            }
        }

        if(set.size()<=3){
            return nums[0];
        }

        return nums[temp];
    }


    public static void main(String []args){
        int []nums = {1,2,2,4};
        Arrays.sort(nums);
        for(int i:nums){
            System.out.println(i);
        }
        ThirdMax thirdMax = new ThirdMax();
        System.out.println(thirdMax.thirdMax(nums));
    }
}
