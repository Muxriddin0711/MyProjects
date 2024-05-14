package entity;

import java.util.*;

public class Direction {
    private final String id = UUID.randomUUID().toString();
    private String  naem;
    private Boolean isActive;
    private Double price;
    Set<User> doctors = new HashSet<>();

    public String  getId() {
        return id;
    }


    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<User> getDoctors() {
        return doctors;
    }

    public void setDoctors(User doctors) {
        this.doctors.add(doctors);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDoctors(Set<User> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "id='" + id + '\'' +
                ", naem='" + naem + '\'' +
                ", isActive=" + isActive +
                ", price=" + price +
                ", doctors=" + doctors +
                '}';
    }
}
