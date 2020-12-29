package com.management.manage;

import com.management.context.ApplicationContext;
import com.management.exception.NoCommandException;
import com.management.exception.QueryStopException;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
    private CommandFactory factory = CommandFactory.getInstance();
    private Menu menu = Menu.getInstance();
    private static ApplicationContext applicationContext = ApplicationContext.getApplicationContextInstance();
    private static Scanner scanner = ApplicationContext.getApplicationContextInstance().getScanner();
    private static Controller controller = new Controller();
    private MenuItem queryItem = null;
    private MenuItem queryItemForPerson = null;
    private MenuItem addItem = null;
    private MenuItem deleteItem = null;
    private MenuItem updateItem = null;

    public Controller getInstance() {
        return controller;
    }

    private void init() throws NoCommandException, CloneNotSupportedException {
        MenuItem first = new MenuItem("欢迎来到学生公寓管理系统，在任意菜单输入q返回上一级");

        queryItem = new MenuItem("查询");
        Command q1 = factory.getCommandCopy("查询所有公寓")
                .setCmd("1");
        q1.addNext(factory.getCommandCopy("查询公寓通过ID")
                .setCmd("2"));
        q1.addNext(factory.getCommandCopy("查询公寓通过名字")
                .setCmd("3"));
        q1.addNext(factory.getCommandCopy("查询公寓通过名字，使用正则表达式")
                .setCmd("4"));
        q1.addNext(factory.getCommandCopy("查询所有房间")
                .setCmd("5"));
        q1.addNext(factory.getCommandCopy("查询房间通过ID")
                .setCmd("6"));
        q1.addNext(factory.getCommandCopy("查询房间通过名字，使用正则表达式")
                .setCmd("7"));
        q1.addNext(factory.getCommandCopy("查询房间通过额外描述，使用正则表达式")
                .setCmd("8"));
        q1.addNext(factory.getCommandCopy("查询房间通过房间号，使用正则表达式")
                .setCmd("9"));
        queryItem.addCommand(q1);

        queryItemForPerson = new MenuItem("查询学生");
        Command q11 = factory.getCommandCopy("查询所有学生")
                .setCmd("1");
        q11.addNext(factory.getCommandCopy("查询学生通过ID")
                .setCmd("2"));
        q11.addNext(factory.getCommandCopy("查询学生通过名字，使用正则表达式")
                .setCmd("3"));
        q11.addNext(factory.getCommandCopy("查询学生通过性别")
                .setCmd("4"));
        queryItemForPerson.addCommand(q11);

        addItem = new MenuItem("增加");
        Command q2 = factory.getCommandCopy("增加公寓").setCmd("1");
        q2.addNext(factory.getCommandCopy("增加房间").setCmd("2"));
        q2.addNext(factory.getCommandCopy("增加学生").setCmd("3"));
        addItem.addCommand(q2);

        deleteItem = new MenuItem("删除");
        Command q3 = factory.getCommandCopy("删除公寓").setCmd("1");
        q3.addNext(factory.getCommandCopy("删除房间").setCmd("2"));
        q3.addNext(factory.getCommandCopy("删除学生").setCmd("3"));
        deleteItem.addCommand(q3);
//        updateItem = new MenuItem("修改");
        Command gotoQuery = new Command() {
            @Override
            Object execute() {
                menu.addMenuItem(queryItem);
                return null;
            }
        }.setCmd("1").setName("查询");
        Command gotoQueryPerson = new Command() {
            @Override
            Object execute() {
                menu.addMenuItem(queryItemForPerson);
                return null;
            }
        }.setCmd("11").setName("查询学生");
        Command gotoAdd = new Command() {
            @Override
            Object execute() {
                menu.addMenuItem(addItem);
                return null;
            }
        }.setCmd("2").setName("增加");
        Command gotoDelete = new Command() {
            @Override
            Object execute() {
                menu.addMenuItem(deleteItem);
                return null;
            }
        }.setCmd("3").setName("删除");
//        Command gotoUpdate = new Command() {
//            @Override
//            Object execute() {
//                menu.addMenuItem(updateItem);
//                return null;
//            }
//        }.setCmd("4").setName("修改");
        Command saveAll = new Command() {
            @Override
            Object execute() {
                try {
                    applicationContext.saveAll();
                    System.out.println("保存成功");
                } catch (IOException e) {
                    System.out.println("保存失败");
                    e.printStackTrace();
                }
                return null;
            }
        }.setCmd("4").setName("保存数据");
        gotoQuery.addNext(gotoQueryPerson);
        gotoQuery.addNext(gotoAdd);
        gotoQuery.addNext(gotoDelete);
//        gotoQuery.addNext(gotoUpdate);
        gotoQuery.addNext(saveAll);
        first.addCommand(gotoQuery);


        //主菜单添加（主页面）
        menu.addMenuItem(first);
    }

    public static void main(String[] args) {
        try {
            controller.init();
        } catch (NoCommandException | CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("异常结束");
            return;
        }
        while (true) {
            controller.menu.showCurrMenu();
            System.out.println("选择您的操作");
            try {
                controller.menu.notifyTopMenuItem(scanner.next());
            } catch (NoCommandException e) {
                System.out.println(e.getMessage());
            } catch (QueryStopException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }
}
