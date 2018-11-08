package com.example.group.ondemandhomerepair;

public class Service {

    private String name;
    private int hourlyRate;

    public Service(String n, int hR){
        this.name = n;
        this.hourlyRate = hR;
    }

    public String getServiceName(){
        return this.name;
    }

    public int getHourlyRate(){
        return this.hourlyRate;
    }

    public void setServiceName(String n){
        this.name = n;
    }

    public void setHourlyRate(int hR){
        this.hourlyRate = hR;
    }

    public String toString(){
        return "Service: "+this.name+"| Hourly Rate: "+this.hourlyRate;
    }
}
