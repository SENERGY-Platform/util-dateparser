import org.infai.ses.senergy.util.DateParser;
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
        String format = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.toString(), s);

        format = dateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.toString(), s);

        format = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.toString(), s);

        format = dateTime.format(DateTimeFormatter.ISO_INSTANT);
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.atZoneSameInstant(ZoneOffset.UTC).toString(), s);

        format = dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.toString(), s);

        format = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.toString(), s);

        format = "" + dateTime.toInstant().getEpochSecond();
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.atZoneSameInstant(ZoneOffset.UTC).toString(), s);

        format = "" + dateTime.toInstant().toEpochMilli();
        s = DateParser.parseDate(format);
        Assert.assertEquals(dateTime.atZoneSameInstant(ZoneOffset.UTC).toString(), s);
    }
}
