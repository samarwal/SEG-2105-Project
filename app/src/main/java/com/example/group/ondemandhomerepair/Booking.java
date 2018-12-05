package com.example.group.ondemandhomerepair;

public class Booking {


    private String usersName;
    private String providersName;
    private String bookingTimes;

    private User user;
    private ProviderProfile provider;
    private Service service;

    public Booking (String user, String provider, String times){
        usersName = user;
        providersName = provider;
        bookingTimes = times;

    }

    public String getUser(){
        return this.usersName;
    }

    public String getProvider(){
        return this.providersName;
    }

    public String getTimes(){
        return this.bookingTimes;
    }

    public void setUser(String u){
        this.usersName = u;
    }

    public void setProvider(String p){
        this.providersName = p;
    }

    public void setTimes(String s){
        this.bookingTimes = s;
    }

    public String toString(){
        return ("Your name: " + usersName +  ", Provider: " + providersName + " Booked times: " + bookingTimes);
    }
}
