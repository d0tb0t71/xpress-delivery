package com.example.xpressdelivery;

public class ParcelModel {

    String pID;
    String rName;
    String rEmail;
    String rMobile;
    String rAdd;
    String sName;
    String pType;
    String issueDate;
    String issued;
    String wayToWH ;
    String inWH ;
    String wayToR;
    String delivered;
    String weight;

    public ParcelModel() {
    }

    public ParcelModel(String pID, String rName, String rEmail, String rMobile, String rAdd, String sName, String pType, String issueDate, String issued, String wayToWH, String inWH, String wayToR, String delivered, String weight) {
        this.pID = pID;
        this.rName = rName;
        this.rEmail = rEmail;
        this.rMobile = rMobile;
        this.rAdd = rAdd;
        this.sName = sName;
        this.pType = pType;
        this.issueDate = issueDate;
        this.issued = issued;
        this.wayToWH = wayToWH;
        this.inWH = inWH;
        this.wayToR = wayToR;
        this.delivered = delivered;
        this.weight = weight;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrEmail() {
        return rEmail;
    }

    public void setrEmail(String rEmail) {
        this.rEmail = rEmail;
    }

    public String getrMobile() {
        return rMobile;
    }

    public void setrMobile(String rMobile) {
        this.rMobile = rMobile;
    }

    public String getrAdd() {
        return rAdd;
    }

    public void setrAdd(String rAdd) {
        this.rAdd = rAdd;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getWayToWH() {
        return wayToWH;
    }

    public void setWayToWH(String wayToWH) {
        this.wayToWH = wayToWH;
    }

    public String getInWH() {
        return inWH;
    }

    public void setInWH(String inWH) {
        this.inWH = inWH;
    }

    public String getWayToR() {
        return wayToR;
    }

    public void setWayToR(String wayToR) {
        this.wayToR = wayToR;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
