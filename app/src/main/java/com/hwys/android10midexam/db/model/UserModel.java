package com.hwys.android10midexam.db.model;

public class UserModel {
    private int u_id;
    private String u_name, password;

    public UserModel(int u_id, String u_name, String password) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.password = password;
    }

    public UserModel(String u_name, String password) {
        this.u_name = u_name;
        this.password = password;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
