package com.management.dao.ext;

import com.management.dao.base.Building;
import com.management.dao.base.Room;

import java.io.Serializable;
import java.util.LinkedList;

public class Dormitory extends Building implements Serializable {
    private LinkedList<Room> rooms = new LinkedList<>();

    public Dormitory() {
        super();
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "rooms=" + rooms +
                "} " + super.toString();
    }

    public Dormitory(String name, String ext) {
        super(name, ext);
    }

    public LinkedList<Room> getRooms() {
        return rooms;
    }
}
