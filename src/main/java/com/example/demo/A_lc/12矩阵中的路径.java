package com.example.demo.A_lc;//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board =
// [
//      ["A","B","C","E"],
//      ["S","F","C","S"],
//      ["A","D","E","E"]
// ],
// word = "ABCCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board =
// [
//      ["a","b"],
//      ["c","d"]
// ],
// word = "abcd"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/ 
// Related Topics 数组 回溯 矩阵 👍 623 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution12 {
    public boolean exist(char[][] board, String word) {
        // Solutions第二个描述的很明白，DFS+剪枝
        // 1.尝试矩阵中的每个元素，尝试深度优先遍历，
        // 有一个满足的即返回true，否则继续尝试下个元素
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, char[] words, int i, int j, int w) {
        // 2.深度优先遍历（递归），下、上、右、左的顺序进行遍历
        // 为了标记已访问的不能再访问，将访问过的标识为空，完成遍历后要恢复
        // 递归终止条件
        if (i > board.length - 1 || i < 0
                || j > board[0].length - 1 || j < 0
                || board[i][j] != words[w]) {
            return false;
        }
        if (w == words.length - 1) {
            return true;
        }
        // 将访问过的标识为空，这样访问时会被board[i][j] != words[w]控制住
        board[i][j] = '\0';
        // 这里按照下、上、右、左的顺序进行尝试，只要有一条通就继续往下，不通就放弃
        boolean res = dfs(board, words, i + 1, j, w + 1) || dfs(board, words, i - 1, j, w + 1)
                || dfs(board, words, i, j + 1, w + 1) || dfs(board, words, i, j - 1, w + 1);
        board[i][j] = words[w];
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) || dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }
}*/

