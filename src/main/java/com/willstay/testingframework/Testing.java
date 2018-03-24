package com.willstay.testingframework;

import com.willstay.testingframework.annotations.After;
import com.willstay.testingframework.annotations.Before;
import com.willstay.testingframework.annotations.Test;
import com.willstay.testingframework.exception.NoTests;
import com.willstay.testingframework.exception.TooManyAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Testing {
    private final Class testClass;
    private List<Method> testMethodList;
    private List<Method> beforeMethod;
    private List<Method> afterMethod;
    private Object object;

    public Testing(Class testClass) {
        this.testClass = testClass;
    }

    public void doTests() {
        parseClass();
        createInstance();
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
            if (!beforeMethod.isEmpty()) {
                callMethod(beforeMethod.get(0));
            }

            callMethod(method);

            if (!afterMethod.isEmpty()) {
                callMethod(afterMethod.get(0));
            }
        }
    }

    private void callMethod(Method method) {
        method.setAccessible(true);
        try {
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void createInstance() {
        try {
            object = testClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
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

    private List<Method> findSingularAnnotation(Class annotationClass) throws TooManyAnnotations {
        List<Method> methodList = findAnnotationMethods(annotationClass);
        if (methodList.size() > 1) {
            throw new TooManyAnnotations(annotationClass.toString() + " can be one time");
        }
        return methodList;
    }
}
