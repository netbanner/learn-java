package leetcode.string;

public class Palindrome {

    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        String str = s.toLowerCase();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : str.toCharArray()) {
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString().equals(stringBuffer.reverse().toString());
    }

    public static void main(String []args){
        String s = "race a car";
        isPalindrome(s);
    }
}
