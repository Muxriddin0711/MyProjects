package service;

import db.Database;
import entity.Consultation;
import entity.Direction;
import entity.User;
import enums.Status;

import javax.xml.crypto.Data;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static util.Utils.*;

public class UserService {

    private User currentUser;
    Database database = Database.getInstance();

    public void service(User user) {
        currentUser = user;
        while (true) {
            switch (getInt("""
                0 exit
                1 show profession
                2 consultation
                3 upcoming consultation
                4 cancel consultation
                5 history
                """)) {
                case 0 -> {
                    System.out.println("see you soon " + currentUser.getName());
                    return;
                }
                case 1 -> {
                    showDirection();
                }
                case 2 -> {
                    boolean success = false;
                    Optional<Direction> optionalDirection = getDirection();
                    if (optionalDirection.isEmpty()) {
                        System.out.println("direction not foun!");
                        break;
                    }
                    Direction direction = optionalDirection.get();
                    Set<User> doctors = direction.getDoctors();
                    for (User doctor : doctors) {
                        System.out.println(doctor);
                    }
                    System.out.println("enter doctor id");
                    String id = strScanner.nextLine();
                    for (User doctor : doctors) {
                        if (doctor.getId().equals(id)) {
                            Consultation consultation = new Consultation();
                            consultation.setDoctor(doctor);
                            consultation.setPatient(currentUser);
                            consultation.setDirection(direction.getNaem());
                            consultation.setStatus(Status.WAITING);
                            consultation.setPrice(direction.getPrice());
                            database.consultations.add(consultation);
                            success = true;
                            break;
                        }
                    }
                    System.out.println(success ? "consultation created!" : "something went wrong!");
                }
                case 3 -> {
                    consultationBoard(false);

                }
                case 4 -> {
                    consultationBoard(false);
                    System.out.println("enter id");
                    String id = strScanner.nextLine();
                    for (Consultation consultation : database.consultations) {
                        if (consultation.getId().equals(id)) {
                            consultation.setStatus(Status.CANCELLED);
                            break;
                        }
                    }
                }
                case 5 -> {
                    consultationBoard(true);
                }
            }
        }
    }

    private void consultationBoard(boolean isAll) {
        for (Consultation consultation : database.consultations)
            if (consultation.getPatient().equals(currentUser))
                if (isAll)
                    System.out.printf("id = %s, doctor = %s, direction = %s, status = %s\n", consultation.getId(), consultation.getDoctor().getName(), consultation.getDirection(), consultation.getStatus().name());
                else if (consultation.getStatus().equals(Status.WAITING))
                    System.out.printf("id = %s, doctor = %s, direction = %s, status = %s\n", consultation.getId(), consultation.getDoctor().getName(), consultation.getDirection(), consultation.getStatus().name());
    }


    private Optional<Direction> getDirection() {
        showDirection();
        System.out.println("enter direction id");
        String id = strScanner.nextLine();
        for (Direction direction : database.directions) {
            if (direction.getActive() && Objects.equals(direction.getId(), id)) {
                return Optional.of(direction);
            }
        }
        return Optional.empty();
    }

    private void showDirection() {
        for (Direction direction : database.directions) {
            if (direction.getActive())
                System.out.println(direction);
        }
    }




    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null)
            userService = new UserService();
        return userService;
    }
}
