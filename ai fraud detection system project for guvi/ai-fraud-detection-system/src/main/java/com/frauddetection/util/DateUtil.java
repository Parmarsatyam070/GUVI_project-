package com.frauddetection.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String format(Timestamp ts) {
        if (ts == null) return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ts.getTime()));
    }
}
