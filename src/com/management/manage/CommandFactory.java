package com.management.manage;

import com.management.context.ApplicationContext;
import com.management.dao.base.Room;
import com.management.dao.ext.Dormitory;
import com.management.dao.ext.Student;
import com.management.exception.NoCommandException;
import com.management.service.DormitoryService;
import com.management.service.RoomService;
import com.management.service.ext.StudentService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();
    private DormitoryService dormitoryService = new DormitoryService();
    private RoomService roomService = new RoomService();
    private ApplicationContext applicationContext = ApplicationContext.getApplicationContextInstance();
    private Scanner scanner = ApplicationContext.getApplicationContextInstance().getScanner();
    private StudentService studentService = new StudentService();

    public static CommandFactory getInstance() {
        return factory;
    }

    private HashMap<String, Command> commands;

    private CommandFactory() {
        commands = new HashMap<>();
        dormitoryService = new DormitoryService();
        Command cmd = new Command() {
            @Override
            Object execute() {
                dormitoryService.printDormitories(dormitoryService.queryAllDormitories());
                ;
                return null;
            }
        }.setName("查询所有公寓");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                roomService.printRooms(roomService.queryAllRooms());
                return null;
            }
        }.setName("查询所有房间");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                studentService.printPeople(studentService.queryAll());
                return null;
            }
        }.setName("查询所有学生");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入公寓ID");
                Integer id = scanner.nextInt();
                dormitoryService.printDormitory(dormitoryService.queryDormitoryById(id));
                return null;
            }
        }.setName("查询公寓通过ID");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入学生ID");
                Integer id = scanner.nextInt();
                studentService.printPerson(studentService.queryById(id));
                return null;
            }
        }.setName("查询学生通过ID");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入房间ID");
                Integer id = scanner.nextInt();
                roomService.printRoom(roomService.queryRoomById(id));
                return null;
            }
        }.setName("查询房间通过ID");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入公寓名字");
                String name = scanner.next();
                dormitoryService.printDormitory(dormitoryService.queryDormitoriesByName(name));
                return null;
            }
        }.setName("查询公寓通过名字");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入学生名字");
                String name = scanner.next();
                studentService.printPeople(studentService.queryByNameUsingRe(name));
                return null;
            }
        }.setName("查询学生通过名字，使用正则表达式");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入学生性别");
                String name = scanner.next();
                studentService.printPeople(studentService.queryByGender(name));
                return null;
            }
        }.setName("查询学生通过性别");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入房间名字");
                String name = scanner.next();
                roomService.printRooms(roomService.queryRoomsByNameUsingRe(name));
                return null;
            }
        }.setName("查询房间通过名字，使用正则表达式");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入公寓名字");
                String name = scanner.next();
                dormitoryService.printDormitory(dormitoryService.queryDormitoriesByNameUsingRe(name));
                return null;
            }
        }.setName("查询公寓通过名字，使用正则表达式");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入房间额外描述");
                String name = scanner.next();
                roomService.printRooms(roomService.queryRoomsByExtUsingRe(name));
                return null;
            }
        }.setName("查询房间通过额外描述，使用正则表达式");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("输入房间号");
                String name = scanner.next();
                roomService.printRooms(roomService.queryRoomsByRoomNumberUsingRe(name));
                return null;
            }
        }.setName("查询房间通过房间号，使用正则表达式");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("公寓名字");
                String s = scanner.next();
                System.out.println("公寓额外描述");
                String s1 = scanner.next();
                Dormitory dormitory = new Dormitory(s, s1);
                applicationContext.addDormitory(dormitory);
                return dormitory;
            }
        }.setName("增加公寓");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("房间名字");
                String name = scanner.next();
                System.out.println("房间号");
                String roomNum = scanner.next();
                System.out.println("房间额外描述");
                String ext = scanner.next();
                Room room = new Room(name, roomNum, ext);
                applicationContext.addRoom(room);
                return null;
            }
        }.setName("增加房间");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("请按以下顺序输入，以空格分隔参数");
                studentService.printTitleForInput();
                scanner.nextLine();
                String[] s = scanner.nextLine().split("\\s+");
//                System.out.println(Arrays.asList(s));
                Student student = new Student(s[0], s[1], s[2], s[3], s[4], s[5], null, s[6]);
                applicationContext.addPerson(student);
                return null;
            }
        }.setName("增加学生");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("公寓ID");
                applicationContext.deleteDormitory(scanner.nextInt());
                return null;
            }
        }.setName("删除公寓");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("房间ID");
                applicationContext.deleteRoom(scanner.nextInt());
                return null;
            }
        }.setName("删除房间");
        commands.put(cmd.getName(), cmd);
        cmd = new Command() {
            @Override
            Object execute() {
                System.out.println("学生ID");
                applicationContext.deletePerson(scanner.nextInt());
                return null;
            }
        }.setName("删除学生");
        commands.put(cmd.getName(), cmd);
    }

    public CommandFactory addCommand(String name, Command cmd) {
        commands.put(name, cmd);
        return this;
    }

    public Command getCommandCopy(String name) throws NoCommandException, CloneNotSupportedException {
        Command command = commands.get(name);
        if (command == null) {
            throw new NoCommandException("工厂中没有这个命令");
        }
        return (Command) command.clone();
    }
}
