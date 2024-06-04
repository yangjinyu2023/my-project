package com.example.demo.A_lc;
//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。 
// 
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
// 
// Related Topics 递归 字符串 动态规划 👍 408 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution19 {
    // solutions第三个
    public boolean isMatch(String s, String p) {
        // 动态规划
        // dp[i][j]代表s的前i个和p的前j个是否匹配，dp[i][j]依赖于前面的状态，使用动态规划
        // 初始值
        // dp[0][0]=true即p为空s为空能匹配上；
        // dp[i][0]一定为false（boolean默认是false所以无需初始化）；
        // dp[0][j]需要看p的偶数位是不是*，偶数位是*为true，奇数位一定是false
        // 状态转移方程
        // （1）当p[j-1]为*（拿aaa，ab*.*举例）：
        //      去掉p的.*看看是否能匹配，即dp[i][j-2]；
        //      看看p的.和s的a是否相等，如果相等再看剩余的aa和p是否匹配，即p[j-2]==s[i-1] && dp[i-1][j]；
        //      看看p的.是不是.，如果是再看剩余的aa和p是否匹配，即p[j-2]=='.' && dp[i-2][j]
        // （2）当p[j-1]不为*（拿aaa，aa.举例）：
        //      看看p的.和s的a是否相等，如果相等再看看剩余的aa和aa是否匹配，即p[j-1]==s[i-1] && dp[i-1][j-1]
        //      看看p的.是不是.，如果是再看看剩余的aa和aa是否匹配，即p[j-1]=='.' && dp[i-1][j-1]
        // 返回值
        // dp[m-1][n-1]
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for (int j = 2; j < n; j += 2) dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = p.charAt(j - 1) == '*'
                        ? dp[i][j - 2] || dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')
                        : dp[i - 1][j - 1] && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1));
            }
            // 为什么dp[i][j - 2]不会数组越界？
            // 已经确保了 j 至少为 2（因为 * 不会是模式 p 的第一个字符，按照正则表达式的规则，* 前面需要有一个字符）
        }
        return dp[m - 1][n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
