package com.management.service.ext;

import com.management.dao.base.Person;
import com.management.dao.ext.Student;
import com.management.service.PersonService;

import java.util.LinkedList;

public class StudentService extends PersonService {
    @Override
    public LinkedList<Person> queryAll() {
        return applicationContext.getStudents();
    }

    @Override
    public Person queryById(Integer id) {
        return super.queryById(id);
    }

    @Override
    public LinkedList<Person> queryByBirthday(String bir) {
        return super.queryByBirthday(bir);
    }

    @Override
    public LinkedList<Person> queryByGender(String gender) {
        return super.queryByGender(gender);
    }

    @Override
    public LinkedList<Person> queryByNameUsingRe(String name) {
        return super.queryByNameUsingRe(name);
    }

    public void printTitle() {
        System.out.println("学生ID\t\t学生名字\t\t生日\t\t性别\t\t班级\t\t年纪\t\t学号\t\t床号");
    }
    public void printTitleForInput() {
        System.out.println("学生名字\t\t生日\t\t性别\t\t班级\t\t年纪\t\t学号\t\t床号");
    }

    public void printPerson(Person s) {
        Student student = (Student)s;
        System.out.printf("%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\n",
                student.getId(), student.getName(), student.getBirthday(), student.getGender(),
                student.getClazz(),student.getGrade(),student.getStudentId(),student.getBedNumber());
    }

    public void printPeople(LinkedList<Person> queryAllRooms) {
        printTitle();
        queryAllRooms.forEach(this::printPerson);
    }
}
