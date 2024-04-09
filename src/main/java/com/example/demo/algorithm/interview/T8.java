package com.example.demo.algorithm.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有序列表，找出所有两数之和为10000的组合，要求空间复杂度O(1)
 * </p>
 * 单链表奇数节点移动到最前面：输入：a1->a2->a3->a4->a5，输出：a1->a3->a5->a2->a4
 * 
 * @author yangjinyu
 * @time 2022/10/19 22:45
 */
public class T8 {

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 5, 15, 20, 40, 50, 65, 80, 95, 98, 99, 100 };
        System.out.println(new T8().find(arr));
        Node head = new Node(1);
        Node node = head;
        for (int i = 2; i <= 5; i++) {
            node.next = new Node(i);
            node = node.next;
        }
        Node newHead = new T8().moveOddNode2(head);
        System.out.println(newHead);
    }

    // 有序列表，找出所有两数之和为10000的组合，要求空间复杂度O(1)
    // 双指针，从列表的两边向中间扫，和小于10000，则左指针右移，和大于10000，则右指针左移
    public List < List < Integer > > find(int[] arr) {
        int i = 0, j = arr.length - 1;
        List < List < Integer > > res = new ArrayList <>();
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == 100) {
                res.add(Arrays.asList(arr[i], arr[j]));
            }
            if (sum > 100) {
                j--;
            }
            else {
                i++;
            }
        }
        return res;
    }

    static class Node {
        public Node(int val) {
            this.val = val;
        }

        int val;

        Node next;
    }

    // 单链表奇数节点移动到最前面：输入：a1->a2->a3->a4->a5，输出：a1->a3->a5->a2->a4
    // 奇数odd 偶数even
    public void moveOddNode(Node head) {
        Node node = head;
        Node prev = null;
        int position = 1;
        while (node != null) {
            Node next = node.next;
            if (position > 1 && position % 2 == 1) {
                prev.next = next;
                node.next = head;
                head = node;
            }
            else {
                prev = node;
            }
            node = next;
            position++;
            // 打印，a1->a2->a3->a4->a5 变成 a5->a3->a1->a2->a4了，不符合要求
            // 思路，题意要求奇数顺序不变，将偶数位置节点插入尾部，见moveOddNode2
            /*
             * Node h = head; while(h != null){ System.out.print(h.val+" "); h = h.next; } System.out.println();
             */
        }
    }

    // 输入：a1->a2->a3->a4->a5，输出：a1->a3->a5->a2->a4，要求空间复杂度O(1)
    public Node moveOddNode2(Node head) {
        // 两个以下节点直接返回
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node odd = head;
        // evenHead指向偶数节点头结点，最终拼装到奇数节点链表后
        Node evenHead = head.next, even = evenHead;
        // while结束条件是关键，even.next != null 能确保 odd.next != null
        // 如果even.next == null，那么没有继续下去的比要了，画个图就可以理解
        while(even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        // 最终将evenHead挂到奇数节点链表后面
        odd.next = evenHead;
        return head;
        // 总结：核心还是双指针，一个指向奇数节点一个指向偶数节点，同时移动
        // a1 -> a2 -> a3 -> a4 -> a5 -> a6
        //  ↑     ↑
        //odd   even
        // a1 -> a2 -> a3 -> a4 -> a5 -> a6
        //              ↑     ↑
        //            odd   even
        // a1 -> a2 -> a3 -> a4 -> a5 -> a6
        //                          ↑     ↑
        //                        odd   even
    }



    public Node move(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        //输入：a1->a2->a3->a4->a5，输出：a1->a3->a5->a2->a4，要求空间复杂度O(1)
        Node odd = head;
        Node even = head.next, evenHead = even;
        while (even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}