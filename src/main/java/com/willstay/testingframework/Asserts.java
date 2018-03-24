package com.willstay.testingframework;

public class Asserts {
    private Asserts() {
    }

    public static void assertTrue(boolean condition) {
        String msg = "assertTrue";
        if (condition) {
            System.out.println(msg + " test passed");
        } else {
            System.out.println(msg + " test failed");
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        String msg = "assertEquals";
        if (expected.equals(actual)) {
            System.out.println(msg + " test passed");
        } else {
            System.out.println(msg + " test failed");
        }
    }

    public static void assertNotNull(Object o) {
        String msg = "assertNotNull";
        if (o != null) {
            System.out.println(msg + " test passed");
        } else {
            System.out.println(msg + " test failed");
        }
    }
}
