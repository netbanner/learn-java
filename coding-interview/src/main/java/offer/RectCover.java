package offer;

/**
 *  矩阵覆盖
 * 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？
 */
public class RectCover {

    public int solution(int n){
        if(n<=2){
            return n;
        }
        int pre1= 1,pre2 =2;
        int result =3;
        for(int i=3;i<n;i++){
            result = pre2 +pre1;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }


}
