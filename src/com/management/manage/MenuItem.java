package com.management.manage;

import com.management.exception.NoCommandException;

public class MenuItem {
    private String name;
    private Object curr;
    private Command firstCommand;

    //责任链模式
    public MenuItem addCommand(Command cmd){
        if(firstCommand==null){
            firstCommand = cmd;
        }else {
            firstCommand.addNext(cmd);
        }
        return this;
    }

    public MenuItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCurr() {
        return curr;
    }

    public void setCurr(Object curr) {
        this.curr = curr;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", curr=" + curr +
                '}';
    }

    public void show(){
        System.out.println("菜单: "+name);
        if(firstCommand==null){
            System.out.println("暂无");
        }
        else{
            firstCommand.show();
        }
    }

    public Object update(String cmd) throws NoCommandException {
        return firstCommand.executeCommand(cmd);
    }
}
