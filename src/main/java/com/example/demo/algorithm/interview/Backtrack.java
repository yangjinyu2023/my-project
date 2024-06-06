package com.example.demo.algorithm.interview;

/**
 * 回溯专题
 *
 * @author yangjinyu
 * @time 2024/6/5 17:54
 */
public class Backtrack {
    //1、八皇后问题
    //在标准的8x8国际象棋棋盘上放置八个皇后，要求皇后之间不能互相攻击，即任意两个皇后不能位于同一行、同一列或同一对角线上。
    //任务是找出所有可能的放置方法，使得这八个皇后彼此之间都是安全的。
    //
    //具体来说，八皇后问题的解需要满足以下三个条件：
    //每行只能放置一个皇后。
    //每列只能放置一个皇后。
    //任何两个皇后不能位于两条对角线上（即主对角线和副对角线）。
    static class EightQueens {
        public static int solutionCount = 0;

        public static void main(String[] args) {
            int size = 8;
            int[] board = new int[size];
            placeQueens(size, board, 0);
            System.out.printf("There are %d solutions.\n", solutionCount);
        }

        /**
         * 为什么说八皇后的解法用了回溯：
         * 在八皇后问题的Java实现中，确实存在隐式的“回到上一状态”的操作，这正是通过递归调用的机制实现的。让我们仔细看看这个过程：
         * 在递归函数placeQueens中，当尝试在一个特定的行放置皇后时，会通过一个循环遍历该行的所有列。对于每一列，如果通过isValid检查发现当前位置可以放置皇后，则在该列放置皇后（通过更新board[row] = col;），然后递归调用自身处理下一行，即placeQueens(size, board, row + 1);。这里，关键点在于：
         * 递归调用：每次递归调用实际上是在探索一个更深的决策树分支，尝试在新的一行放置皇后。
         * 回溯：当在某一行无法找到合适的列放置皇后（即所有的列尝试都失败了），递归调用会自然返回到上一层调用（即上一行），这时因为之前对board[row]的赋值（即放置皇后）并未影响到外部的board状态（因为是在当前函数栈帧内的局部操作），所以当函数返回时，相当于撤销了在该行的决策，回到了放置前的状态，然后尝试下一列。这就是回溯的过程。
         * 因此，尽管代码中没有显式地写着“回到上一状态”，但通过递归函数的返回和循环的继续，实际上实现了回溯逻辑，不断地尝试、撤销（通过函数返回）并尝试新的可能性，直到找到一个完整的解或者所有可能的排列都被探索完毕。
         *
         * @param size
         * @param board
         * @param row
         */
        public static void placeQueens(int size, int[] board, int row) {
            if (row == size) {
                solutionCount++;
                printBoard(board, size);
                return;
            }

            for (int col = 0; col < size; col++) {
                if (isValid(board, row, col)) {
                    board[row] = col;
                    placeQueens(size, board, row + 1);
                }
            }
        }

        /**
         * isValid 方法在这个八皇后问题的解决方案中起到了关键作用，它负责检查在棋盘的特定行和列放置皇后是否安全，即是否满足不被其他皇后攻击的条件。具体来说，这个方法有三个主要检查点：
         * 1、同一列检查 (board[r] == col): 这个条件检查当前想要放置皇后的列 (col) 是否已经在之前某一行 (r) 放置过皇后。如果是，说明在同一列已经有皇后，因此当前位置不安全。
         * 2、左上到右下对角线检查 (board[r] - r == col - row): 这个条件用来检查从当前位置到棋盘左上角的对角线上是否有其他皇后。计算方式是，对于已放置的每一个皇后，其列坐标减去行坐标应等于一个常数（这条对角线的斜率）。如果当前尝试放置的皇后也满足这个等式，说明它将位于一条已经被占据的对角线上。
         * 3、右上到左下对角线检查 (board[r] + r == col + row): 类似地，这个条件检查从当前位置到棋盘右上角的对角线。计算方式是，对于已放置的皇后，其列坐标加上行坐标也应等于一个常数（这条对角线的另一个斜率）。如果新皇后的位置也符合这个条件，说明它将位于一条已有皇后的对角线上。
         * 如果上述任何一个条件成立，说明在当前位置放置皇后会导致与之前放置的皇后冲突，因此 isValid 方法返回 false，表示当前位置不安全。只有当这三个条件都不满足时，即新皇后不会与任何已放置的皇后冲突，isValid 方法才返回 true，表明当前位置是放置皇后的一个有效选择。
         *
         * @param board
         * @param row
         * @param col
         * @return
         */
        public static boolean isValid(int[] board, int row, int col) {
            for (int r = 0; r < row; r++) {
                if (board[r] == col || // same column
                        board[r] - r == col - row || // same left-up diagonal
                        board[r] + r == col + row) { // same right-down diagonal
                    return false;
                }
            }
            return true;
        }

        public static void printBoard(int[] board, int size) {
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    System.out.print(board[r] == c ? "Q " : ".");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}