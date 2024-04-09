package com.example.demo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("rawtypes")
public class TEST {

    public static int lengthOfLongestSubstring(String s) {
        List<Map<String, Object>> list = new ArrayList<>();
        System.out.println(list.stream().map(map -> new BigDecimal(map.get("11").toString()))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        if ("".equals(s)) {
            return 0;
        }
        // 动态规划，dp[i]表示以chars[i]结尾的最长不重复子符串长度
        int[] dp = new int[s.length()];
        // 存放char在字符串中的索引
        Map<Character, Integer> map = new HashMap<>();
        int max = dp[0] = 1;
        int i, j;
        for (i = 1; i < s.length(); i++) {
            j = map.getOrDefault(s.charAt(i), -1);
            map.put(s.charAt(i), i);
            if (j == -1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                if (dp[i - 1] >= i - j) {// 这里是关键
                    dp[i] = i - j;
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int nthUglyNumber(int n) {
        Set < Integer > set = new HashSet <>();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        set.add(1);
        for (int i = 2; i <= n; i++) {
            int add = 1;
            while (true) {
                int num = dp[i - 1] + add;
                if (num % 2 == 0 && set.contains(num / 2)) {
                    dp[i] = num;
                    break;
                }
                if (num % 3 == 0 && set.contains(num / 3)) {
                    dp[i] = num;
                    break;
                }
                if (num % 5 == 0 && set.contains(num / 5)) {
                    dp[i] = num;
                    break;
                }
                add++;
            }
            set.add(dp[i]);
        }
        return dp[n];
    }

    public static int countDigitOne(int n) {
        // solutions第三个，“冷静分析”
        // 0-9 -> 1 dp[1]
        // 0-99 -> 20 dp[2]
        // 0-999 -> 300 dp[3]
        // 可以发现，dp[n] = dp[n-1]*10 + 10^(n-1)
        // 这个容易理解，比如0-999，可以分为0-99,100-199，……，900-999
        // 也就是10个0-99，但是其中100-199多了100个1
        // 因此dp[3] = dp[2]*10 + 10^2
        // 以234为例，0-199可以用上个例子推出，200-234如何推解？
        // 2个0-99，加一个10^2，加3个0-9，加一个10^1，加0-4，最终解为154

        // 开始写代码
        // 先求位数
        String nStr = String.valueOf(n);
        int length = nStr.length();
        // 初始化dp数组并赋初始值dp[1]=1
        int[] dp = new int[length + 1];
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (int) (10 * dp[i - 1] + Math.pow(10, i - 1));
        }
        int res = 0, i = 1;// i用来标记现在在第几位
        while (i <= length) {
            int num = nStr.charAt(i - 1) - '0';
            res += dp[length - i] * num;
            if (num > 1) {
                res += (int) (Math.pow(10, length - i));
            }
            if (num == 1) {
                res += nStr.substring(i).length() == 0 ? 1 : Integer.parseInt(nStr.substring(i)) + 1;
            }
            i++;
        }
        return res;
    }

    public static boolean isNull(Object obj) {
        if (obj instanceof String) {
            return "".equals(obj.toString());
        }
        else {
            return obj == null;
        }
    }

    public static void main(String[] args) {
        Map < String, Object > map = new HashMap <>();
        map.put("A", "123");
        map.put("B", 123);
        System.out.println(isNull(map.get("A")));
        System.out.println(isNull(map.get("B")));

        countDigitOne(100);
        lengthOfLongestSubstring(" ");
        nthUglyNumber(1352);
        int[] nums = new int[10];
        StringBuilder res = new StringBuilder();
        int length = nums.length;
        while (length > 0) {
            for (int i = 0; i < length; i++) {
                res.append(nums[i]);
                // 将该元素剔除
                nums[i] = nums[length - 1];
                length--;
            }
        }
        System.out.println("1" + null);
        PriorityQueue < Integer > queue = new PriorityQueue <>();
        queue.add(2);
        queue.add(3);
        queue.add(1);
        System.out.println(queue.poll());
        queue = new PriorityQueue <>((x, y) -> y - x);
        queue.add(6);
        queue.add(5);
        queue.add(7);
        System.out.println(queue.poll());

        Set < String > set = new HashSet <>();
        set.add("1");
        String[] s = set.toArray(new String[0]);
        System.out.println(new HashMap <>().get(null));
        System.out.println(
                Arrays.toString(spiralOrder(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } })));
        String exclusiveInsuTypes = "110,120";
        if (StringUtils.isNotBlank(exclusiveInsuTypes)
                && Arrays.asList(exclusiveInsuTypes.split(",")).contains("110")) {
            System.out.println(11111);
        }
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        reverseList(n1);
        // System.out.println(replaceSpace("we are happy"));
        // ListNode head = new ListNode(2);
        // head.next = new ListNode(1);
        // head.next.next = new ListNode(3);
        // int[] array = reversePrint(null);
        // System.out.println();
        // cuttingRope(10);
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(1 / 2.5);
        System.out.println(1 / 2);
        // "[+-]?((\\d+\\.?){1}|(\\d+\\.?\\d*){1}|(\\.{1}\\d+){1}){1}([eE]{1}[+-]?\\d+)?";

        // 明天把19、20刷完，先停一停，看看kafka、mq，复习复习spring、mysql
        // 数值（按顺序）可以分成以下几个部分：
        //
        // 若干空格
        // 一个 小数 或者 整数
        // （可选）一个 'e' 或 'E' ，后面跟着一个 整数
        // 若干空格
        // 小数（按顺序）可以分成以下几个部分：
        //
        // （可选）一个符号字符（'+' 或 '-'）
        // 下述格式之一：
        // 至少一位数字，后面跟着一个点 '.'
        // 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
        // 一个点 '.' ，后面跟着至少一位数字
        // 整数（按顺序）可以分成以下几个部分：
        //
        // （可选）一个符号字符（'+' 或 '-'）
        // 至少一位数字

        // String regex = "\\s*[+-]?((\\d+\\.)|(\\d+\\.\\d+)|(\\.\\d+)|(\\d+))([eE][+-]?\\d+)?\\s*";
        // (\d+\.)和(\d+)优化成(\d+\.?)
        String regex = "\\s*[+-]?((\\d+\\.?)|(\\d+\\.\\d+)|(\\.\\d+))([eE][+-]?\\d+)?\\s*";
        String[] inputs = new String[] { "+100", "5e2", "-123", "3.1416", "-1E-16", "0123", " 0e-0", "0" };
        for (String input : inputs) {
            System.out.println(input.matches(regex));
        }
        System.out.println("-------------");
    }

    public static String replaceSpace(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        s = s.replaceAll("\\s", "%20");
        return s;
    }

    public static int[] reversePrint(ListNode head) {
        if (head != null) {
            Stack < Integer > stack = new Stack <>();
            stack.push(head.val);
            ListNode next = head.next;
            while (next != null) {
                stack.push(next.val);
                next = next.next;
            }
            int[] array = new int[stack.size()];
            for (int i = 0; !stack.isEmpty(); i++) {
                array[i] = stack.pop();
            }
            return array;
        }
        return new int[0];
    }

    public static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static int cuttingRope(int n) {
        // 计算过程中可能超过int的最大值，使用bigint（最多有19位）
        final BigInteger MOD = BigInteger.valueOf(1000000007);
        BigInteger[] dp = new BigInteger[n + 1];
        Arrays.fill(dp, BigInteger.valueOf(1));
        // dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = dp[i].max(BigInteger.valueOf(j * (i - j)).max(BigInteger.valueOf(j).multiply(dp[i - j])));
            }
        }
        return dp[n].mod(MOD).intValue();
    }

    // public static boolean isMatch(String s, String p) {
    // if(p == null || "".equals(p)){
    // return false;
    // }
    // Stack<Character> ps = new Stack<>();
    // p.chars().forEach(c->ps.push((char) c));
    // Stack<Character> ss = new Stack<>();
    // if(s != null && !"".equals(s)) {
    // s.chars().forEach(c->ss.push((char) c));
    // }
    // while(!ps.isEmpty()){
    // char a = ps.pop();
    // if(a != '*'){
    // if(ss.isEmpty() || ss.pop() != a){
    // return false;
    // }
    // }else{
    // if(!ps.isEmpty()){
    // a = ps.pop();
    // // 如果为.*，没法处理
    // if(a == '.') {
    //
    // }else{
    // while (!ss.isEmpty()) {
    // if (ss.peek() == a) {
    // ss.pop();
    // } else {
    // break;
    // }
    // }
    // }
    // }else{
    // // 不允许*号开头
    // return false;
    // }
    // }
    // if(ss.isEmpty()){
    // break;
    // }
    // }
    // return ps.isEmpty() && ss.isEmpty();
    // // 自己瞎琢磨，不行，情况太多想不全
    // // 应该从后往前看，用两个栈，会简单很多
    // /*
    // if (p == null || "".equals(p)) {
    // return false;
    // }
    // Queue<Character> sq = new LinkedList<>();
    // for (char c : s.toCharArray()) {
    // sq.offer(c);
    // }
    // Queue<Character> pq = new LinkedList<>();
    // for (char c : p.toCharArray()) {
    // pq.offer(c);
    // }
    // // "mississippi", "mis*is*p*."
    // // .代表任意字母，所以有两种组合：字母，字母*
    // while (!pq.isEmpty()) {
    // if (sq.peek() == null) {
    // break;
    // }
    // char i = pq.poll();
    // // 如果p下个字符为*，弹出s中元素直到遇到和它不等的元素为止
    // // "mississippi", "mis*is*p*."
    // if (pq.peek() != null && pq.peek() == '*') {
    // pq.poll();
    // while (!sq.isEmpty()) {
    // if (i != '.' && sq.peek() != i) {
    // break;
    // }
    // sq.poll();
    // }
    // } else {
    // if (i != '.' && sq.peek() != i) {
    // return false;
    // } else {
    // sq.poll();
    // }
    // }
    // }
    // return pq.isEmpty() && sq.isEmpty();
    // */
    // }

    public static ListNode reverseList(ListNode head) {
        ListNode prev, current = head, next = head.next;
        while (next != null) {
            prev = current;
            if (prev == head) {
                prev.next = null;
            }
            current = next;
            next = next.next;
            current.next = prev;
        }
        return current;
    }

    public static int[] spiralOrder(int[][] matrix) {
        int m = matrix.length - 1, n = matrix[0].length - 1;
        int total = (m + 1) * (n + 1);
        int[] result = new int[total];
        boolean[][] visited = new boolean[m + 1][n + 1];
        int i = 0, j = 0, visitedSize = 0;
        while (visitedSize < total) {
            while (true) {
                if (!visited[i][j]) {
                    result[visitedSize] = matrix[i][j];
                    visitedSize++;
                    visited[i][j] = true;
                }
                if (j == n || visited[i][j + 1]) {
                    break;
                }
                j++;
            }
            while (true) {
                if (!visited[i][j]) {
                    result[visitedSize] = matrix[i][j];
                    visitedSize++;
                    visited[i][j] = true;
                }
                if (i == m || visited[i + 1][j]) {
                    break;
                }
                i++;
            }
            while (true) {
                if (!visited[i][j]) {
                    result[visitedSize] = matrix[i][j];
                    visitedSize++;
                    visited[i][j] = true;
                }
                if (j == 0 || visited[i][j - 1]) {
                    break;
                }
                j--;
            }
            while (true) {
                if (!visited[i][j]) {
                    result[visitedSize] = matrix[i][j];
                    visitedSize++;
                    visited[i][j] = true;
                }
                if (i == 0 || visited[i - 1][j]) {
                    break;
                }
                i--;
            }
        }
        return result;
    }
}