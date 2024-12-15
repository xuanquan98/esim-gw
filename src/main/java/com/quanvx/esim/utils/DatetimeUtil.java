package com.quanvx.esim.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DatetimeUtil {
    public static long getTimestamp() {
        // Current LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();

        // Convert LocalDateTime to milliseconds since epoch
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    }
}
