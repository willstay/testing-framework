package com.willstay.testingframework;

import com.willstay.testingframework.messenger.Messenger;

import java.util.List;

public class TestClasses {
    private final List<Class> classesToTest;
    private final Messenger messenger;

    public TestClasses(List<Class> classesToTest, Messenger messenger) {
        this.classesToTest = classesToTest;
        this.messenger = messenger;
    }

    public void doTests(){
        for (Class testClass : classesToTest){
            new TestClass(testClass,messenger).doTest();
        }
    }
}
