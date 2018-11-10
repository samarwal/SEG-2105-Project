package com.example.group.ondemandhomerepair;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnitTestTwo {

    private User userTest = new User("Test","roleTypeTest");

    @Test
    public void isInitialized(){
        assertNotNull(userTest);
    }

}
