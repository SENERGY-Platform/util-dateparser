import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateParserTest {

    @Test
    public void parseDate() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        dateTime = dateTime.minusNanos(dateTime.getNano());
        String s;
        s = DateParser.parseDate(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        Assert.assertEquals(dateTime.toString(), s);
        s = DateParser.parseDate(dateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        Assert.assertEquals(dateTime.toString(), s);
        s = DateParser.parseDate(dateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        Assert.assertEquals(dateTime.toString(), s);
        s = DateParser.parseDate(dateTime.format(DateTimeFormatter.ISO_INSTANT));
        Assert.assertEquals(dateTime.atZoneSameInstant(ZoneOffset.UTC).toString(), s);
        s = DateParser.parseDate(dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        Assert.assertEquals(dateTime.toString(), s);
    }
}