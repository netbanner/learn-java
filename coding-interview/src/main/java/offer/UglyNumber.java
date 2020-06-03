package offer;

/**
 * 丑数
 * 题目：丑数
 * * 我们把只包含因子2，3，5的数称为丑数（Ugly Number).
 * * 求按从小到大的顺序的第1500个丑数。
 * * 例如6，8都是丑数，但14不是，因为它含有因子7.习惯上我们把1当作第一个丑数
 */
public class UglyNumber {

    public int getUglyNumber(int n) {
        if(n<=0){
            return 0;
        }
        int number = 0;
        int uglyFound = 0;
        while (uglyFound<n){
            number++;
            if(isUgly(number)){
                ++uglyFound;
            }
        }
        return n;
    }

    private boolean isUgly(int a) {
        while (a % 2 == 0) {
            a /= 2;
        }
        while (a%3==0){
            a /=3;
        }
        while (a%5==0){
            a /=5;
        }

        return a == 1 ? true : false;
    }


    public int getUglyNumbers(int n){
        if(n<0){
            return 0;
        }
        int []uglyArray = new int[n];
        //乘以2
        int a2 =0;
        //乘以3
        int a3 =0;
        //乘以3
        int a5 =0;
        uglyArray[0] = 1;
        for(int i=1;i<n;i++){
            int min = min(uglyArray[a2]*2,uglyArray[a3]*3,uglyArray[a5]*5);
            uglyArray[i] = min;
            while (uglyArray[a2]*2==uglyArray[i]){
                a2++;
            }
            while (uglyArray[a3]*3==uglyArray[i]){
                a3++;
            }
            while (uglyArray[a5]*5==uglyArray[i]){
                a5++;
            }
        }
        return uglyArray[n-1];
    }
    private int min(int a1,int a2,int a3){
        int min = Math.min(a1,a2);
        return min<a3?min:a3;
    }
}
