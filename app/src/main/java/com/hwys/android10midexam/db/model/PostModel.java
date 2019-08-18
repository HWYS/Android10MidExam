package com.hwys.android10midexam.db.model;

public class PostModel {
    private int p_id, u_id;
    private String status, u_name;

    public PostModel(int p_id, int u_id,String u_name, String status ) {
        this.p_id = p_id;
        this.u_id = u_id;
        this.status = status;
        this.u_name = u_name;
    }

    public PostModel(int u_id, String status) {
        this.u_id = u_id;
        this.status = status;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
}
