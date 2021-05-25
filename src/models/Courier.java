package models;

import models.enums.CourierStatus;

public class Courier {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private CourierStatus status;

    public Courier(int id, String firstName, String lastName, String phoneNo, CourierStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    public Courier(String firstName, String lastName, String phoneNo, CourierStatus status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }

    public String[] toStringArray() {
        return new String[] { String.valueOf(id), firstName, lastName, phoneNo, status.toString()};
    }
}
