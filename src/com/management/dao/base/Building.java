package com.management.dao.base;

import com.management.dao.ext.Dormitory;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Building implements Serializable {
    private final Integer id;
    private String name;
    private String ext;
    private static Integer BUILDING_ID = -1;

    @SuppressWarnings("all")
    protected static Integer getBuildingId() {
        BUILDING_ID++;
        return BUILDING_ID;
    }

    public Building() {
        id = Building.getBuildingId();
    }

    public Building(String name, String ext) {
        this.id = Person.getPersonId();
        this.name = name;
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void update(Dormitory dormitory) throws IllegalAccessException {
        Field[] fields = Dormitory.class.getDeclaredFields();
        for (Field field : fields) {
            if(!field.get(this).equals(field.get(dormitory))){
                field.set(this,field.get(dormitory));
            }
        }
    }
}
