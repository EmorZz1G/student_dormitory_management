package com.management.dao.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class Person implements Serializable {
    public Person() {
        id = Person.getPersonId();
    }

    public Person(String name, String birthday, String gender) {
        this.id = Person.getPersonId();
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }


    @SuppressWarnings("all")
    protected static Integer getPersonId() {
        PERSON_ID++;
        return PERSON_ID;
    }

    private final Integer id;
    private String name;
    //使用2020/12/12格式
    private String birthday;

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    private String gender;//性别
    private static Integer PERSON_ID = -1;

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void update(Person person) throws IllegalAccessException {
        Field[] fields = Person.class.getDeclaredFields();
        for (Field field : fields) {
            if(!field.get(this).equals(field.get(person))){
                field.set(this,field.get(person));
            }
        }
    }
}
