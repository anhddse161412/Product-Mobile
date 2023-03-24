package com.mquan.productmarket.model;

import java.io.Serializable;

public class CustomerOrderForm implements Serializable {
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String city;

    public CustomerOrderForm(String fullName, String phone, String email, String address, String city) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
