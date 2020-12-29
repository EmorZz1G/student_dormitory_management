package com.management.manage;

import com.management.exception.NoCommandException;
import com.management.exception.QueryStopException;

import java.util.LinkedList;
import java.util.Objects;

public class Menu {
    private LinkedList<MenuItem> menuStack = new LinkedList<>();
    private static Menu menuInstance = null;
    public Menu addMenuItem(MenuItem menuItem){
        menuStack.add(menuItem);
        return this;
    }
    //单例模式
    public static Menu getInstance(){
        if(menuInstance==null){
            synchronized (Menu.class){
                if(menuInstance==null){
                    menuInstance = new Menu();
                }
            }
        }
        return menuInstance;
    }
    public Menu showCurrMenu(){
        menuStack.peekLast().show();
        return this;
    }
    public Object notifyTopMenuItem(String cmd) throws NoCommandException, QueryStopException {
        if (cmd.equals("q")){
            if(menuStack.size()==1){
                throw new QueryStopException("程序结束");
            }
            menuStack.pollLast();
            showCurrMenu();
        }
        return menuStack.peekLast().update(cmd);
    }
}
