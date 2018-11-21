package com.example.group.ondemandhomerepair;


public class DayList {
    private String mon;
    private String tues;
    private String wed;
    private String thurs;
    private String fri;
    private String sat;
    private String sun;

    public DayList(String mon,String tues,String wed,String thurs,String fri,String sat,String sun){
        this.mon = mon;
        this.tues = tues;
        this.wed = wed;
        this.thurs =thurs;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    public String getMon(){
        return this.mon;
    }

    public String getTues(){
        return this.tues;
    }

    public String getWed(){
        return this.wed;
    }

    public String getThurs(){
        return this.thurs;
    }

    public String getFri(){
        return this.fri;
    }

    public String getSat(){
        return this.sat;
    }

    public String getSun(){
        return this.sun;
    }


}
