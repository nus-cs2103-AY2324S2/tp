package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTmeUtilTest {
    @Test
    public void isValidDateTimeString_validFormat_returnTrue() {
        assertTrue(DateTimeUtil.isValidDateTimeString("2022-03-13 12:30"));
    }

    @Test
    public void isValidDateTimeString_invalidFormat_returnFalse() {
        assertFalse(DateTimeUtil.isValidDateTimeString("2022/03/13 12:30"));
    }

    @Test
    public void isValidDateTimeString_nullInput_returnFalse() {
        assertFalse(DateTimeUtil.isValidDateTimeString(null));
    }

    @Test
    public void isParsableDateTimeString_parsableDateTime_returnTrue() {
        assertTrue(DateTimeUtil.isParsableDateTimeString("2022-03-13 12:30"));
    }

    @Test
    public void isParsableDateTimeString_invalidDateTime_returnFalse() {
        assertFalse(DateTimeUtil.isParsableDateTimeString("2022/03/13 12:30"));
    }

    @Test
    public void isPastDateTime_pastDateTime_returnTrue() {
        assertTrue(DateTimeUtil.isPastDateTime(LocalDateTime.now().minusHours(1)));
    }

    @Test
    public void isPastDateTime_futureDateTime_returnFalse() {
        assertFalse(DateTimeUtil.isPastDateTime(LocalDateTime.now().plusHours(1)));
    }

    @Test
    public void parseStringToDateTime_validDateTime_returnLocalDateTime() {
        LocalDateTime expectedDateTime = LocalDateTime.parse("2022-03-13 12:30",
                DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT));
        assertEquals(expectedDateTime, DateTimeUtil.parseStringToDateTime("2022-03-13 12:30"));
    }

    @Test
    public void parseStringToDateTime_invalidDateTime_throwDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseStringToDateTime("2022/03/13 12:30"));
    }

    @Test
    public void parseDateToString_validDateTime_returnFormattedString() {
        LocalDateTime dateTime = LocalDateTime.parse("2022-03-13 12:30",
                DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT));
        assertEquals("2022-03-13 12:30", DateTimeUtil.parseDateToString(dateTime));
    }

    @Test
    public void parseDateToString_nullDateTime_returnEmptyString() {
        assertEquals("", DateTimeUtil.parseDateToString(null));
    }

    @Test
    public void getMessageConstraintsForDateType_validDateType_returnFormattedMessage() {
        assertEquals("Birthday should be in the format YYYY-MM-DD HH:mm and must be an actual dateTime",
                DateTimeUtil.getMessageConstraintsForDateType("Birthday"));
    }
}


