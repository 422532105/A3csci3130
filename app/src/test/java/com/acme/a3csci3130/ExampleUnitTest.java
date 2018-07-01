package com.acme.a3csci3130;

import com.google.firebase.database.DataSnapshot;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSetBusiness(){
        Data data = new Data();
        String number = "123456789";
        String name = "Anna";
        String busni = "Fisher";
        String addr = "Address";
        String pro = "provence";

        data.setAddress(addr);
        data.setBusinessNumber(number);
        data.setName(name);
        data.setPrimaryBusiness(busni);
        data.setProvince(pro);

        assertEquals(name, data.getName());
        assertEquals(number, data.getBusinessNumber());
        assertEquals(busni, data.getPrimaryBusiness());
        assertEquals(addr, data.getAddress());
        assertEquals(pro, data.getProvince());

    }


}