package leetcode.array;

public class RemoveElements {


    public static int  remove(int []nums,int val){

        if(nums==null||nums.length==0){
            return 0;
        }
        int i=0;
        for(int j=0;j<nums.length;++j){
            if(nums[j]!=val){
                nums[i++] = nums[j];
            }
        }

        return i;
    }
    public static void main(String []args){
        int []nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(remove(nums,1));
    }
}
