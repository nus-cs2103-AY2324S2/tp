package seedu.findvisor.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {
    @Test
    public void parseDateTimeString_validString() {
        assertEquals(LocalDateTime.of(2024, 1, 29, 14, 0), DateTimeUtil.parseDateTimeString("29-01-2024T14:00"));
    }

    @Test
    public void parseDateTimeString_invalidString_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseDateTimeString("INVALID STRING"));
    }

    @Test
    public void dateTimeToInputString() {
        assertEquals("29-01-2024T14:00", DateTimeUtil.dateTimeToInputString(LocalDateTime.of(2024, 1, 29, 14, 0)));
    }

    @Test
    public void dateTimeToString() {
        assertEquals("29-01-2024 14:00", DateTimeUtil.dateTimeToString(LocalDateTime.of(2024, 1, 29, 14, 0)));
    }

    @Test
    public void isAfterCurrentDateTime() {
        assertTrue(DateTimeUtil.isAfterCurrentDateTime(LocalDateTime.now().plusMinutes(5)));
    }

}

