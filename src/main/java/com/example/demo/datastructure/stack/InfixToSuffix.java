package com.example.demo.datastructure.stack;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 中缀表达式转后缀表达式
 * <p>
 * 计算机容易识别的是前缀表达式和后缀表达式，
 * 将中缀表达式转换为前缀表达式或者后缀表达式之后，计算机能很快计算出表达式的值
 * </p>
 *
 * @author yangjinyu
 * @time 2021/6/9 9:33
 */
public class InfixToSuffix {

    public static void main(String[] args) {
//        System.out.println("enter infix");
//        Scanner scanner = new Scanner(System.in);
//        String infix = scanner.nextLine();
//        infixToSuffix(infix);
        calcSuffix(infixToSuffix("4*(2+7)-6/(2+1)"));
    }

    public static void calcSuffix(String input) {
        Stack<Integer> stack = new Stack<>();
        int num1, num2, result;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                stack.push(c - '0');//如果是数字，直接压入栈中
            } else {
                num2 = stack.pop();//注意先出来的为第二个操作数
                num1 = stack.pop();
                switch (c) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    default:
                        result = 0;
                        break;
                }
                stack.push(result);
            }
        }
        result = stack.pop();
        System.out.println(result);
    }

    /**
     * A*(B+C)-D/(E+F) 转成后缀表达式 ABC+*DEF+/-
     */
    public static String infixToSuffix(String infix) {
        Stack<Character> stack = new Stack<>();
        List<Character> list = new ArrayList<>();
        if (StringUtils.isNotBlank(infix)) {
            for (char c : infix.toCharArray()) {
                // 数字直接放入list
                if (Character.isLetterOrDigit(c)) {
                    list.add(c);
                    continue;
                }
                if (stack.isEmpty()) {
                    stack.push(c);
                    continue;
                }
                while (!stack.isEmpty()) {
                    // 栈顶为'('，入栈；c为'('，入栈
                    if ((stack.peek() == '(' && c != ')') || c == '(') {
                        stack.push(c);
                        break;
                    }
                    // c为')'，出栈直到遇到'('
                    if (c == ')') {
                        char cc = stack.pop();
                        if (cc == '(') {
                            break;
                        } else {
                            list.add(cc);
                        }
                    } else {
                        // 优先级高于栈顶，入栈； 否则栈顶出栈，继续比较
                        if (getOperatorPriority(c) > getOperatorPriority(stack.peek())) {
                            stack.push(c);
                            break;
                        } else {
                            list.add(stack.pop());
                            // 栈空后退出
                            if (stack.isEmpty()) {
                                stack.push(c);
                                break;
                            }
                        }
                    }
                }
            }
            // 将栈中剩余元素弹出
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
            list.forEach(System.out::print);
            System.out.println();
        }
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static int getOperatorPriority(char opr) {
        int priority;
        switch (opr) {
            case '+':
            case '-':
                priority = 1;
                break;
            case '*':
            case '/':
                priority = 2;
                break;
            default:
                priority = 0;
                break;
        }
        return priority;
    }
}