package offer;

/**
 * 第一个只出现一次的字符位置
 */
public class FirstNotRepeatingChar {

    public int solution(String str){
        int []cnts = new int[256];
        for(int i=0;i<str.length();i++){
            cnts[str.charAt(i)]++;
        }
        for(int j=0;j<str.length();j++){
            if(cnts[str.charAt(j)]==1){
                return j;
            }
        }

        return -1;
    }
}
