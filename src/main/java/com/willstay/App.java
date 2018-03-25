package com.willstay;

import com.willstay.testingclasses.TestOne;
import com.willstay.testingframework.TestClasses;
import com.willstay.testingframework.messenger.SendToConsole;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Class> classList = new ArrayList<>();
        classList.add(TestOne.class);
        new TestClasses(classList, new SendToConsole()).doTests();
    }
}
