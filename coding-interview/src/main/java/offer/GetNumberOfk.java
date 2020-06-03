package offer;

public class GetNumberOfk {

    public int getNumberOfK(int []nums,int k){
        int first =  binarySearch(nums,k);
        int last = binarySearch(nums,k+1);

        return (first==nums.length||nums[first]!=k)?0:last-first;
    }

    private int binarySearch(int []nums,int k){
        int l=0,h =nums.length;
        while (l<h){
            int m = l +(h-l)/2;
            if(nums[m]>=k){
                h = m;
            }else{
                l = m+1;
            }
        }
        return l;
    }

}
