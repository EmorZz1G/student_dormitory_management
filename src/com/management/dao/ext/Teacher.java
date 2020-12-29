package com.management.dao.ext;

import com.management.dao.base.Person;
import com.management.dao.base.Room;

import java.io.Serializable;

public class Teacher extends Person implements Serializable {
    private String teacherId;//学号
    private Room<Teacher> room;
    private String bedNumber;//床号

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", room=" + room +
                ", bedNumber='" + bedNumber + '\'' +
                "} " + super.toString();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Room<Teacher> getRoom() {
        return room;
    }

    public void setRoom(Room<Teacher> room) {
        this.room = room;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
}
