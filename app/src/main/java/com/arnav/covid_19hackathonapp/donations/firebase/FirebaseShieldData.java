package com.arnav.covid_19hackathonapp.donations.firebase;

public class FirebaseShieldData {
    String address, apartment, city, state, zipcode, amount, comments;
    //add data to database
    public FirebaseShieldData(String address, String apartment, String city, String state, String zipcode, String amount, String comments) {
        this.address = address;
        this.apartment = apartment;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.amount = amount;
        this.comments = comments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
