import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class DateParser {

    public static String parseDate(String s) {
        try {
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(s));
        } catch (DateTimeParseException dtpe) {
            try {
                return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(s));
            } catch (DateTimeParseException dtpe2) {
                try {
                    return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DateTimeFormatter.ISO_DATE_TIME.parse(s));
                } catch (Exception dtp3) {
                    try {
                        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DateTimeFormatter.ISO_INSTANT.parse(s));
                    } catch (DateTimeParseException dtpe4) {
                        try {
                            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DateTimeFormatter.RFC_1123_DATE_TIME.parse(s));
                        } catch (DateTimeParseException dtpe5) {
                            try {
                                //No standard time format with offset found. Is probably ISO_LOCAL_DATE_TIME. Assume Offset like local offset
                                String s2 = s + OffsetDateTime.now().getOffset().toString();
                                TemporalAccessor temporalAccessor = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(s2);
                                String format = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(temporalAccessor);
                                return format;
                            } catch (DateTimeParseException dtpe6) {
                                System.err.println("DateParser could not parse date!");
                                return "0";
                            }
                        }
                    }
                }
            }
        }
    }

    public static long parseDateMills(String s) {
        TemporalAccessor temporalAccessor = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(parseDate(s));
        Instant instant = Instant.from(temporalAccessor);
        return instant.toEpochMilli();
    }
}
