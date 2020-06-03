package offer;

/**
 * 数值的整数次方
 * 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
 */
public class Power {

    public double power(double base,int exponent){
        double result=0d;
        if(exponent==0) {
            return 1;
        }
        if(exponent==1){
            return base;
        }
        boolean isNegative = false;
        if(exponent<0){
            exponent = -exponent;
            isNegative = true;
        }
        result = power(base*base,exponent/2);
        if(exponent%2!=0){
            result = result * base;
        }
        return  isNegative?1/result:result;
    }
}
