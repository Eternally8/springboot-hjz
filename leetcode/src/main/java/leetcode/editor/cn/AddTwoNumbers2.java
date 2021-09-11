package leetcode.editor.cn;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学
// 👍 6429 👎 0

import com.alibaba.fastjson.JSON;

public class AddTwoNumbers2 {
    public static void main(String[] args) {
//        String cc = "12345678";
//        ListNode listNode = new ListNode();
//        listNode.val = cc.charAt(cc.length() - 1) -'0';
//        listNode.next = getNext(cc,cc.length() - 1);
//
//        System.out.println(JSON.toJSONString(listNode));
    }

//    private static ListNode getNext(String cc, int i) {
//        ListNode a = new ListNode();
//        int y = i - 1;
//        char j = cc.charAt(y);
//        a.val = j-'0';
//
//        if(y == 0){
//            a.next = null;
//            return a;
//        }else{
//            a.next = getNext(cc,y);
//        }
//        return a;
//    }
//
//
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode listNode = new ListNode();
//
//        String a1 = "";
//        while (l1.next != null){
//            a1 = l1.val + a1;
//            l1 = l1.next;
//        }
//        if(l1 != null){
//            a1 = l1.val + a1;
//        }
//        long aa = Long.getLong(a1);
//
//        String b1 = "";
//        while (l2.next != null){
//            b1 = l2.val + b1;
//            l2 = l2.next;
//        }
//        if(l2 != null){
//            b1 = l2.val + b1;
//        }
//        long bb = Long.getLong(b1);
//
//        String cc = aa + bb +"";
//
//        listNode.val = cc.charAt(cc.length() - 1) -'0';
//        listNode.next = getNext(cc,cc.length() - 1);
//        return listNode;
//    }
//



//leetcode submit region begin(Prohibit modification and deletion)
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
//        ListNode listNode = new ListNode();

        String a1 = "";
        while (l1.next != null){
            a1 = l1.val + a1;
            l1 = l1.next;
        }
        if(l1 != null){
            a1 = l1.val + a1;
        }
        long aa = Long.getLong(a1);

        String b1 = "";
        while (l2.next != null){
            b1 = l2.val + b1;
            l2 = l2.next;
        }
        if(l2 != null){
            b1 = l2.val + b1;
        }
        long bb = Long.getLong(b1);

        String cc = aa + bb +"";

        l1.val = cc.charAt(cc.length() - 1) -'0';
        l1.next = getNext(cc,cc.length() - 1);
        return l1;
    }


    public  ListNode getNext(String cc, int i) {
        ListNode a = new ListNode();
        int y = i - 1;
        char j = cc.charAt(y);
        a.val = j-'0';

        if(y == 0){
            a.next = null;
            return a;
        }else{
            a.next = getNext(cc,y);
        }
        return a;
    }

//    public  class ListNode {
//        int val;
//        ListNode next;
//        ListNode() {}
//        ListNode(int val) { this.val = val; }
//        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
