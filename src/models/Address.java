package models;

public class Address {
    private int id;
    private String streetName;
    private String streetNumber;
    private String floor;
    private String city;
    private String postalCode;

    public Address(int id, String streetName, String streetNumber, String floor, String city, String postalCode) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.floor = floor;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Address(String streetName, String streetNumber, String floor, String city, String postalCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.floor = floor;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return this.streetName + " " + this.streetNumber + ",<br>Floor: " + this.floor + ", " + this.getPostalCode() + " " + this.getCity();
    }
}
