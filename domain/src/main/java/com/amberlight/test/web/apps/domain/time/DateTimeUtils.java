package com.amberlight.test.web.apps.domain.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * A set of utility functions implementing common patterns when working with {@link java.time}.
 */
public abstract class DateTimeUtils {

    /**
     * The default time-zone to use.
     */
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * Convert a date to a unix millisecond time.
     * @param date a date, which will be interpreted using the system default time-zone.
     * @return the number of milliseconds since the unix epoch.
     */
    public static long toMillis(LocalDateTime date) {
        return date.atZone(DEFAULT_ZONE).toInstant().toEpochMilli();
    }

    /**
     * Represent a unix timestamp as a LocalDateTime instance.
     * @param millis the number of milliseconds since the unix epoch.
     * @return a LocalDateTime (in the system default time-zone).
     */
    public static LocalDateTime fromMillis(long millis) {
        return Instant.ofEpochMilli(millis).atZone(DEFAULT_ZONE).toLocalDateTime();
    }
}
