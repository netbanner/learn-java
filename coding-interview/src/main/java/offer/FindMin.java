package offer;

/**
 * 11.旋转数组中的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class FindMin {

    public static int findMin(int []arr){
        if(arr.length==0){
            return 0;
        }
        int l=0,r=arr.length-1;
        while (l<r){
            int mid = (l+r)/2;
            if(arr[mid]>arr[r]){
                l = mid+1;
            }else if(arr[mid]==arr[r]){
                r -=1;
            }else{
                r = mid;
            }
        }
        return arr[l];
    }


    public static void main(String[]args){
        int []arr = {4,5,6,1,2,3};
        System.out.print(findMin(arr));
    }
}
