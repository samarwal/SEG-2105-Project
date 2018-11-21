package com.example.group.ondemandhomerepair;

import org.junit.Test;

import java.security.Provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnitTestTwo {

    private ProviderProfile provTest = new ProviderProfile("545 King Edward", 1234567890,
            "Test Inc.","Good Services Here!", "Standard License");


    @Test
    public void isInitialized(){
        assertNotNull(provTest);
    }

    @Test
    public void correctToString(){
        assertEquals("PhoneNumber: "+provTest.phonenumber+" | Address: "+provTest.address+
                " | Description: "+provTest.profiledescription+ "| Company: "+provTest.company, provTest.toString());
    }
}
