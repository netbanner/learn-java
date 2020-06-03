package leetcode.array;


import java.util.*;

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

public class RandomizedSet {

    Map<Integer,Integer> map;
    List<Integer> list;

    public RandomizedSet(){
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean instert(int val){
        if(!map.containsKey(val)){
            map.put(val,list.size());
            list.add(list.size(),val);
            return  true;
        }

        return false;
    }
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val){
        if(map.containsKey(val)){
           int index =  map.remove(val);
            list.add(index,Integer.MIN_VALUE);
            return true;
        }
        return false;
    }

    public int getRandom(){
        Random random = new Random();
        int i = random.nextInt(list.size());
        while (list.get(i)==Integer.MIN_VALUE){
            i = random.nextInt(list.size());
        }
        return list.get(i);
    }
}
