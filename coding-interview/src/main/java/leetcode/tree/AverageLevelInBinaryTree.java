package leetcode.tree;

import java.util.*;

/**
 * 广度优先遍历
 */
public class AverageLevelInBinaryTree {


    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ave = new ArrayList<Double>();
        if (root == null){
            return ave;
        }
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            double sum = 0.0;
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ave.add((sum / count));
        }
        return ave;
    }



}
