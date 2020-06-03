package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author zhuwh
 * @date 2019/2/22 14:16
 * @desc
 */
public class BinarySearchTree {

    TreeNode createBST(int arr[], int start, int end){
        if(end<start){
            return  null;
        }
        int mid = (start+end)/2;

        TreeNode n = new TreeNode(arr[mid]);
        n.left = createBST(arr,start,mid-1);
        n.right = createBST(arr,mid+1,end);
        return n;
    }

    TreeNode createBST(int array[]) {
        return createBST(array, 0, array.length - 1);
    }


    ArrayList<LinkedList<TreeNode>> createLinkedListForEachLevel(TreeNode root){
        ArrayList<LinkedList<TreeNode>> linkedLists = new ArrayList<LinkedList<TreeNode>>();
        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        if(root!=null){
            current.add(root);
        }
        while (current.size()>0){
            linkedLists.add(current); //add previous level
            LinkedList<TreeNode> parents = current; //go to next level
            current = new LinkedList<TreeNode>();
            for(TreeNode treeNode :parents){
                if(treeNode.left!=null){
                    current.add(treeNode.left);
                }
                if(treeNode.right!=null){
                    current.add(treeNode.right);
                }
            }
        }

        return linkedLists;
    }
}
