package offer;

import leetcode.tree.TreeNode;

import java.util.ArrayList;

/**
 * 二叉树中和为某一值的路径
 *
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 *
 * 下图的二叉树有两条和为 22 的路径：10, 5, 7 和 10, 12
 */
public class FindPath {

    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int num){
        backtracting(root,num,new ArrayList<>());
        return ret;
    }

    private void backtracting(TreeNode node,int num ,ArrayList<Integer> path){
        if(node ==null){
            return;
        }
        path.add(node.val);
        num -=node.val;
        if(num==0&&node.left==null&&node.right==null){
            ret.add(path);
        }else{
            backtracting(node.left,num,path);
            backtracting(node.right,num,path);
        }
        path.remove(path.size()-1);
    }
}
