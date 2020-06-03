package leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class FindTargetCopyTreeNode {

    //前序遍历非递归代码 根左右
    public static TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        Deque<TreeNode> dequeclone = new LinkedList<TreeNode>();

        TreeNode node = original;
        TreeNode cloneTarget = cloned;
        while (node != null || !deque.isEmpty()) {
            if (node != null) {
                if (node == target) {
                    //找到目标，返回
                    return cloneTarget;
                }    //节点不为空,先左节点
                deque.push(node);
                node = node.left;
                dequeclone.push(cloneTarget);
                cloneTarget = cloneTarget.left;
            }else{
                node = deque.pop();
                node = node.right;
                cloneTarget = dequeclone.pop();
                cloneTarget = cloneTarget.right;
            }
        }
        return null;
    }
    //前序遍历递归代码  根左右
    public static  TreeNode getTargetCopy1(final TreeNode original,final TreeNode cloned,final TreeNode target){
        if(original==null){
            return null;
        }
        if(original==target){
            return cloned;
        }
        //递归左子树
        TreeNode resl = getTargetCopy(original.left,cloned.left,target);
        if(resl!=null){
            return resl;
        }

        //递归右子树
        TreeNode resr = getTargetCopy(original.right,cloned.right,target);
        if(resr!=null){
            return resr;
        }

        return null;
    }

    //层次遍历代码

    public static  TreeNode get(final TreeNode original,final TreeNode cloned,final TreeNode target){
        Queue<TreeNode> oriTreeNode = new LinkedList<TreeNode>();
        Queue<TreeNode> cloTreeNode = new LinkedList<TreeNode>();

        oriTreeNode.offer(original);
        cloTreeNode.offer(cloned);

        TreeNode treeNode1;
        TreeNode treeNode2;
        while (!oriTreeNode.isEmpty()){
            treeNode1 = oriTreeNode.poll();
            treeNode2 = cloTreeNode.poll();
            if(treeNode1==target){
                return treeNode2;
            }
            if(treeNode1.left!=null){
                oriTreeNode.offer(treeNode1.left);
                cloTreeNode.offer(treeNode2.left);
            }
            if(treeNode2.right!=null){
                oriTreeNode.offer(treeNode1.right);
                cloTreeNode.offer(treeNode2.right);
            }
        }

        return null;
    }


}
