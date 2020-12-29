package test;

import com.management.dao.ext.Dormitory;

import java.io.*;
import java.util.LinkedList;

public class Test1 implements Serializable {
    public LinkedList<Dormitory> dormitories = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Test1 t = new Test1();
        Dormitory d = new Dormitory();
        Dormitory d2 = new Dormitory();
        d.setName("hhh");
        d2.setName("ggg");
        d.setExt("hhh");
        d2.setExt("ggg");
        t.dormitories.add(d);
        t.dormitories.add(d2);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("" +
                "test")));
        oos.writeObject(t);
    }
}
