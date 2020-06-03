package offer;
/**
 * 字符流中第一个不重复的字符
 * 当从该字符流中读出前六个字符“google" 时，第一个只出现一次的字符是 "l"。
 */

import java.util.LinkedList;
import java.util.Queue;

public class FirstAppearingOnce {

    private Queue<Character> queue = new LinkedList<>();
    private int[] cnts = new int[256];

    public void insert(char ch) {
        cnts[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }

    public char FirstAppearingOnece() {
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
