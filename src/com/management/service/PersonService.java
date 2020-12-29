package com.management.service;

import com.management.context.ApplicationContext;
import com.management.dao.base.Person;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class PersonService implements BaseService{
    protected static ApplicationContext applicationContext = ApplicationContext.getApplicationContextInstance();
    public LinkedList<Person> queryAll(){
        return applicationContext.getPeople();
    }
    public Person queryById(Integer id){
        for (Person person : queryAll()) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }
    public LinkedList<Person> queryByBirthday(String bir){
        LinkedList<Person> ret = new LinkedList<>();
        for (Person person : queryAll()) {
            if (person.getBirthday().equals(bir)) {
                ret.add(person);
            }
        }
        return ret;
    }
    public LinkedList<Person> queryByGender(String gender){
        LinkedList<Person> ret = new LinkedList<>();
        for (Person person : queryAll()) {
            if (person.getGender().equals(gender)) {
                ret.add(person);
            }
        }
        return ret;
    }

    public LinkedList<Person> queryByNameUsingRe(String name){
        LinkedList<Person> ret = new LinkedList<>();
        Pattern pattern = Pattern.compile(".*" + name + ".*");
        for (Person person : queryAll()) {
            if (pattern.pattern().matches(person.getName())) {
                ret.add(person);
            }
        }
        return ret;
    }
    public void addPerson(Person person){
        applicationContext.addPerson(person);
    }
    public void deletePerson(Person person){
        applicationContext.deletePerson(person);
    }
    public void updatePerson(Person person) throws IllegalAccessException {
        applicationContext.update(person);
    }
}
