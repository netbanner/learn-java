package offer;

public class IsContinuous {

    public boolean isContinuous(int []nums){
        int cnt =0;
        if(nums.length<5){
            return false;
        }
        for(int num:nums){
            if(num==0){
                cnt++;
            }
        }
        for(int i=cnt;i<nums.length-1;i++){
            if(nums[i+1]==nums[i]){
                return false;
            }
            cnt -=nums[i+1]-nums[i]-1;
        }

        return cnt>=0;
    }
}
