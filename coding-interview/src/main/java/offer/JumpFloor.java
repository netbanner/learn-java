package offer;

/**
 *  青蛙跳台阶
 *  f(n) = {
 *      n,n<=2;
 *      f(n-1)+f(n-2),n>2
 *  }
 */
public class JumpFloor {

    public int solution(int n){
        if(n<=2) {
            return n;
        }
        int pre1=1,pre2 = 2;
        int result =0;
        for(int i=3;i<n;i++){
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }

        return  result;
    }
}
