package seedu.address.model.person.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateAttributeTest {

    @Test
    public void testConstructor() {
        LocalDate testDate = LocalDate.of(2020, 1, 1);
        DateAttribute dateAttribute = new DateAttribute("TestDate", testDate);
        assertEquals("TestDate", dateAttribute.getName(), "Constructor should correctly set name");
        assertEquals(testDate, dateAttribute.getValue(), "Constructor should correctly set date value");
    }

    @Test
    public void testGetValueAsString() {
        LocalDate testDate = LocalDate.of(2020, 2, 20);
        DateAttribute dateAttribute = new DateAttribute("TestDateAsString", testDate);
        assertEquals("2020-02-20", dateAttribute.getValueAsString(),
                "getValueAsString should return correct date string");
    }

    @Test
    public void testIsOnDate() {
        LocalDate testDate = LocalDate.of(2021, 3, 15);
        DateAttribute dateAttribute = new DateAttribute("TestIsOnDate", testDate);

        // Test with the same date
        assertTrue(dateAttribute.isOnDate(LocalDate.of(2021, 3, 15)),
                "isOnDate should return true for the same date");

        // Test with a different date
        assertFalse(dateAttribute.isOnDate(LocalDate.of(2021, 3, 14)),
                "isOnDate should return false for a different date");
    }
}

