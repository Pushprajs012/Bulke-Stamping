package com.ps.bulke_stamping.DetailClasses;

public class UserDetail {


    public UserDetail(String name, String phoneno, String zila, String tahsil, String address, String pin, String usertype) {
        this.name = name;
        this.phoneno = phoneno;
        this.zila = zila;
        this.tahsil = tahsil;
        this.address = address;
        this.pin = pin;
        this.usertype = usertype;
    }

    public UserDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getZila() {
        return zila;
    }

    public void setZila(String zila) {
        this.zila = zila;
    }

    public String getTahsil() {
        return tahsil;
    }

    public void setTahsil(String tahsil) {
        this.tahsil = tahsil;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    String name,phoneno,zila,tahsil,address,pin,usertype;


}