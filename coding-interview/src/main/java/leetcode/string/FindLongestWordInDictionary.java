package leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class FindLongestWordInDictionary {


    public String findLongestWord(String[] words) {
        Set<String> wordset = new HashSet();
        for (String word : words) {
            wordset.add(word);
        }
        String longest = "";
        for (String s : words) {
            int l1 = longest.length();
            int l2 = s.length();
            if (l2 > l1 || (l1 == l2 && longest.compareTo(s) > 0)) {
                boolean good = true;
                for(int k=1;k<s.length();++k){
                    if(!wordset.contains(s.substring(0,k))){
                        good = false;
                        break;
                    }
                }
                if (good) {
                    longest = s;
                }
            }

        }
        return longest;
    }

    public static void main(String[] args) {
        FindLongestWordInDictionary findLongestWordInDictionary = new FindLongestWordInDictionary();
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.print(findLongestWordInDictionary.findLongestWord(words));
    }
}
