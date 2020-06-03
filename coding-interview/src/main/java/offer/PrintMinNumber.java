package offer;

import java.util.Arrays;

/**
 * 把数组排成最小的数
 */
public class PrintMinNumber {

    public String solution(int []nums){
        if(nums==null||nums.length==0){
            return "";
        }
        int n = nums.length;
        String []strings = new String[n];
        for(int i=0;i<nums.length;i++){
            strings[i] = nums[i]+"";
        }
        Arrays.sort(strings,(s1,s2)->(s1+s2).compareTo(s2+s1));
        StringBuffer stringBuffer = new StringBuffer();
        for(String s:strings){
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }
}
