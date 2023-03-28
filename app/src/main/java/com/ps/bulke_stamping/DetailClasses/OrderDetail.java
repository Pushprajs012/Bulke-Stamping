package com.ps.bulke_stamping.DetailClasses;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private String grahakuid;
    private String estamparticle;
    private String oderno;
    private String etsampno;
    private String estampprice;
    private String datetime;

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    private String partyname;

    public int setStatus(int status) {
        this.status = status;
        return status;
    }

    private int status;

    public String getGrahakuid() {
        return grahakuid;
    }

    public void setGrahakuid(String grahakuid) {
        this.grahakuid = grahakuid;
    }

    public String getEstamparticle() {
        return estamparticle;
    }

    public void setEstamparticle(String estamparticle) {
        this.estamparticle = estamparticle;
    }

    public String getOderno() {
        return oderno;
    }

    public void setOderno(String oderno) {
        this.oderno = oderno;
    }

    public String getEtsampno() {
        return etsampno;
    }

    public void setEtsampno(String etsampno) {
        this.etsampno = etsampno;
    }

    public String getEstampprice() {
        return estampprice;
    }

    public void setEstampprice(String estampprice) {
        this.estampprice = estampprice;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getStatus() {
        return status;
    }



    public OrderDetail(String grahakuid, String estamparticle, String oderno, String etsampno, String estampprice, String datetime, String partyname, int status) {
        this.grahakuid = grahakuid;
        this.estamparticle = estamparticle;
        this.oderno = oderno;
        this.etsampno = etsampno;
        this.estampprice = estampprice;
        this.datetime = datetime;
        this.partyname=partyname;
        this.status = status;
    }

    public OrderDetail() {
    }
}
