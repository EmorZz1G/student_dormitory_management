package com.management.context;

import com.management.dao.base.Person;
import com.management.dao.base.Room;
import com.management.dao.ext.Dormitory;
import com.management.dao.ext.SecurityStaff;
import com.management.dao.ext.Student;
import com.management.dao.ext.Teacher;
import com.alibaba.fastjson.*;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class ApplicationContext implements Serializable {
    private ApplicationContext() throws ClassNotFoundException {
        ObjectInputStream ois = null;
        ApplicationContext tmp = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(
                    new File("./application")
            )));
            tmp = (ApplicationContext) ois.readObject();
        } catch (IOException e) {
            System.out.println("文件不存在");
            this.dormitories = new LinkedList<>();
            this.rooms = new LinkedList<>();
            this.students = new LinkedList<>();
            this.securityStaffs = new LinkedList<>();
            this.teachers = new LinkedList<>();
            this.people = new LinkedList<>();
            return;
        }

        this.dormitories = tmp.dormitories;
        this.rooms = tmp.rooms;
        this.securityStaffs = tmp.securityStaffs;
        this.teachers = tmp.teachers;
        this.people = tmp.people;
        this.students = tmp.students;
    }

    public Scanner getScanner() {
        return scanner;
    }

    private static ApplicationContext applicationContextInstance = null;
    private transient Scanner scanner = new Scanner(new BufferedInputStream(System.in));
    private LinkedList<Person> people;
    private LinkedList<Person> students;
    private LinkedList<Person> teachers;
    private LinkedList<Person> securityStaffs;
    private LinkedList<Room> rooms;//所有房间，不同公寓的在内
    private LinkedList<Dormitory> dormitories;

    public ApplicationContext deletePerson(Person p) {
        if (p instanceof Student) {
            students.remove(p);
        } else if (p instanceof Teacher) {
            teachers.remove(p);
        } else if (p instanceof SecurityStaff) {
            securityStaffs.remove(p);
        }
        people.remove(p);
        return this;
    }

    public ApplicationContext addDormitory(Dormitory dormitory) {
        if (!dormitories.contains(dormitory))
            dormitories.add(dormitory);
        return this;
    }

    public ApplicationContext deleteDormitory(Dormitory dormitory) {
        dormitories.remove(dormitory);
        return this;
    }

    public ApplicationContext deleteDormitory(Integer dormitoryId) {
        for (Dormitory dormitory : dormitories) {
            if (dormitory.getId().equals(dormitoryId)) {
                dormitories.remove(dormitory);
                break;
            }
        }
        return this;
    }

    public ApplicationContext addRoom(Room room) {
        if (!rooms.contains(room))
            rooms.add(room);
        return this;
    }

    public ApplicationContext deleteRoom(Room room) {
        rooms.remove(room);
        return this;
    }

    public ApplicationContext addPerson(Person p) {
        if (p instanceof Student) {
            if (!students.contains(p))
                students.add(p);
        } else if (p instanceof Teacher) {
            if (!teachers.contains(p))
                teachers.add(p);
        } else if (p instanceof SecurityStaff) {
            if (!securityStaffs.contains(p))
                securityStaffs.add(p);
        }
        if (!people.contains(p))
            people.add(p);
        return this;
    }

    //单例模式
    public static ApplicationContext getApplicationContextInstance() {
        if (applicationContextInstance == null) {
            synchronized (ApplicationContext.class) {
                if (applicationContextInstance == null) {
                    try {
                        applicationContextInstance = new ApplicationContext();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("失败");
                    }
                }
            }
        }
        return applicationContextInstance;
    }

    public LinkedList<Person> getPeople() {
        return people;
    }

    public LinkedList<Person> getStudents() {
        return students;
    }

    public LinkedList<Person> getTeachers() {
        return teachers;
    }

    public LinkedList<Person> getSecurityStaffs() {
        return securityStaffs;
    }

    public LinkedList<Room> getRooms() {
        return rooms;
    }

    public LinkedList<Dormitory> getDormitories() {
        return dormitories;
    }

    public void update(Person person) throws IllegalAccessException {
        for (Person person1 : people) {
            if (person1.getId().equals(person.getId())) {
                person1.update(person);
                return;
            }
        }
    }

    public void update(Room room) throws IllegalAccessException {
        for (Room room1 : rooms) {
            if (room1.getId().equals(room.getId())) {
                room1.update(room);
                return;
            }
        }
    }

    public void update(Dormitory dormitory) throws IllegalAccessException {
        for (Dormitory room1 : dormitories) {
            if (room1.getId().equals(dormitory.getId())) {
                room1.update(dormitory);
                return;
            }
        }
    }

    public void saveAll() throws IOException {

        ObjectOutputStream stream;
        stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
                new File("./application"))
        ));

        stream.writeObject(this);
        stream.close();
        System.out.println("保存成功！");
//        ObjectOutputStream stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
//                new File("./people")
//        )));
//        stream.writeObject(people);
//
//        stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
//                new File("./students")
//        )));
//        stream.writeObject(students);
//
//        stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
//                new File("./teachers")
//        )));
//        stream.writeObject(teachers);
//
//        stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
//                new File("./securityStaffs")
//        )));
//        stream.writeObject(securityStaffs);
//
//        stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
//                new File("./dormitories")
//        )));
//        stream.writeObject(dormitories);
//
//        stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
//                new File("./rooms")
//        )));
//        stream.writeObject(rooms);
//        stream.close();

    }

    public void deleteRoom(int id) {
        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                rooms.remove(room);
                break;
            }
        }
    }

    public void deletePerson(int nextInt) {
        for (Person person : people) {
            if (person.getId().equals(nextInt)) {
                people.remove(person);
                break;
            }
        }
    }
}
