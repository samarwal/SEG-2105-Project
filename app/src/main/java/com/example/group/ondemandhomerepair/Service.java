package com.example.group.ondemandhomerepair;

public class Service {

    private String name;
    private String hourlyRate;

    public Service(String n, String hR){
        this.name = n;
        this.hourlyRate = hR;
    }

    public Service(){
    }

    public String getServiceName(){
        return this.name;
    }

    public String getHourlyRate(){
        return this.hourlyRate;
    }

    public void setServiceName(String n){
        this.name = n;
    }

    public void setHourlyRate(String hR){
        this.hourlyRate = hR;
    }

    public String toString(){
        return "Service: "+this.name+" | Hourly Rate: "+this.hourlyRate;
    }
}
