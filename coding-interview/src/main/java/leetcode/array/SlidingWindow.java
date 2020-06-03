package leetcode.array;

public class SlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0){
            return null;
        }
        int []a = new int[nums.length-k+1];

        for(int i=0;i<nums.length-2;i++){
            a[i] =   nums[i]+nums[i+1]+nums[i+2];
            System.out.println(a[i]);
        }
        return a;
    }

    public static void main(String []args){
        SlidingWindow slidingWindow = new SlidingWindow();
        int []nums= {1,3,-1,-3,5,3,6,7};
        slidingWindow.maxSlidingWindow(nums,3);
    }

}
