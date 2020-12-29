package com.management.manage;

import com.management.exception.NoCommandException;

public abstract class Command implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private Command nextCommand;
    private String cmd;
    private String name;

    public void show() {
        System.out.printf("%s: %s\n", cmd, name);
        if (nextCommand != null)
            nextCommand.show();
    }

    public String getName() {
        return name;
    }

    public Command setName(String name) {
        this.name = name;
        return this;
    }

    public Command getNextCommand() {
        return nextCommand;
    }

    public void setNextCommand(Command nextCommand) {
        this.nextCommand = nextCommand;
    }

    public String getCmd() {
        return cmd;
    }

    public Command setCmd(String cmd) {
        this.cmd = cmd;
        return this;
    }

    abstract Object execute();

    public Object executeCommand(String cmd) throws NoCommandException {
        if (this.cmd.equals(cmd)) {
            return execute();
        } else if (nextCommand == null) {
            throw new NoCommandException("没有对应的操作");
        } else {
            return nextCommand.executeCommand(cmd);
        }
    }

    public void addNext(Command cmd) {
        if (this.nextCommand == null) {
            this.nextCommand = cmd;
        } else {
            nextCommand.addNext(cmd);
        }
    }
}
