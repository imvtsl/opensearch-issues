package org.vatsal.learn.issue14519;

public class TestBoolean {
    public static void main(String[] args) {
        Boolean found = null;
        try {
            testMethod(found);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testMethod(boolean f) {
        System.out.println(f);
    }
}
