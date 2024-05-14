package service;

import db.Database;
import entity.Direction;
import entity.User;
import enums.Role;

import java.util.Objects;

import static util.Utils.*;


public class AdminService {

    private User currentUser;

    private Database database =Database.getInstance();



    public void service(User user) {

        currentUser = user;

        while (true){
            switch (getInt("""
                0 exit
                1 show direction
                2 add direction
                3 edit direction
                4 add doctor
                5 show users
                5 show doctors
                """)){
                case 0->{
                    System.out.println("see you soon "+currentUser.getName());
                    return;
                }
                case 1->{
                    for (Direction direction : database.directions) {
                        System.out.printf("id = %s  name =  '%s'  active = %s \n",direction.getId(),direction.getNaem(),direction.getActive());
                    }
                }
                case 2->{
                    Direction direction =new Direction();
                    System.out.println("enter direction name");
                    direction.setNaem(strScanner.nextLine());
                    System.out.println("enter amount");
                    direction.setPrice(scanner.nextDouble());
                    direction.setActive(true);

                    database.directions.add(direction);
                }
                case 3->{
                    System.out.println("enter id");
                    String id = strScanner.nextLine();

                    for (Direction direction : database.directions) {
                        if (direction.getId().equals(id)){
                            direction.setActive(!direction.getActive());
                            if (direction.getActive()){
                                System.out.println("direction activated!");
                            }else {
                                System.out.println("direction disabled!");
                            }
                            break;
                        }
                    }
                }
                case 4->{
                    System.out.println("enter direction id");
                    String id =strScanner.nextLine();
                    Direction direction=null;
                    for (Direction temp : database.directions) {
                        if (Objects.equals(temp.getId(),id)){
                            direction = temp;
                            break;
                        }
                    }
                    if (direction==null) {
                        System.out.println("direction not found!");
                        break;
                    }
                    showUsers();
                    System.out.println("enter username");
                    String username = strScanner.nextLine();
                    label:
                    {
                        for (User temp : database.users) {
                            if (Objects.equals(temp.getUsername(), username)) {
                                if (temp.getRole().equals(Role.USER)) {
                                    temp.setRole(Role.DOCTOR);
                                    direction.setDoctors(temp);
                                    break label;
                                }
                            }
                        }
                        System.out.println("user not found!");
                    }
                }
                case 5->{
                    showUsers();
                }
                case 6->{
                    for (Direction direction : database.directions) {
                        System.out.println(direction.getNaem());
                        if (direction.getDoctors().isEmpty()){
                            System.out.println("there is no any doctors in this direction");
                        }else {
                            for (User doctor : direction.getDoctors()) {
                                System.out.println(doctor);
                            }
                            System.out.println("\n\n");
                        }
                    }
                }
            }
        }
    }

    private void showUsers() {
        for (User temp : database.users) {
            if (temp.getRole().equals(Role.USER))
                System.out.println(temp);
        }
    }




    private static AdminService adminService;

    public static AdminService getInstance() {
        if (adminService == null)
            adminService = new AdminService();
        return adminService;
    }
}
