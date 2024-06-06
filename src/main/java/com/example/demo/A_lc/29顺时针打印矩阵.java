package com.example.demo.A_lc;
//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组 矩阵 模拟 👍 431 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution29 {
    // solutions第二个
    public int[] spiralOrder(int[][] matrix) {
        // 刚开始考虑设置一个boolean[][]记录已访问，麻烦且不容易理解
        /*
        if (matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length - 1, n = matrix[0].length - 1;
        int total = (m + 1) * (n + 1);
        int[] result = new int[total];
        boolean[][] visited = new boolean[m + 1][n + 1];
        int i = 0, j = 0, visitedSize = 0;// 记录当前访问位置和已经访问的总数
        while (visitedSize < total) {
            while (true) {
                // 在换方向时需要判断当前位置是否已访问，已经访问需要跳过
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
        */
        // 仔细想下，其实边界会移动的，每次方向调整，就意味着一个边界向相反方向移动1
        // 比如从左到右访问一遍，开始向下时，上边界向下移动1。任意一对相对的边界相撞，就代表访问完成
        if (matrix.length == 0) {
            return new int[0];
        }
        // 定义边界，size是已访问的
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, visited = 0;
        int[] result = new int[matrix.length * matrix[0].length];
        // 不断切换方向，直到某对相对的方向相撞
        while (true) {
            // 向右
            for (int i = left; i <= right; i++) {
                result[visited++] = matrix[top][i];
            }
            if (top++ == bottom) {
                break;
            }
            // 向下
            for (int i = top; i <= bottom; i++) {
                result[visited++] = matrix[i][right];
            }
            if (left == right--) {
                break;
            }
            // 向左
            for (int i = right; i >= left; i--) {
                result[visited++] = matrix[bottom][i];
            }
            if (top == bottom--) {
                break;
            }
            // 向上
            for (int i = bottom; i >= top; i--) {
                result[visited++] = matrix[i][left];
            }
            if (left++ == right) {
                break;
            }

        }
        return result;
    }

    public int[] spiralOrder111(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        int[] arr = new int[matrix.length * matrix[0].length];
        int i = 0, j = 0, t = 0;
        while (top != bottom || left != right) {
            while (i <= right) {
                arr[t++] = matrix[top][i++];
            }
            if (top++ == bottom) break;
            while (j <= bottom) {
                arr[t++] = matrix[j++][right];
            }
            if (right-- == left) break;
            while (i >= left) {
                arr[t++] = matrix[bottom][i--];
            }
            if (bottom-- == top) break;
            while (j >= top) {
                arr[t++] = matrix[j--][left];
            }
            if (left++ == right) break;
        }
        return arr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
