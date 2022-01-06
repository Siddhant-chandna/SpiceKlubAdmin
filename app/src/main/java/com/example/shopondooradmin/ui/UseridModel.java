package com.example.shopondooradmin.ui;

import java.io.Serializable;

public class UseridModel implements Serializable {
    String Uid;

    public UseridModel() {
    }

    public UseridModel(String uid) {
        Uid = uid;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
