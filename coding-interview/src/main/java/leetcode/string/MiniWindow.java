package leetcode.string;

import java.util.HashMap;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * @desc LeetCode 76
 */
public class MiniWindow {

    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return "";
        }

        int left = 0, right = 0;
        int count = 0;
        HashMap<Character,Integer> needs = new HashMap<Character, Integer>();
        HashMap<Character,Integer> windows = new HashMap<Character, Integer>();
        for(int i=0;i<t.length();i++){
            //needs.getOrDefault(t.charAt(i),0)+1 含义是：needs如果包含t.charAt(i)，
            //则取出值+1;不包含取0+1
            needs.put(t.charAt(i),needs.getOrDefault(t.charAt(i),0)+1);

        }
        // 记录最小覆盖子串的起始索引及长度
        int start = 0,end =0, minlen = Integer.MAX_VALUE;
        while (right<s.length()){
            // c是将移入窗口的字符
            char c = s.charAt(right);
            //右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            //如果是t中字符，在windows里添加，累计出现次数
            if(needs.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0)+1);
                if(windows.get(c).compareTo(needs.get(c))==0){
                    count++;
                }
            }
            //符合要求的字符个数正好是t中所有字符，获得一个可行解
            while(count==needs.size()){
                //更新结果
                if(right-left<minlen){
                    start = left;
                    end = right;
                    minlen = end - left;
                }
                //开始进行优化，即缩小区间，删除s[left],
                char temp =s.charAt(left);

                //当前删除的字符包含于t,更新Windows中对应出现的次数，如果更新后的次数<t中出现的次数，
                // 符合要求的字符数减1，下次判断count==needs.size()时不满足情况，
                if(needs.containsKey(temp)){
                    windows.put(temp,windows.getOrDefault(temp,1)-1);
                    if(windows.get(temp)<needs.get(temp)){
                        count--;
                    }
                }
                left++;
            }

        }

        //返回覆盖的最小串
        return minlen==Integer.MAX_VALUE ? "":s.substring(start,end);

    }


}
