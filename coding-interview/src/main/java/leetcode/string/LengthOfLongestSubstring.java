package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LengthOfLongestSubstring {


    public int lengthOfLongestSubstring(String s){
        if(s==null){
            return 0;
        }
        int len = s.length();
        List<Character> list = new ArrayList<Character>();
        int count =0;
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(list.contains(s.charAt(j))){
                    break;
                }else{
                    list.add(s.charAt(j));
                }
            }
            count = Math.max(count,list.size());
            //当count大于等于剩下字符串的长度时，不再遍历
            if(count>=len-1-i){
                break;
            }
            list.clear();
        }
        return count;
    }

    /**
     *  Sliding Window
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s){
        if(s.length()==0) {
            return 0;
        }
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }

        return max;
    }
}
