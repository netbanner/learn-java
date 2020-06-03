package leetcode.array;

public class RemoveDuplicates {


    public static int remove(int[] num) {
        if (num == null || (num.length == 0)) {
            return 0;
        }
        int i=0;
        for(int j=1;j<num.length;j++){
            if(num[j]!=num[i]){
                i++;
                num[i] = num[j];
            }
        }

        return i+1;
    }

    public static void main(String []args){
        int []nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(remove(nums));

    }
}
