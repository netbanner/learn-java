package offer;

/**
 * 左旋转字符串
 * 先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"，然后再把整个字符串翻转得到 "XYZdefabc"。
 */
public class LeftRotateString {


    public String leftRotate(String str,int n){
        if(n>=str.length()){
            return str;
        }

        char []chars = str.toCharArray();
        reverte(chars,0,n-1);
        reverte(chars,n,str.length()-1);
        reverte(chars,0,str.length()-1);

        return new String(chars);
    }

    private void  reverte(char []chars,int i,int j){
        while (i<j){
            swap(chars ,i++,j--);
        }
    }

    private void swap(char []chars,int i,int j){
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
