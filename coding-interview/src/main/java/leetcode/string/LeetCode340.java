package leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class LeetCode340 {

    public static int  solution(String s,int k){

        int j =0;
        if(s==null||s.length()==0) {
            return 0;
        }
        int maxLen = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            while (j < s.length() && map.size() <= k) {
                if(map.size()==k&&!map.containsKey(k)){
                    break;
                }
                map.put(s.charAt(j),map.getOrDefault(s.charAt(j),0)+1);
                j++;
            }
            maxLen = Math.max(maxLen,j-i);
            int count = map.get(s.charAt(i))-1;
            if(count==0){
                map.remove(s.charAt(i));
            }else{
                map.put(s.charAt(i),count);
            }
        }

        return maxLen;
    }

    public static void main(String []args){
        String s = "absdfdfdfefgheihk123";
        int k =4;
        System.out.print(solution(s,k));
    }
}
