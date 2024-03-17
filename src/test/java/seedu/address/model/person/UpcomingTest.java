package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UpcomingTest {

    @Test
    public void constructor_validDateTimeString_success() {
        // Valid date and time string
        Upcoming upcoming = new Upcoming("01-01-2022 1200");
        assertTrue(Upcoming.isValidUpcoming("01-01-2022 1200"));
        assertTrue(Upcoming.isValidUpcoming("")); // Empty string is considered valid
        assertEquals("01-01-2022 1200", upcoming.toString());
    }

    @Test
    public void constructor_invalidDateTimeString_throwsIllegalArgumentException() {
        // Invalid date and time string
        String invalidDateTime = "2022-01-01 12:00"; // Invalid format
        assertThrows(IllegalArgumentException.class, () -> new Upcoming(invalidDateTime));
    }

    @Test
    public void compareTo_sameDateTime_returnsZero() {
        Upcoming upcoming1 = new Upcoming("01-04-2023 1500");
        Upcoming upcoming2 = new Upcoming("01-04-2023 1500");

        assertEquals(0, upcoming1.compareTo(upcoming2));
    }

    @Test
    public void compareTo_laterDateTime_returnsPositive() {
        Upcoming upcoming1 = new Upcoming("01-04-2023 1500");
        Upcoming upcoming2 = new Upcoming("01-04-2023 1400");

        assertTrue(upcoming1.compareTo(upcoming2) > 0);
    }

    @Test
    public void compareTo_earlierDateTime_returnsNegative() {
        Upcoming upcoming1 = new Upcoming("01-04-2023 1400");
        Upcoming upcoming2 = new Upcoming("01-04-2023 1500");

        assertTrue(upcoming1.compareTo(upcoming2) < 0);
    }

    @Test
    public void compareTo_nullUpcoming_throws() {
        Upcoming upcoming1 = new Upcoming("01-04-2023 1500");
        Upcoming upcoming2 = new Upcoming("");

        // Assuming you want to throw an exception when comparing with a null Upcoming
        try {
            upcoming1.compareTo(upcoming2);
            // Fail the test if no exception is thrown
            throw new AssertionError("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception, test passed
        }
    }

    @Test
    public void compareTo_differentDayMonthYear_returnsCorrectValue() {
        Upcoming upcoming1 = new Upcoming("01-04-2023 1500");
        Upcoming upcoming2 = new Upcoming("02-05-2024 1600");

        assertTrue(upcoming1.compareTo(upcoming2) < 0);
        assertTrue(upcoming2.compareTo(upcoming1) > 0);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Upcoming upcoming = new Upcoming("01-01-2022 1200");
        assertTrue(upcoming.equals(upcoming));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        Upcoming upcoming = new Upcoming("01-01-2022 1200");
        assertFalse(upcoming.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        Upcoming upcoming = new Upcoming("01-01-2022 1200");
        assertFalse(upcoming.equals("01-01-2022 1200"));
    }

    @Test
    public void equals_sameDateTime_returnsTrue() {
        Upcoming upcoming1 = new Upcoming("01-01-2022 1200");
        Upcoming upcoming2 = new Upcoming("01-01-2022 1200");
        assertTrue(upcoming1.equals(upcoming2));
    }

    @Test
    public void equals_differentDateTime_returnsFalse() {
        Upcoming upcoming1 = new Upcoming("01-01-2022 1200");
        Upcoming upcoming2 = new Upcoming("02-01-2022 1200");
        assertFalse(upcoming1.equals(upcoming2));
    }

    @Test
    public void equals_differentUpcomingStatus_returnsFalse() {
        Upcoming upcoming1 = new Upcoming("");
        Upcoming upcoming2 = new Upcoming("01-04-2023 1500");
        assertFalse(upcoming1.equals(upcoming2));
    }

    @Test
    public void equals_oneUpcomingEmpty_returnsFalse() {
        Upcoming upcoming1 = new Upcoming("");
        Upcoming upcoming2 = new Upcoming("01-04-2023 1500");
        assertFalse(upcoming1.equals(upcoming2));
    }

    @Test
    public void equals_bothUpcomingEmpty_returnsTrue() {
        Upcoming upcoming1 = new Upcoming("");
        Upcoming upcoming2 = new Upcoming("");
        assertTrue(upcoming1.equals(upcoming2));
    }

    @Test
    public void hashCode_sameDateTime_returnsSameHashCode() {
        Upcoming upcoming1 = new Upcoming("01-01-2022 1200");
        Upcoming upcoming2 = new Upcoming("01-01-2022 1200");
        assertEquals(upcoming1.hashCode(), upcoming2.hashCode());
    }

    @Test
    public void hashCode_differentDateTime_returnsDifferentHashCode() {
        Upcoming upcoming1 = new Upcoming("01-01-2022 1200");
        Upcoming upcoming2 = new Upcoming("02-01-2022 1200");
        assertNotEquals(upcoming1.hashCode(), upcoming2.hashCode());
    }

    @Test
    public void toString_hasUpcoming_returnsFormattedDateTime() {
        Upcoming upcoming = new Upcoming("01-01-2022 1200");
        String expected = "01-01-2022 1200";
        assertEquals(expected, upcoming.toString());
    }

    @Test
    public void toString_noUpcoming_returnsEmptyString() {
        Upcoming upcoming = new Upcoming("");
        String expected = "";
        assertEquals(expected, upcoming.toString());
    }


}
