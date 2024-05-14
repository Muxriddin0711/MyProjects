package entity;

import enums.Status;

import java.util.UUID;

public class Consultation {
    private String id = UUID.randomUUID().toString();
    private String direction;
    private User patient;
    private User doctor;
    private Double price;
    private Status status;

    public Consultation(){

    }
    public Consultation(String direction, User patient, User doctor, Status status) {
        this.direction = direction;
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id='" + id + '\'' +
                ", direction='" + direction + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
