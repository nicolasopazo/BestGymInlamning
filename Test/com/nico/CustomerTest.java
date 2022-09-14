package com.nico;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {


    Customer testCustomer = new Customer("Grodan Boll", "7603021234", "2022-07-01", 0);

    @Test
    void getName() {
        assertTrue(testCustomer.getName().equals("Grodan Boll"));
        assertFalse(testCustomer.getName().equals("Pingo Lingo"));
    }

    @Test
    void isMember() {
        assertEquals(0, testCustomer.getSincePaidDate());
    }

}

