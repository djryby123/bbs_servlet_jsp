package com.djr.entity;

import java.sql.Timestamp;
import java.util.Date;

public class UserInfo {

    private int uId;
    private String uName;
    private String uPass;
    private String head;
    private Timestamp regTime;
    private String gender;

    public UserInfo(){}
    public UserInfo(int uId, String uName, String uPass, String head, Timestamp regTime, String gender) {
        this.uId = uId;
        this.uName = uName;
        this.uPass = uPass;
        this.head = head;
        this.regTime = regTime;
        this.gender = gender;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uID) {
        this.uId = uID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
