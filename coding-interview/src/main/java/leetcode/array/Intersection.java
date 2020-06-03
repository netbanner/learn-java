package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class Intersection {

    public int[] intersection(int []nums1,int []nums2){
        Set set1 = new HashSet();
        Set set2 = new HashSet();

        for(int i:nums1){
            set1.add(i);
        }
        for(int j:nums2){
            set2.add(j);
        }
        set2.retainAll(set1);
        int []num = new int[set2.size()];

        return null;
    }
}
