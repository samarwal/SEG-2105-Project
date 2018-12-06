package com.example.group.ondemandhomerepair;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {


    private String usersName;
    private String providerName;
    private String serviceName;
    private String bookingTimes;
    private String date;

    public Booking (String user, String provider, String service, String times){
        usersName = user;
        providerName = provider;
        serviceName = service;
        bookingTimes = times;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOb = new Date();

        date = formatter.format(dateOb);

    }

    public String getUser(){
        return this.usersName;
    }

    public String getProvider(){
        return this.providerName;
    }

    public String getService(){
        return this.serviceName;
    }

    public String getTimes(){
        return this.bookingTimes;
    }

    public String getDate(){
        return this.date;
    }

    public void setUser(String u){
        this.usersName = u;
    }

    public void setProvider(String p){
        this.providerName = p;
    }

    public void setService(String s){
        this.serviceName = s;
    }

    public void setTimes(String s){
        this.bookingTimes = s;
    }

    public void setDate(String s){
        this.date = s;
    }

    public String toString(){
        return ("Your name: " + usersName +  ", Provider: " + providerName + ", Service: " + providerName + ", Booked times: " + bookingTimes);
    }
}
