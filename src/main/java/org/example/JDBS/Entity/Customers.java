package org.example.JDBS.Entity;


import java.util.Date;
import java.util.Objects;

public class Customers {
    private long id;
    private String firstName;
    private String email;
    private String city;
    private Date registrationDate;

    public Customers(String firstName, String email, String city, Date registrationDate) {
        this.registrationDate = registrationDate;
        this.firstName = firstName;
        this.email = email;
        this.city = city;
    }

    public Customers(long id, String firstName, String email, String city, Date registrationDate) {
        this.city = city;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.registrationDate = registrationDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return id == customers.id && Objects.equals(firstName, customers.firstName) && Objects.equals(email, customers.email) && Objects.equals(city, customers.city) && Objects.equals(registrationDate, customers.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, email, city, registrationDate);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "city='" + city + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
