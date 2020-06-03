package offer;

/**
 *
 * 要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句 A ? B : C。
 * 条件与 && 具有短路原则
 */
public class Sum1toN {

    public int solution(int n){
        int sum = n;
        boolean b = (n>0)&&((sum +=solution(n-1))>0);
        return sum;
    }

    //写一个函数，求两个整数之和，要求不得使用 +、-、*、/ 四则运算符号。
    public int Add(int a, int b) {
        return b == 0 ? a : Add(a ^ b, (a & b) << 1);
    }

    public int[] multiply(int []a){
        int n = a.length;
        int []b = new int[n];
        for(int i=0,product=1;i<n; product *=a[i],i++){
            b[i] = product;
        }
        for(int i=n-1,product =1;i>=0;product *=a[i],i--){
            b[i] *=product;
        }
        return b;
    }
}
