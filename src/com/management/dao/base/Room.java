package com.management.dao.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;

public class Room<T extends Person> implements Serializable {
    private final Integer id;
    private String name;
    private String roomNumber;
    private String ext;
    private static Integer ROOM_ID = -1;
    private LinkedList<T> personsInRoom = new LinkedList<>();

    public LinkedList<T> getPersonsInRoom() {
        return personsInRoom;
    }

    public void setPersonsInRoom(LinkedList<T> personsInRoom) {
        this.personsInRoom = personsInRoom;
    }

    public Room() {
        id = getRoomId();
    }

    public Room(String name, String roomNumber, String ext) {
        this.id = getRoomId();
        this.name = name;
        this.roomNumber = roomNumber;
        this.ext = ext;
    }

    @SuppressWarnings("all")
    protected Integer getRoomId(){
        ROOM_ID++;
        return ROOM_ID;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void update(Room room) throws IllegalAccessException {
        Field[] fields = Room.class.getDeclaredFields();
        for (Field field : fields) {
            if(!field.get(this).equals(field.get(room))){
                field.set(this,field.get(room));
            }
        }
    }
}
