import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuwh
 * @date 2019/4/24 17:43
 * @desc
 */
public class test {

    public static boolean isSubsequence(String x, String y) {
        int j = 0;
        if (x == null || y == null) {
            return false;
        }
        for (int i = 0; i < y.length() && j < x.length(); i++) {
            if (x.charAt(j) == y.charAt(i)) {
                j++;
            }
        }
        return j == x.length();
    }


    public static int findLUSlength(String[] strs) {
        int res = -1;
        for (int i = 0, j; i < strs.length; i++) {
            for (j = 0; j < strs.length; j++) {
                if (j == i)
                    continue;
                if (isSubsequence(strs[i], strs[j]))
                    break;
            }
            if (j == strs.length)
                res = Math.max(res, strs[i].length());
        }
        return res;
    }


    public static void main(String[] args) {
        String[] strs = {"abc", "ab", "c"};
    //    System.out.println(strs);
   //     System.out.println(findLUSlength(strs));
        String str1 = "1600";

       str1 = str1.replace(str1.substring(str1.length()-2,str1.length()),":"+str1.substring(str1.length()-2,str1.length()));
        System.out.print(str1);
    }
}
