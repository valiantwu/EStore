package org.woo.framework.util;

public class EnvivironmentUtil {
    private static String classPath;

    public static String getClassPath() {
        return classPath;
    }

    public static void setClassPath(String classPath) {
        EnvivironmentUtil.classPath = classPath;
    }
}
