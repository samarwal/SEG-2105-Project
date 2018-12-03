package com.example.group.ondemandhomerepair;

public class Timeslot {
    public String date;
    public int sHour;
    public int sMinute;
    public int eHour;
    public int eMinute;

    public Timeslot(String date, int sHour, int sMinute, int eHour, int eMinute) {
        this.date =date;
        this.sHour =sHour;
        this.sMinute =sMinute;
        this.eHour =eHour;
        this.eMinute =eMinute;
    }
}
