package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root){
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root==null){
            return result;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> oneLevel = new ArrayList<Integer>();
            int count = queue.size();
            for(int i=0;i<count;i++){
                Node node = queue.poll();
                oneLevel.add(node.val);
                for(Node n:node.children){
                    queue.add(n);
                }
            }
            result.add(oneLevel);
        }

        return result;
    }
}
