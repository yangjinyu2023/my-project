package com.example.demo.A_lc;
//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// Related Topics 深度优先搜索 广度优先搜索 动态规划 👍 523 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution13 {
    int m, n, k;
    boolean[][] visited;

    static class Cell {
        int i, j, si, sj;

        public Cell(int i, int si, int j, int sj) {
            this.i = i;
            this.j = j;
            this.si = si;
            this.sj = sj;
        }
    }

    // 看solutions第二个
    public int movingCount(int m, int n, int k) {
        // 方法一：DFS，递归+回溯
        //this.m = m;
        //this.n = n;
        //this.k = k;
        //this.visited = new boolean[m][n];
        //return dfs(0, 0, 0, 0);
        // 方法二：BFS，迭代
        this.visited = new boolean[m][n];
        int count = 0;
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(0, 0, 0, 0));
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            if (cell.i >= m || cell.j >= n || cell.si + cell.sj > k || visited[cell.i][cell.j]) {
                continue;
            }
            visited[cell.i][cell.j] = true;
            count++;
            // 向下
            queue.offer(new Cell(cell.i + 1, nextDigit(cell.i, cell.si), cell.j, cell.sj));
            // 向右
            queue.offer(new Cell(cell.i, cell.si, cell.j + 1, nextDigit(cell.j, cell.sj)));
        }
        return count;
    }

    // 递归参数，在方格中的位置i，j，和对应的数位si，sj
    public int dfs(int i, int j, int si, int sj) {
        // 递归结束条件，i、j超出m、n限制，或者si+sj超出k限制，或者已经被访问过
        if (i >= m || j >= n || si + sj > k || visited[i][j]) {
            return 0;
        }
        // 递推工作，将访问过记录到visited中，并继续访问（向下，向右）
        visited[i][j] = true;
        // 本次到达的格子数1，加上回溯返回值
        return 1 + dfs(i + 1, j, nextDigit(i, si), sj) + dfs(i, j + 1, si, nextDigit(j, sj));
    }

    // 计算i+1的数位
    public int nextDigit(int i, int si) {
        // 分两种情况，
        // 如果i+1取余为0，s(i+1)=si-8，比如19,20数位10,2
        // 如果i+1取余不为0，s(i+1)=si+1，比如1,2数位1,2
        return (i + 1) % 10 == 0 ? si - 8 : si + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
class Solution {
    int m, n, k;
    boolean[][] visited;
    public int movingCount(int m, int n, int k) {
        this.m = m; this.n = n; this.k = k;
        this.visited = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }
    public int dfs(int i, int j, int si, int sj) {
        if(i >= m || j >= n || k < si + sj || visited[i][j]) return 0;
        visited[i][j] = true;
        return 1 + dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj) + dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
    }
}
 */