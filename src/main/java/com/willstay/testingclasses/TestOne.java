package com.willstay.testingclasses;

import com.willstay.testingframework.annotations.After;
import com.willstay.testingframework.annotations.Before;
import com.willstay.testingframework.annotations.Test;

import static com.willstay.testingframework.Asserts.assertTrue;
import static com.willstay.testingframework.Asserts.assertEquals;
import static com.willstay.testingframework.Asserts.assertNotNull;

public class TestOne {
    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void assertTrueTest() {
        assertTrue(true);
    }

    @Test
    public void assertEqualsTest() {
        String string = new String();
        assertEquals(string, string);
    }

    @Test
    public void assertNotNullTest() {
        assertNotNull(null);
    }

    @After
    public void after() {
        System.out.println("after");
    }
}
