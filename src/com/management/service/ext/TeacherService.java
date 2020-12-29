package com.management.service.ext;

import com.management.dao.base.Person;
import com.management.service.PersonService;

import java.util.LinkedList;

public class TeacherService extends PersonService {
    @Override
    public LinkedList<Person> queryAll() {
        return applicationContext.getTeachers();
    }

    @Override
    public Person queryById(Integer id) {
        for (Person person : queryAll()) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    @Override
    // 可能有BUG
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
}
