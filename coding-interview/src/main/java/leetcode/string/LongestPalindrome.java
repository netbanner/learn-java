package leetcode.string;

public class LongestPalindrome {

    public String longestPalindrome(String s){
        String ans = "";
        int max = 0;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<=s.length();j++){
               String str = s.substring(i,j);
                if(isPalindrome(str)&&str.length()>max){
                    ans = s.substring(i,j);
                    max = Math.max(max,ans.length());
                }
            }
        }

        return ans;
    }

    public boolean isPalindrome(String s ){
        int len = s.length();
        for(int i=0;i<len/2;i++){
            if(s.charAt(i)!=s.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }
}
