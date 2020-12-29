package com.management.service;

import com.management.context.ApplicationContext;
import com.management.dao.base.Room;
import com.management.dao.ext.Dormitory;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DormitoryService implements BaseService {
    private static ApplicationContext applicationContext = ApplicationContext.getApplicationContextInstance();

    public LinkedList<Dormitory> queryAllDormitories() {
        return applicationContext.getDormitories();
    }

    public Dormitory queryDormitoryById(Integer id) {
        LinkedList<Dormitory> dormitories = queryAllDormitories();
        for (Dormitory dormitory : dormitories) {
            if (dormitory.getId().equals(id)) {
                return dormitory;
            }
        }
        return null;
    }

    public Dormitory queryDormitoriesByName(String name) {
        LinkedList<Dormitory> dormitories = queryAllDormitories();
        for (Dormitory dormitory : dormitories) {
            if (dormitory.getName().equals(name)) {
                return dormitory;
            }
        }
        return null;
    }

    public Dormitory queryDormitoriesByNameUsingRe(String name) {
        LinkedList<Dormitory> dormitories = queryAllDormitories();
        Pattern pattern = Pattern.compile(".*" + name + ".*");
        for (Dormitory dormitory : dormitories) {
            if (pattern.pattern().matches(dormitory.getName())) {
                return dormitory;
            }
        }
        return null;
    }

    public void addDormitory(Dormitory dormitory) {
        applicationContext.addDormitory(dormitory);
    }

    public void deleteDormitory(Dormitory dormitory) {
        applicationContext.deleteDormitory(dormitory);
    }

    public void udpateDormitory(Dormitory dormitory) throws IllegalAccessException {
        applicationContext.update(dormitory);
    }

    public void printTitle() {
        System.out.println("公寓ID\t\t公寓名字\t\t公寓额外描述");
    }

    public void printDormitory(Dormitory dormitory) {
        System.out.printf("%s\t\t\t%s\t\t\t%s\n", dormitory.getId(), dormitory.getName(), dormitory.getExt());
    }

    public void printDormitories(LinkedList<Dormitory> dormitories) {
        printTitle();
        dormitories.forEach(this::printDormitory);
    }
}
