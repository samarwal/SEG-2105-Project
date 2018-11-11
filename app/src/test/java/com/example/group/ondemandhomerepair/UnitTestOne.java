package com.example.group.ondemandhomerepair;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTestOne {

    private Service servTest = new Service("Test",10);


    @Test
    public void isInitialized(){
        assertNotNull(servTest);
    }

    @Test
    public void correctServiceName() {
        assertEquals("Test", servTest.getServiceName());
    }

    @Test
    public void correctHourlyRate() {
        assertEquals(10, servTest.getHourlyRate());
    }

    @Test
    public void canSetServiceName(){
        servTest.setServiceName("Test_Good");
        assertEquals("Test_Good", servTest.getServiceName());
    }

    @Test
    public void canSetHourlyRate(){
        servTest.setHourlyRate(150);
        assertEquals(150, servTest.getHourlyRate());
    }

    @Test
    public void correctToString(){
        assertEquals("Service: "+servTest.getServiceName()+" | Hourly Rate: "+servTest.getHourlyRate(), servTest.toString());
    }


}
