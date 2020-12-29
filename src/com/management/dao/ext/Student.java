package com.management.dao.ext;

import com.management.dao.base.Person;
import com.management.dao.base.Room;

import java.io.Serializable;
import java.util.Date;

public class Student extends Person implements Serializable {
    private String clazz;
    private String grade;//年级
    private String studentId;//学号

    public Student() {

    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Room<Student> getRoom() {
        return room;
    }

    public void setRoom(Room<Student> room) {
        this.room = room;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    private Room<Student> room;
    private String bedNumber;//床号

    @Override
    public String toString() {
        return "Student{" +
                "clazz='" + clazz + '\'' +
                ", grade='" + grade + '\'' +
                ", studentId='" + studentId + '\'' +
                ", room=" + room +
                ", bedNumber='" + bedNumber + '\'' +
                "} " + super.toString();
    }

    public Student(String clazz, String grade, String studentId, Room<Student> room, String bedNumber) {
        this.clazz = clazz;
        this.grade = grade;
        this.studentId = studentId;
        this.room = room;
        this.bedNumber = bedNumber;
    }

    public Student(String name, String birthday, String gender, String clazz, String grade, String studentId, Room<Student> room, String bedNumber) {
        super(name, birthday, gender);
        this.clazz = clazz;
        this.grade = grade;
        this.studentId = studentId;
        this.room = room;
        this.bedNumber = bedNumber;
    }
}
