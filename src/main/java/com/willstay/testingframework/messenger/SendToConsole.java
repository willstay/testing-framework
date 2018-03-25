package com.willstay.testingframework.messenger;

public class SendToConsole implements Messenger {
    @Override
    public void sendPassed(String msg) {
        System.out.println(msg + " test passed");
    }

    @Override
    public void sendFailed(String msg) {
        System.out.println(msg + " test failed");
    }
}
