package leetcode.sort;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 最小的 K 个数
 */
public class GetLeastNumbers {

    public ArrayList<Integer> getLeastNumbersK(int []nums,int k){
        ArrayList<Integer> result = new ArrayList<>();
        if(k>nums.length||k<0){
            return result;
        }

        findKthSmallest(nums,k-1);
        /* findKthSmallest 会改变数组，使得前 k 个数都是最小的 k 个数 */
        for(int i=0;i<k;i++){
            result.add(nums[i]);
        }
        return result;
    }

    private void findKthSmallest(int []nums,int k){
        int l=0,h= nums.length-1;
        while (l<h){
            int j = partition(nums,l,h);
            if(j==k){
                break;
            }
            if(j>k){
                h = j-1;
            }else{
                l =j+1;
            }
        }
    }

    private int partition(int []nums,int l,int h){
        int p = nums[l];
        int i = l,j= h+1;
        while (true){
            while (i!=h&&nums[++i]<p){};
            while (j!=l&&nums[--j]>p){};
            if(i>=j){
                break;
            }
            swap(nums,i,j);
        }
        swap(nums,l,j);
        return j;
    }

    private void swap(int []nums,int i,int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public ArrayList<Integer> GetLeastNumbers_Solution(int []nums,int k){
        if(k>nums.length||k<0){
            return new ArrayList<>();
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2-o1));
        for(int num:nums){
            maxHeap.add(num);
            if(maxHeap.size()>k){
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }

     public static void main(String []args){
        int []nums = {1,2,3,4,5,6,7,9,11};
        GetLeastNumbers getLeastNumbers = new GetLeastNumbers();
        getLeastNumbers.GetLeastNumbers_Solution(nums,4);
     }
}
