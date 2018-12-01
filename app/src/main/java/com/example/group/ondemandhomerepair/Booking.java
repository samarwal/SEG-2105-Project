package com.example.group.ondemandhomerepair;

public class Booking {

    private User user;
    private ProviderProfile provider;
    private Service service;

    public Booking (User u, ProviderProfile p, Service s){
        this.user = u;
        this.provider = p;
        this.service = s;
    }

    public User getUser(){
        return this.user;
    }

    public ProviderProfile getProvider(){
        return this.provider;
    }

    public Service getService(){
        return this.service;
    }

    public void setUser(User u){
        this.user = u;
    }

    public void setProvider(ProviderProfile p){
        this.provider = p;
    }

    public void setService(Service s){
        this.service = s;
    }
}
