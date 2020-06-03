package offer;

import java.util.HashMap;
import java.util.Map;

/**
 *  数组中出现次数超过一半的数字
 */
public class MoreThanHalfNum {


    public static int morethanHalfNum(int []a){
        if(a==null||a.length==0){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<a.length;i++){
                if(map.containsKey(a[i])) {
                    map.put(a[i], map.get(a[i]) + 1);
                }else{
                    map.put(a[i], map.getOrDefault(a[0], 1));
                }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>(a.length/2)){
                return entry.getKey().intValue();
            }
        }
        return 0;
    }

    public static void main(String []args){
        int a[] = {1,2,2,2,2,2,2,3,4,5};
        System.out.println(morethanHalfNum(a));
    }
}
