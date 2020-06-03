package offer;

/**
 * 圆圈中最后剩下的数
 * 约瑟夫环
 */
public class LastRemaining {

    public int solution(int n,int m){
        if(n==0){
            return -1;
        }
        if(n==1){
            return 0;
        }

        return (solution(n-1,m)+m)%n;
    }
}
