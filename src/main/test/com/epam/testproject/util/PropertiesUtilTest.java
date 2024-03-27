package com.epam.testproject.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertiesUtilTest {
    @Test
    public void checkProperties() {
        //given
        //when
        //then

        assertEquals(1000, PropertiesUtil.FILE_SIZE_IN_RECORD, 0);
        assertEquals(1.2, PropertiesUtil.MIN_RATE, 0);
        assertEquals(1.5, PropertiesUtil.MAX_RATE, 0);
        assertEquals(4, PropertiesUtil.NORMAL_DEEP_MANAGER_LIMIT, 0);
        assertEquals("records.csv", PropertiesUtil.FILE_NAME);

    }

}
