package com.willstay.testingframework;

import com.willstay.testingframework.annotations.After;
import com.willstay.testingframework.annotations.Before;
import com.willstay.testingframework.annotations.Test;
import com.willstay.testingframework.exception.NoTests;
import com.willstay.testingframework.exception.TestFailedException;
import com.willstay.testingframework.exception.TooManyAnnotations;
import com.willstay.testingframework.messenger.Messenger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Testing {
    private final Class testClass;
    private List<Method> testMethodList;
    private Method beforeMethod;
    private Method afterMethod;
    private Object object;
    private final Messenger messenger;

    public Testing(Class testClass, Messenger messenger) {
        this.testClass = testClass;
        this.messenger = messenger;
    }

    public void doTests() {
        parseClass();
        doMethods();
    }

    private void parseClass() {
        try {
            findTests();
        } catch (NoTests noTests) {
            noTests.printStackTrace();
        }
        try {
            beforeMethod = findSingularAnnotation(Before.class);
            afterMethod = findSingularAnnotation(After.class);
        } catch (TooManyAnnotations tooManyAnnotations) {
            tooManyAnnotations.printStackTrace();
        }
    }

    private void doMethods() {
        for (Method method : testMethodList) {
            createInstance();
            try {
                if (beforeMethod != null) {
                    callMethod(beforeMethod);
                }

                callMethod(method);
                messenger.sendPassed(method.getName());

                if (afterMethod != null) {
                    callMethod(afterMethod);
                }
            } catch (Throwable e) {
                if (e instanceof TestFailedException) {
                    messenger.sendFailed(method.getName());
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    private void callMethod(Method method) throws Throwable {
        method.setAccessible(true);
        try {
            method.invoke(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private void createInstance() {
        try {
            object = testClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Method> findAnnotationMethods(Class annotationClass) {
        List<Method> methodList = new ArrayList<>();
        for (Method method : testClass.getMethods()) {
            if (method.getAnnotation(annotationClass) != null) {
                methodList.add(method);
            }
        }
        return methodList;
    }

    private void findTests() throws NoTests {
        testMethodList = findAnnotationMethods(Test.class);
        if (testMethodList.isEmpty()) {
            throw new NoTests("There are no tests");
        }
    }

    private Method findSingularAnnotation(Class annotationClass) throws TooManyAnnotations {
        List<Method> methodList = findAnnotationMethods(annotationClass);
        if (methodList.size() > 1) {
            throw new TooManyAnnotations(annotationClass.toString() + " can be one time");
        }
        if (methodList.isEmpty()) {
            return null;
        }
        return methodList.get(0);
    }
}
