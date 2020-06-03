package leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t 。
 * Sinding window;
 */
public class LeetCode159 {

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i=0,j=0,max=0;
        Set<Character> set = new HashSet<Character>();
        while (j<s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));

                max = Math.max(max,set.size());
            }else{
                set.remove(set.remove(s.charAt(i++)));
            }
        }
        return max;
    }

    public static void main(String []args){
        String s = "ccaabbb";
        System.out.print(lengthOfLongestSubstringTwoDistinct(s));
    }
}
