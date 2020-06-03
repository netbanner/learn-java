package offer;

public class NumberOf1 {

    public static int solution(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n &= (n - 1);
        }
        return cnt;
    }


    public static void main(String []args){
        int n = 20;
        System.out.print(solution(20));
    }
}
