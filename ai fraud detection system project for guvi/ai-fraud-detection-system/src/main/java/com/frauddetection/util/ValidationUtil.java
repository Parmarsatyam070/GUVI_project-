/*package com.frauddetection.util;

public class ValidationUtil {

    public static boolean isEmpty(String s) {
        return (s == null || s.trim().isEmpty());
    }

    public static boolean isNumber(String s) {
        if (isEmpty(s)) return false;
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
*/

package com.frauddetection.util;

public class ValidationUtil {

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNumber(String s) {
        if (isEmpty(s)) return false;
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
