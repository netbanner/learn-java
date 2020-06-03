package leetcode.string;

import java.util.List;

/**
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，
 * 该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，
 * 返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串
 */
public class FindLongestWord {


    //找出最长的字符串
    public String findLongestWord(String s, List<String> d) {
        String longest = "";
        for (String str : d) {
            int l1 = longest.length();
            int l2 = str.length();
            if (l2 < l1 || (l1 == l2 && longest.compareTo(str) < 0)) {
                continue;
            }
            if (isValid(s, str)) {
                longest = str;
            }
        }
        return longest;
    }

    //比较目标字符串中是否包含字典中的字符串
    public boolean isValid(String s, String str) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < str.length()) {
            if (s.charAt(i) == str.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == str.length();
    }
}
