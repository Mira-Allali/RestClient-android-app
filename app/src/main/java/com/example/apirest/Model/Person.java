package com.example.apirest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("f_name")
    @Expose
    private String f_name;

    @SerializedName("l_name")
    @Expose
    private String l_name;

    public Person(){

    }

    public Person (int id, String n, String m) {
        this.id = id;
        this.f_name = n;
        this.l_name = m;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return f_name;
    }

    public void setNombres(String name) {
        this.f_name = name;
    }

    public String getApellidos() {
        return l_name;
    }

    public void setApellidos(String famname) {
        this.l_name = famname;
    }
}
