package offer;

/**
 * 打印从 1 到最大的 n 位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
 */
public class Print1ToMaxOfNDigits {


    public  void print(int n){
        if(n<=0){
            return;
        }
        char[]numbers = new char[n];
        printDigits(numbers,n);
    }

    private void printDigits(char []numbers,int n){
        if(n==numbers.length){
            printNumber(numbers);
            return;
        }
        for(int i=0;i<10;i++){
            numbers[n] = (char)(i+'0');
            printDigits(numbers,n+1);
        }
    }

    private void printNumber(char []numbers){
        int index =0;
        while (index<numbers.length&&numbers[index]=='0'){
            index++;
        }
        while (index<numbers.length){
            System.out.print(numbers[index++]);
        }
        System.out.println();
    }

}
