package com.willstay.testingframework.messenger;

public interface Messenger {
    void sendPassed(String msg);
    void sendFailed(String msg);
}
