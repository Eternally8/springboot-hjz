### 解题思路
由于两数相加是从低位逐渐向高位，对应着链表从头结点向尾结点，因此我们只需要从头结点进行遍历即可
需要注意两个点：
- 两数相加可能会产生进位，要对进位进行处理
- 两条链表可能长度不一致，要判断某条链表是否已经到达尾结点

### 方法一

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //安全检查
        if(l1==null)    return l2;
        if(l2==null)    return l1;

        //设置头结点
        ListNode root=new ListNode(-1);
        ListNode tmp=root;
        int sum=0;  //存储当前结点相加值
        int count=0;    //存储当前结点进位值
        while(l1!=null && l2!=null){
            sum=l1.val+l2.val+count;
            count=sum/10;
            tmp.next=new ListNode(sum%10);

            l1=l1.next;
            l2=l2.next;
            tmp=tmp.next;
        }

        //如果剩余的l1链表非空
        while(l1!=null){
            sum=l1.val+count;
            count=sum/10;
            tmp.next=new ListNode(sum%10);

            l1=l1.next;
            tmp=tmp.next;
        }
        //如果剩余的l2链表非空
        while(l2!=null){
            sum=l2.val+count;
            count=sum/10;
            tmp.next=new ListNode(sum%10);

            l2=l2.next;
            tmp=tmp.next;
        }

        //如果链表所有元素相加完，还有进位剩余，将进位设为一个新结点
        if(count!=0){
            tmp.next=new ListNode(count);
        }

        //返回相加后的链表结果
        return root.next;
    }
}
```

### 方法二
方法一中，我们将l1链表和l2链表是否为空，以及进位是否剩余进行了单独考虑，为了简化代码，方法二将其进行融合
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //安全检查
        if(l1==null)    return l2;
        if(l2==null)    return l1;

        //设置头结点
        ListNode root=new ListNode(-1);
        ListNode tmp=root;
        int sum=0;  //存储当前结点相加值
        int count=0;    //存储当前结点进位值

        /*
        和方法一不同，这里用一个while循环解决所有问题*/
        while(l1!=null || l2!=null || count!=0){
            int l1val = l1==null ? 0 : l1.val;
            int l2val = l2==null ? 0 : l2.val;
            sum=l1val+l2val+count;
            count=sum/10;
            tmp.next=new ListNode(sum%10);

            if(l1!=null)    l1=l1.next;
            if(l2!=null)    l2=l2.next;
            tmp=tmp.next;
        }

        return root.next;
    }
}
```
