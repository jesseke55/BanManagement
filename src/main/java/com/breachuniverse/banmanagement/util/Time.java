package com.breachuniverse.banmanagement.util;

import java.sql.Timestamp;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class Time {

    private Time() { }

    public static long toLong(Timestamp timestamp) {
        return timestamp.getTime();
    }

    public static boolean isExpired(Timestamp oneTime, Timestamp twoTime) {
        return Time.toLong(oneTime) >= Time.toLong(twoTime);
    }
}
