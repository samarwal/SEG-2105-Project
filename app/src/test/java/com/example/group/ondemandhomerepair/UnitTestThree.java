package com.example.group.ondemandhomerepair;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnitTestThree {

    private Booking testBooking = new Booking("testUser", "testProvider",
            "testService", "Start time: 1:20 End time: 1:20");

    private Timeslot testTimeslot = new Timeslot(14, 30, 20, 0);

    @Test
    public void isBookingInitialized(){
        assertNotNull(testBooking);
    }

    @Test
    public void correctUserName() {
        assertEquals("testUser", testBooking.getUser());
    }

    @Test
    public void correctProviderName() {
        assertEquals("testProvider", testBooking.getProvider());
    }

    @Test
    public void correctServiceName() {
        assertEquals("testService", testBooking.getService());
    }

    @Test
    public void correctTime() {
        assertEquals("Start time: 1:20 End time: 1:20", testBooking.getTimes());
    }

    @Test
    public void correctDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOb = new Date();

        String date = formatter.format(dateOb);

        assertEquals(date, testBooking.getDate());
    }

    @Test
    public void isTimeslotInitialized(){
        assertNotNull(testTimeslot);
    }

    @Test
    public void correctStartHour() {
        assertEquals(14, testTimeslot.getSHour());
    }

    @Test
    public void correctStartMinute() {
        assertEquals(30, testTimeslot.getSMinute());
    }

    @Test
    public void correctEndHour() {
        assertEquals(20, testTimeslot.getEHour());
    }

    @Test
    public void correctEndMinute() {
        assertEquals(0, testTimeslot.getEMinute());
    }

}
