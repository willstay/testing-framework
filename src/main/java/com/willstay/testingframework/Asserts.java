package com.willstay.testingframework;

import com.willstay.testingframework.messenger.SendToConsole;

public class Asserts {
    private Asserts() {
    }

    public static void assertTrue(boolean condition) {
        SendToConsole sendToConsole = new SendToConsole();
        String msg = "assertTrue";
        if (condition) {
            sendToConsole.sendPassed(msg);
        } else {
            sendToConsole.sendFailed(msg);
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        SendToConsole sendToConsole = new SendToConsole();
        String msg = "assertEquals";
        if (expected.equals(actual)) {
            sendToConsole.sendPassed(msg);
        } else {
            sendToConsole.sendFailed(msg);
        }
    }

    public static void assertNotNull(Object o) {
        SendToConsole sendToConsole = new SendToConsole();
        String msg = "assertNotNull";
        if (o != null) {
            sendToConsole.sendPassed(msg);
        } else {
            sendToConsole.sendFailed(msg);
        }
    }
}
