package offer;

/**
 *  复杂链表的复制
 */
public class RandomListNode {

    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label){
        this.label = label;
    }


    public RandomListNode clone(RandomListNode phead){
        if(phead==null){
            return null;
        }

        RandomListNode cur = phead;
        //插入新节点
        while (cur!=null){
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }

        //建立randomw链接
        cur = phead;
        while (cur!=null){
            RandomListNode clone = cur.next;
            if(cur.random!=null){
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }
        //拆分
        cur = phead;
        RandomListNode pcloneHead = phead.next;
        while (cur.next!=null){
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }

        return pcloneHead;
    }
}
