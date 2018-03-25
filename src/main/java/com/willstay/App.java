package com.willstay;

import com.willstay.testingclasses.TestOne;
import com.willstay.testingframework.Testing;
import com.willstay.testingframework.messenger.SendToConsole;

public class App {
    public static void main(String[] args) {
        new Testing(TestOne.class, new SendToConsole()).doTests();
    }
}
