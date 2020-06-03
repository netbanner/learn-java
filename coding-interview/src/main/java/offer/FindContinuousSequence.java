package offer;

import java.util.ArrayList;

/**
 * 和为s的连续正数序列
 *
 * 从递增数组中两个何=和为s的数得到启示，我们也可以设置两个指针，一个指向当前序列的最小的数，一个指向当前序列最大的数。
 *
 * 1）设置两个指针，一个为small指向当前正数序列中最小的数，一个为big指向当前正数序列中最大的数；
 *
 * 2）若是当前的正数序列之和大于S，那么缩小序列范围，让small指针不停往前走，知道等于S停止；
 *
 * 3）若是当前的正数序列之和小于S，那么扩大序列范围，让big指针不停往前走，直到和为S停止；
 *
 * 注意点：设置mid变量，赋值为(1+s)/2，因为何为s的序列至少包括两个数，所以small要小于s的一半；
 * ————————————————
 */
public class FindContinuousSequence {


    public ArrayList<ArrayList<Integer>> findSequence(int sum){

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if(sum<3){
            return list;
        }
        int smail = 1;
        int big = 2;
        int mid = (sum+1)/2;

        int cur = smail+big;

        while (smail<big){
            if(cur>sum){
                cur -=smail;
                smail++;
            }else if(cur<sum){
                cur +=big;
                big++;
            }else {
                ArrayList<Integer> list1 = new ArrayList<>();
                for(int i=smail;i<=big;i++){
                    list1.add(i);
                }
                list.add(list1);
                cur -=smail;
                smail++;
                big++;
                cur +=big;
            }

        }

        return list;
    }

}
