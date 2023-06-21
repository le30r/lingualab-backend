package xyz.le30r.lingualab.generic.utils;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateTimeUtils {
    public static Instant toInstant(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ?  null : offsetDateTime.toInstant();
    }

    public static OffsetDateTime toOffsetDateTime(Instant instant) {
        return instant == null ? null : instant.atOffset(ZoneOffset.UTC);
    }
}
