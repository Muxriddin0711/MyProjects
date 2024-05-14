package db;

import entity.Consultation;
import entity.Direction;
import entity.User;
import enums.Role;

import javax.xml.crypto.Data;
import java.util.*;

public class Database {

    public Set<User> users=new HashSet<>();
    public List<Direction> directions=new ArrayList<>();
    public List<Consultation> consultations=new ArrayList<>();


    private static Database database;
    public static Database getInstance(){

        if (database==null) {
            database = new Database();
            database.users.add(new User("admin","admin","admin", Role.ADMIN,0.0));
            database.users.add(new User("u1","u1","u1", Role.USER,100000.0));
            database.users.add(new User("u2","u2","u2", Role.USER,100000.0));
            database.users.add(new User("u3","u3","u3", Role.USER,100000.0));
            database.users.add(new User("u4","u4","u4", Role.USER,100000.0));
        }
        return database;
    }
}
