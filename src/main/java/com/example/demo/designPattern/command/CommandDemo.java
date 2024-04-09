package com.example.demo.designPattern.command;

/**
 * 命令模式
 *
 * @author yangjinyu
 * @time 2022/4/29 15:22
 */
public class CommandDemo {
    //接收者角色（Receiver）：负责具体执行一个请求
    //命令角色（ICommand）：定义需要执行的所有命令行为
    //具体的命令角色（ConcreteCommand）：内部维护一个Receiver
    //请求者角色（Invoker）：接收客户端的命令，并执行命令
    public static void main(String[] args) {
        // 命令模式属于行为模式，将请求方和接收方解耦
        // 这里请求方guest，接收方restaurant和bar
        Guest guest = new Guest();
        guest.addCommands(new EatCommand());
        guest.addCommands(new DrinkCommand());
        guest.action();
    }
}