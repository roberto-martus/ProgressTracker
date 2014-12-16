package com.cleancoder.base.common.util;

/**
 * Created by Leonid on 16.12.2014.
 */
public class ArrayUtils {

    public static boolean arrayContains(int[] array, int elem) {
        for (int each : array) {
            if (each == elem) {
                return true;
            }
        }
        return false;
    }

}
