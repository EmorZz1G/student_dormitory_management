package com.management.service;

import com.management.context.ApplicationContext;
import com.management.dao.base.Person;
import com.management.dao.base.Room;
import com.management.dao.ext.Dormitory;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class RoomService<T extends Person> implements BaseService {
    private static ApplicationContext applicationContext = ApplicationContext.getApplicationContextInstance();

    public LinkedList<Room> queryAllRooms() {
        return applicationContext.getRooms();
    }

    public Room queryRoomById(Integer id) {
        for (Room room : queryAllRooms()) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public LinkedList<Room> queryRoomsByNameUsingRe(String name) {
        LinkedList<Room> rooms = queryAllRooms();
        Pattern pattern = Pattern.compile(".*" + name + ".*");
        LinkedList<Room> ret = new LinkedList<>();
        for (Room room : rooms) {
            if (pattern.pattern().matches(room.getName())) {
                ret.add(room);
            }
        }
        return ret;
    }

    public LinkedList<Room> queryRoomsByRoomNumberUsingRe(String num) {
        LinkedList<Room> rooms = queryAllRooms();
        Pattern pattern = Pattern.compile(".*" + num + ".*");
        LinkedList<Room> ret = new LinkedList<>();
        for (Room room : rooms) {
            if (pattern.pattern().matches(room.getRoomNumber())) {
                ret.add(room);
            }
        }
        return ret;
    }

    public LinkedList<Room> queryRoomsByExtUsingRe(String ext) {
        LinkedList<Room> rooms = queryAllRooms();
        Pattern pattern = Pattern.compile(".*" + ext + ".*");
        LinkedList<Room> ret = new LinkedList<>();
        for (Room room : rooms) {
            if (pattern.pattern().matches(room.getExt())) {
                ret.add(room);
            }
        }
        return ret;
    }

    public void addRoom(Room room) {
        applicationContext.addRoom(room);
    }

    public void deleteRoom(Room room) {
        applicationContext.deleteRoom(room);
    }

    public void update(Room room) throws IllegalAccessException {
        applicationContext.update(room);
    }

    public void addPersonToRoom(Room<T> room, T person) {
        LinkedList<T> personsInRoom = room.getPersonsInRoom();
        for (int i = 0, n = personsInRoom.size(); i <= n; i++) {
            if (i == n) {
                personsInRoom.add(person);
            }
            if (personsInRoom.get(i).getId().equals(person.getId())) {
                break;
            }
        }
    }

    public void removePersonFromRoom(Room<T> room, T person) {
        LinkedList<T> personsInRoom = room.getPersonsInRoom();
        for (T t : personsInRoom) {
            if (t.getId().equals(person.getId())) {
                personsInRoom.remove(t);
                break;
            }
        }
    }

    public void printTitle() {
        System.out.println("房间ID\t\t房间名字\t\t房间号\t\t房间额外描述");
    }

    public void printRoom(Room dormitory) {
        System.out.printf("%s\t\t\t%s\t\t\t%s\t\t\t%s\n", dormitory.getId(), dormitory.getName(), dormitory.getRoomNumber(), dormitory.getExt());
    }

    public void printRooms(LinkedList<Room<T> > queryAllRooms) {
        printTitle();
        queryAllRooms.forEach(this::printRoom);
    }
}
