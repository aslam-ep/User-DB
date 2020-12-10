package com.hector.userdb.DataModels;

import java.io.Serializable;

public class UserData implements Serializable {
    String name, number, address;
    byte[] image;
    int id;

    public UserData(){}

    public UserData(int id, String name, String number, String address, byte[] image) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public byte[] getImage() {
        return image;
    }
}
