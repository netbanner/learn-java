package offer;

public class ReverseSentence {

    public String reverseString(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(chars, 0, n - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
           swap(chars,i++,j--);
        }
    }
    private void swap(char[] chars,int i,int j){
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
