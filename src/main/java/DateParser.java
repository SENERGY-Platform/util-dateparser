import java.time.Instant;
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
                } catch (DateTimeParseException dtp3) {
                    try {
                        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DateTimeFormatter.ISO_INSTANT.parse(s));
                    } catch (DateTimeParseException dtpe4) {
                        try {
                            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DateTimeFormatter.RFC_1123_DATE_TIME.parse(s));
                        } catch (DateTimeParseException dtpe5) {
                            System.err.println("DateParser could not parse date!");
                            return "0";
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
