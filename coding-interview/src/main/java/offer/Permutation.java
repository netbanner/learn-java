package offer;

/**
 * 字符串的排列
 */
public class Permutation {

    public void permutation(String str){
        if(str==null){
            return;
        }
        permutation(str.toCharArray(),0);
    }

    private void permutation(char []chars,int index){
        if(index == chars.length-1){
            System.out.println(chars);
        }

        for(int i=index;i<chars.length;i++){
            //首字符和它后面的字符(包含自己)进行交换
            char temp = chars[i];
            chars[i] = chars[index];
            chars[index] = temp;
            //递归求后面的字符的排序
            permutation(chars,index+1);

            //还原字符串
            temp = chars[i];
            chars[i] = chars[index];
            chars[index] = temp;
        }
    }
}
