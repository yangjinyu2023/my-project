//请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
//
// 数值（按顺序）可以分成以下几个部分：
//
//
// 若干空格
// 一个 小数 或者 整数
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数
// 若干空格
//
//
// 小数（按顺序）可以分成以下几个部分：
//
//
// （可选）一个符号字符（'+' 或 '-'）
// 下述格式之一：
//
// 至少一位数字，后面跟着一个点 '.'
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
// 一个点 '.' ，后面跟着至少一位数字
//
//
//
//
// 整数（按顺序）可以分成以下几个部分：
//
//
// （可选）一个符号字符（'+' 或 '-'）
// 至少一位数字
//
//
// 部分数值列举如下：
//
//
// ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
//
//
// 部分非数值列举如下：
//
//
// ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
//
//
//
//
// 示例 1：
//
//
//输入：s = "0"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "e"
//输出：false
//
//
// 示例 3：
//
//
//输入：s = "."
//输出：false
//
// 示例 4：
//
//
//输入：s = "    .1  "
//输出：true
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 20
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
//
// Related Topics 字符串 👍 353 👎 0
package com.example.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Deterministic Finite Automaton
 * 确定有穷自动机
 *
 * @author yangjinyu
 * @time 2022/7/1 16:51
 */
public class DFA {
    // 剑指offer20
    public boolean isNumber(String s) {
        // 有限状态自动机
        // 确定输入集（toCharType方法）
        // 确定状态，绘制状态转移图
        // 根据迁移图编写状态转移函数
        Map<State, Map<CharType, State>> transfer = defineTransferStates();
        State state = State.STATE_INITIAL;
        for (char ch : s.toCharArray()) {
            CharType type = toCharType(ch);
            // 看看当前状态是否包含ch的类型
            if (!transfer.get(state).containsKey(type)) {
                return false;
            } else {
                // 包含的话，看该类型能达到的下个状态
                state = transfer.get(state).get(type);
            }
        }
        // 确定结束状态
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    // 本题输入集包括：空格、正负号、数字、e或E、其他
    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else if (ch == ' ') {
            return CharType.CHAR_SPACE;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    public Map<State, Map<CharType, State>> defineTransferStates() {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        // 状态：起始的空格
        Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
            // 能转换的状态
            put(CharType.CHAR_SPACE, State.STATE_INITIAL);// 起始的空格
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);// 整数部分
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);// 左侧无整数的小数点
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);// 符号位
        }};
        transfer.put(State.STATE_INITIAL, initialMap);

        // 状态：符号位
        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            // 能转换的状态
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);// 整数部分
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);// 左侧无整数的小数点
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);

        // 其余的状态，及其能转换的状态
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_POINT, pointMap);
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);
        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
        Map<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, endMap);
        return transfer;
    }

    enum State {
        // 起始的空格
        STATE_INITIAL,
        // 符号位
        STATE_INT_SIGN,
        // 整数部分
        STATE_INTEGER,
        // 左侧有整数的小数点
        STATE_POINT,
        // 左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
        STATE_POINT_WITHOUT_INT,
        // 小数部分
        STATE_FRACTION,
        // 字符 e
        STATE_EXP,
        // 指数部分的符号位
        STATE_EXP_SIGN,
        // 指数部分的整数部分
        STATE_EXP_NUMBER,
        // 末尾的空格
        STATE_END
    }

    enum CharType {CHAR_NUMBER, CHAR_EXP, CHAR_POINT, CHAR_SIGN, CHAR_SPACE, CHAR_ILLEGAL}

}