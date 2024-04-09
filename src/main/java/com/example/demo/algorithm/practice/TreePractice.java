package com.example.demo.algorithm.practice;

import java.util.*;

/**
 * practice for binary-tree
 *
 * @author yangjinyu
 * @time 2022/10/20 16:58
 */
public class TreePractice {

    // 树的遍历：先序、中序、后序
    public List<Integer> traverse(Node root) {
        // 栈辅助，非常重要
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);// 很好记，深度优先，所以左孩子不为空就一直入栈，为空了出栈，再看右孩子
                //res.add(root.val);// 深度优先搜索，即前序（先序）遍历。后序遍历？前序基础上，先右后左再倒序
                root = root.left;
            }else{
                root = stack.pop();
                res.add(root.val);// 放这里是中序遍历
                root = root.right;
            }
        }
        return res;
    }

    // 广度优先搜索
    public List<Integer> levelTraverse(Node root){
        // 队列辅助
        List<Integer> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if(root != null) {
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            Node n = queue.poll();
            res.add(n.val);
            if(n.left != null){
                queue.offer(n.left);
            }
            if(n.right != null){
                queue.offer(n.right);
            }
        }
        return res;
    }


    // 树高
    public int depth(Node root){
        // 广度优先
        /*
        int depth = 0;
        Queue<Node> queue = new LinkedList<>();
        if(root != null) {
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                if(n.left != null){
                    queue.offer(n.left);
                }
                if(n.right != null){
                    queue.offer(n.right);
                }
            }
            depth++;
        }
        */
        // 深度优先，递归，每次选取左右子树高的那个，加上1
        if(root == null){
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    // 树宽
    public int breadth(Node root){
        int breadth = 0;
        Queue<Node> queue = new LinkedList<>();
        if(root != null) {
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                if(n.left != null){
                    queue.offer(n.left);
                }
                if(n.right != null){
                    queue.offer(n.right);
                }
            }
            breadth = Math.max(breadth, size);
        }
        return breadth;
    }

    // 已知先序和中序（没有重复值），构造完整树
    public Node buildTree(int[] po, int poB, int poE, int[] in, int inB, int inE){
        // poB,poE代表先序子数组在数组的位置，inB,inE代表中序子数组在数组的位置
        // 先序，确定root
        // 中序，确定左右子树
        // 递归处理
        if(poB > poE || inB > inE){
            return null;
        }
        int val = po[poB];
        Node root = new Node(val);
        int index = inB;
        // 寻找root在中序中的位置，左边的为左子树，右边的为右子树
        for (; index <= inE; index++) {
            if(in[index] == val){
                break;
            }
        }
        int leftSize = index - inB;
        root.left= buildTree(po, poB+1, poB+leftSize, in, inB, index -1);
        root.right=buildTree(po, poB+leftSize+1, poE, in , index+1, inE);
        return root;
    }

    // 最近的公共祖先
    public Node lowestCommonAncestor(Node root, Node node1, Node node2){
        // 从root向下，如果一个节点，node1、node2分别在它的左右子树上，这个节点是公共祖先
        // 递归处理
        // 递归结束条件，找到了node1或node2，或都没找到
        if(root == null || node1.val == root.val || node2.val == root.val){
            return root;
        }
        Node left = lowestCommonAncestor(root.left, node1, node2);
        Node right = lowestCommonAncestor(root.right, node1, node2);
        if(left != null && right != null){
            return root;
        }else if(left != null){
            return left;// 说明两个都在左子树，继续往下找
        }
        else{
            return right;// 说明两个都在右子树，继续往下找
        }
        // 如果是二叉平衡树，可以用循环解决
        /*
        Node node = root;
        while (node != null){
            if(node1.val < node.val && node2.val < node.val){
                node = node.left;
            }else if (node1.val > node.val && node2.val > node.val){
                node = node.right;
            }else{
                break;
            }
        }
        return node;
        */
    }

    // 树的镜像
    // 例如输入：
    //
    //        4
    //      /  \
    //     2    7
    //    / \  / \
    //   1  3 6   9
    //镜像输出：
    //
    //       4
    //     /  \
    //    7    2
    //   / \  / \
    //  9  6 3   1
    public Node mirrorTree(Node root){
        if(root == null){
            return null;
        }
        // 左、右、根，后续遍历，访问到根时将左右孩子互换
        Node left =  mirrorTree(root.left);
        Node right = mirrorTree(root.right);
        root.left = right;
        root.right= left;
        return root;
    }

    // 判断node2是不是node1的子结构
    // 例如树1：
    //        3
    //       / \
    //      4   5
    //    / \
    //   1   2
    // 树2：
    //    4
    //   /
    //  1
    // 返回true
    public boolean isSubStructure(Node node1, Node node2){
        // 从node1根节点开始和node2比，不满足则用node1的左、右子树和node2比
        // 如果比到最后还是不满足，则返回false
        if(node1 == null || node2 == null){
            return false;
        }
        if(isSame(node1, node2)){
            return true;
        }
        return isSubStructure(node1.left, node2) || isSubStructure(node1.right, node2);
    }

    private boolean isSame(Node node1, Node node2) {
        // 如果node2为null，说明node2比完了，都符合
        if(node2 == null){
            return true;
        }
        // 如果node2不为null，但node1为null或者两种值不等，返回false
        if(node1 == null || node1.val != node2.val){
            return false;
        }
        return isSame(node1.left, node2.left) && isSame(node1.right, node2.right);
    }

    // 判断是否为二叉搜索树的后序遍历结果
    public boolean verifyPostorder(int[] postorder) {
        // 【重要】后序遍历的特性：根节点是最后一个
        // 二叉搜索树的后序遍历特性：从后往前找到第一个小于根节点的，那么它（包含）之前的是左子树，之后的是右子树
        // 递归判断每颗子树，都满足左<根<右，说明是二叉搜索树的后续遍历结果
        return isBinarySearchTree(postorder, 0, postorder.length-1);
    }

    public boolean isBinarySearchTree(int[] postorder, int b, int e){
        if(b >= e){
            return true;
        }
        int val = postorder[e], index = e-1;
        for (; index >= b ; index--) {
            if(postorder[index] < val){
                break;
            }
        }
        // 判断左子树是不是都小于根
        for (int i = b; i <= index; i++) {
            if(postorder[i] > val){
                return false;
            }
        }
        // 判断右子树是不是都大于根，不需要判断了，因为第一个for循环是找到第一个小于根的节点，因此i后面的肯定都大于根了
        //for (int j = i+1; j <= e-1; j++) {
        //    if(postorder[j] < val){
        //        return false;
        //    }
        //}
        return isBinarySearchTree(postorder, b, index) && isBinarySearchTree(postorder, index+1, e-1);
    }

    // 找出所有二叉树中 从根节点到叶子节点 路径总和等于给定目标和的路径。
    // 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
    // 输出：[[5,4,11,2],[5,8,4,5]]
    public List<List<Integer>> pathSum(Node root, int target) {
        // 这题真是想不出来了。尽量记住吧。
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();// 用于记录路径
        dfs(root, target, stack, res);
        return res;
    }

    // 深度优先遍历，即先序遍历
    // 如何将遍历路径记录下来？递归+stack。这里要注意，用非递归方式（栈辅助），无法通过辅助栈得到路径，一定要理解。
    private void dfs(Node root, int target, Stack<Integer> stack, List<List<Integer>> res) {
        if(root == null){
            return;
        }
        stack.push(root.val);
        target = target - root.val;
        if(target == 0 && root.left == null && root.right == null){// 路径和是target，并且为叶子节点
            res.add(new ArrayList<>(stack));// 注意要new ArrayList
        }
        dfs(root.left, target, stack, res);
        dfs(root.right, target, stack, res);
        stack.pop();
    }

    // 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
    public boolean isBalanced(Node root) {
        // 递归，任意两个左右子树，深度不超过1
        //if(root == null){
        //    return true;
        //}
        //if(Math.abs(depth(root.left) - depth(root.right)) > 1){
        //    return false;
        //}
        //return isBalanced(root.left) && isBalanced(root.right);

        // 此方案为最优解
        return balancedDepth(root) != -1;
    }

    private int balancedDepth(Node root) {
        // 递归
        if(root == null){
            return 0;
        }
        // 左右子树已经不平衡，返回-1；
        int leftDepth = balancedDepth(root.left);
        if(leftDepth == -1){
            return -1;
        }
        int rightDepth = balancedDepth(root.right);
        if(rightDepth == -1){
            return -1;
        }
        if(Math.abs(leftDepth-rightDepth) > 1){
            return -1;
        }
        // 左右子树深度差超1，返回-1；
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
    public int kthLargest(Node root, int k){
        // 中序遍历？ 左<根<右。先右后左，右>根>左，第k个被访问到的节点 即 第k大节点
        Stack<Node> stack = new Stack<>();
        int res = 0;
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.right;
            }else{
                root = stack.pop();
                if(--k == 0){
                    res = root.val;
                }
                root = root.left;
            }
        }
        return res;
    }

    // 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
    // 要求不能创建任何新的节点，只能调整树中节点指针的指向。
    // 就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
    // 还需要返回链表中的第一个节点的指针。
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        // 递归+回溯
        Node head = null, pre = null;
        dfs(root, head, pre);
        pre.right = head;
        head.left = pre;
        return head;
    }

    private void dfs(Node cur, Node head, Node pre){
        if(cur == null){
            return;
        }
        dfs(cur.left, head, pre);
        if(pre == null){
            head = cur;
        }else{
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;
        dfs(cur.right, head, pre);
    }


    // 在不破坏二叉搜索树前提下，维持二叉平衡树。左旋、右旋
    public void rotateLeft(Node x){
        // 左旋，必须有右子节点
        Node xp = x.parent, xr = x.right, xrl = xr.left;
        xr.parent = xp;
        if(xp != null){
            if(x == xp.left){
                xp.left = xr;
            }else {
                xp.right = xr;
            }
        }
        xr.left = x;
        x.parent = xr;
        if(xrl != null){
            x.right = xrl;
            xrl.parent = x;
        }
    }

    static class Node {
        public Node(int val) {
            this.val = val;
        }
        int val;
        Node left;
        Node right;
        Node parent;
    }

    public static Node createTree() {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(7);
        root.right.right.right = new Node(10);
        return root;
    }

    public static void main(String[] args) {
        Node root = createTree();
        TreePractice p = new TreePractice();
        System.out.println(p.traverse(root));
        System.out.println(p.levelTraverse(root));
        System.out.println("depth is "+p.depth(root));
        System.out.println("breadth is " + p.breadth(root));
        Node newNode = p.buildTree(new int[]{4,2,1,3,6,5,7,10}, 0, 7, new int[]{1,2,3,4,5,6,7,10}, 0, 7);
        System.out.println(p.traverse(newNode));
        System.out.println(p.levelTraverse(newNode));
        Node node1 = new Node(5);
        Node node2 = new Node(10);
        System.out.printf("%d and %d lowestCommonAncestor is %d%n",
                node1.val, node2.val, p.lowestCommonAncestor(root, node1, node2).val);
        System.out.println(p.verifyPostorder(new int[]{1,3,2,5,10,7,6,4}));
        System.out.println(p.pathSum(root, 15));
        System.out.println("the kth largest is "+p.kthLargest(root, 4));
        System.out.println("is balanced? "+p.isBalanced(root));
    }
}