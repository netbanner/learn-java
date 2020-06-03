package offer;

public class NumberOf1Between1AndN {

    public int solution(int n){
        int cnt =0;

        for(int i=1;i<=n;i=i*10){
            int a =n/i,b=n%i;
            cnt += (a + 8) / 10 * i + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }
}
