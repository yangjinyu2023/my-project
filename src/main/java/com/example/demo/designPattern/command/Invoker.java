package com.example.demo.designPattern.command;

import java.util.ArrayList;
import java.util.List;

public abstract class Invoker {
    private List<ICommand> iCommands;

    public Invoker() {
        this.iCommands = new ArrayList<>();
    }

    public void addCommands(ICommand iCommand) {
        this.iCommands.add(iCommand);
    }

    public void action() {
        for (ICommand iCommand : iCommands) {
            iCommand.execute();
        }
    }
}
