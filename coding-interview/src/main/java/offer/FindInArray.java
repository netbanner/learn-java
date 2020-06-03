package offer;

/**
 * 题目描述
 *         在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *         请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class FindInArray {


    public boolean find (int [][]a ,int val){
        if(a==null){
            return false;
        }

        int row = a.length;
        int col = a[0].length;
        int i = row-1;
        int j =0;

        while (i>=0&&j<col){
            if(a[i][j]==val){
                return true;
            }else if(a[i][j]>val){
                i--;
            }else{
                j++;
            }
        }

        return false;
    }
}
