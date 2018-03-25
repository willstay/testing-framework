package com.willstay.testingframework;

import com.willstay.testingframework.exception.TestFailedException;

public class Asserts {
    private Asserts() {
    }

    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new TestFailedException();
        }
    }

    public static void assertEquals(Object actual, Object expected) {
        if (!actual.equals(expected)) {
            throw new TestFailedException();
        }
    }

    public static void assertNotNull(Object o) {
        if (o == null) {
            throw new TestFailedException();
        }
    }
}
