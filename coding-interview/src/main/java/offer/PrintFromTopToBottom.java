package offer;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PrintFromTopToBottom {


    public ArrayList<Integer> print(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                cnt--;
            }
        }
        return result;
    }

    //按之字形顺序打印二叉树
    public ArrayList<ArrayList<Integer>> print1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                i++;
                if (i % 2 == 0) {
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    queue.add(node.right);
                    queue.add(node.left);
                }
                list.add(node.val);
            }
            result.add(list);
        }
        return result;
    }
}
