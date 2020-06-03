package offer;

public class VerifySquence {

    public boolean verifySquence(int []a){
        if(a==null){
            return false;
        }
        return verifySquence(a,0,a.length-1);

    }
    private boolean verifySquence(int []a,int start,int end){
        if(start>=end){
            return false;
        }
        //后序遍历，最后一个节点是根节点
        int root = a[end];
        int i=0;
        //在二叉搜索树中左子树的结点小于根结点
        for(;i<end;++i){
            if(a[i]>root){
                break;
            }
        }
        //在二叉搜索树中右子树的结点大于根结点
        int j=i;
        for(;j<end;++j){
            if(a[j]<root){
                return false;
            }
        }

        //判断左子树是否是二叉树
        boolean left = true;
        if(i>start){
            left = verifySquence(a,start,i-1);
        }

        boolean right = true;
        if(i<end){
            right = verifySquence(a,i,end-1);
        }


        return (left&&right);
    }

}
