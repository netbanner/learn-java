package offer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1 和为 S 的两个数字
 */
public class FindNumbersWithSum {

    public ArrayList<Integer> find(int []nums,int sum){
        int i=0,j=nums.length-1;

        while (i<j){
            int cur = nums[i]+nums[j];
            if(cur>sum){
                j--;
            }else if(cur<sum){
                i++;
            }else{
                return new ArrayList<>(Arrays.asList(nums[i],nums[j]));
            }
        }
        return new ArrayList<>();
    }
}
