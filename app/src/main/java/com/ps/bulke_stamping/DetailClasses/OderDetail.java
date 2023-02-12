package com.ps.bulke_stamping.DetailClasses;

public class OderDetail {

    public OderDetail() {
    }

    public OderDetail(String grahakuid, String estamparticle, String etsampno, String estampprice, String datetime, String oderno) {
        this.grahakuid = grahakuid;
        this.estamparticle = estamparticle;
        this.etsampno = etsampno;
        this.estampprice = estampprice;
        this.datetime = datetime;
        this.oderno=oderno;
    }

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

    public void setOderno(String oderno) {
        this.oderno = oderno;
    }
    public String getOderno() {
        return oderno;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    private String grahakuid,estamparticle, etsampno, estampprice,datetime,oderno;
}
