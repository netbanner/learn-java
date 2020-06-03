package offer;

import leetcode.tree.TreeNode;

/**
 * 重建二叉树
 */
public class ReConstructBinaryTree {


    public TreeNode reConstructBinaryTree(int []pre,int []in){

        if(pre==null||in ==null){
            return null;
        }else {
            TreeNode root = new TreeNode(pre[0]);
            int loc = getIndex(pre[0],in);
            //获取左子树的先序遍历序列
            int []preL = getSubArray(1,loc,pre);
            //获取左子树的中序遍历序列
            int []inL= getSubArray(0,loc-1,in);
            //获取右子树的前序遍历序列
            int []preR = getSubArray(loc+1,pre.length-1,pre);
            //获取右子树的中徐遍历序列
            int []inR = getSubArray(loc+1,pre.length-1,in);
            //递归构建当前节点的左子树
            root.left = reConstructBinaryTree(preL, inL);
            //递归构建当前节点的右子树
            root.right = reConstructBinaryTree(preR, inR);

            return root;
        }
    }

    //根据起始坐标获取数组中指定的子串
    public int[] getSubArray(int inx,int end ,int[]arr){
        int []subArr = new int[end-inx+1];
        for(int i=inx;i<=end;i++){
            subArr[i-inx] = arr[i];
        }
        return subArr;
    }

    //获取元素数组小标位置
    private int getIndex(int target,int[]arr){
        int i=0;
        for(int a:arr){
            if(a==target){
                break;
            }
            i++;
        }
        return i;
    }
}
