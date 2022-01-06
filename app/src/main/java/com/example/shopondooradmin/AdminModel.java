package com.example.shopondooradmin;

public class AdminModel {
    String Name;
    String email;
    String password;
    String openStatus="Closed";

    public AdminModel() {
    }

    public AdminModel(String name, String email, String password, String openStatus) {
        Name = name;
        this.email = email;
        this.password = password;
        this.openStatus = openStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }
}
