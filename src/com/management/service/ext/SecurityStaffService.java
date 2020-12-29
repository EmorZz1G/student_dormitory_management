package com.management.service.ext;

import com.management.dao.base.Person;
import com.management.service.PersonService;

import java.util.LinkedList;

public class SecurityStaffService extends PersonService {
    @Override
    public LinkedList<Person> queryAll() {
        return applicationContext.getSecurityStaffs();
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
}
