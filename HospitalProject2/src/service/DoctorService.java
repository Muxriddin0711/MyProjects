package service;

import db.Database;
import entity.Consultation;
import entity.User;
import enums.Status;

import static util.Utils.*;

public class DoctorService {

    private User currentUser;
    private Database database = Database.getInstance();

    public void service(User user) {
        currentUser = user;
        while (true) {
            switch (getInt("""
                0 exit
                1 show orders
                2 finish consultation
                3 show history
                4 show balance              
                """)) {
                case 0 -> {
                    System.out.println("see you soon " + currentUser.getName());
                    return;
                }
                case 1 -> {
                    showConsultations(false);
                }
                case 2 -> {
                    showConsultations(false);
                    System.out.println("enter id");
                    String id = strScanner.nextLine();
                    for (Consultation consultation : database.consultations) {
                        if (consultation.getDoctor().equals(currentUser) && consultation.getId().equals(id)) {
                            User patient = consultation.getPatient();
                            consultation.setStatus(Status.FINISHED);
                            currentUser.setBalance(currentUser.getBalance() + consultation.getPrice());
                            patient.setBalance(patient.getBalance() - consultation.getPrice());
                            break;
                        }
                    }

                }
                case 3 -> {
                    showConsultations(true);
                }
                case 4 -> {
                    System.out.printf("you have %s in your balance\n", currentUser.getBalance());
                }
            }
        }
    }

    private void showConsultations(boolean isAll) {
        for (Consultation consultation : database.consultations) {
            if (consultation.getDoctor().equals(currentUser)) {
                if (isAll)
                    System.out.printf("id = %s, user = %s,  status = %s\n", consultation.getId(), consultation.getPatient().getName(), consultation.getStatus().name());
                else if (consultation.getStatus().equals(Status.WAITING))
                    System.out.printf("id = %s, user = %s,  status = %s\n", consultation.getId(), consultation.getPatient().getName(), consultation.getStatus().name());
            }
        }
    }



    private static DoctorService doctorService;

    public static DoctorService getInstance() {
        if (doctorService == null)
            doctorService = new DoctorService();
        return doctorService;
    }
}
