package com.example.group.ondemandhomerepair;

public class Timeslot {
    public int year;
    public int month;
    public int day;
    public int sHour;
    public int sMinute;
    public int eHour;
    public int eMinute;

    public Timeslot(int sHour, int sMinute, int eHour, int eMinute) {
        this.sHour =sHour;
        this.sMinute =sMinute;
        this.eHour =eHour;
        this.eMinute =eMinute;
    }
    public void setYear(int year){
     this.year = year;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setDay(int day){
        this.day = day;
    }
    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getSHour(){
        return sHour;
    }
    public int getSMinute(){
        return sMinute;
    }
    public int getEHour(){
        return eHour;
    }
    public int getEMinute(){
        return eMinute;
    }
    public String toString(){
        return (String.valueOf(day) + "//" + String.valueOf(month) + "//" + String.valueOf(year) + " Start time: " + String.valueOf(sHour)+ ":" + String.valueOf(sMinute)
                + " End time: " + String.valueOf(sHour)+ ":" + String.valueOf(sMinute));
    }
}
