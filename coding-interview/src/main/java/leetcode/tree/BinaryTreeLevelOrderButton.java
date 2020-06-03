package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderButton {

    public List<List<Integer>> levelOrderButton(TreeNode root){
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root ==null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> oneLevel = new ArrayList<Integer>();
            int count = queue.size();
            for(int i=0;i<count;i++){
                TreeNode node = queue.poll();
                oneLevel.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            //从队头放数据
            result.addFirst(oneLevel);
        }
        return result;
    }
}
