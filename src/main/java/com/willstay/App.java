package com.willstay;

import com.willstay.testingclasses.TestOne;
import com.willstay.testingframework.Testing;

public class App {
    public static void main(String[] args) {
        new Testing(TestOne.class).doTests();
    }
}
