package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BirthdayAttributeTest {

    @Test
    public void testValidBirthdayBeforeToday() {
        // Assuming today is not the test execution day, adjust the date accordingly
        LocalDate validBirthday = LocalDate.now().minusYears(20); // 20 years ago
        BirthdayAttribute birthdayAttribute = new BirthdayAttribute("ValidBirthday", validBirthday);
        assertEquals(validBirthday, birthdayAttribute.getValue(),
                "Birthday should be set correctly for dates before today");
    }

    @Test
    public void testInvalidBirthdayToday() {
        // Test with today's date
        LocalDate today = LocalDate.now();
        assertThrows(IllegalArgumentException.class, () -> {
            new BirthdayAttribute("BirthdayToday", today);
        }, "Should throw IllegalArgumentException for birthdays set to today");
    }

    @Test
    public void testInvalidBirthdayInFuture() {
        // Test with a future date
        LocalDate futureDate = LocalDate.now().plusDays(1); // Tomorrow
        assertThrows(IllegalArgumentException.class, () -> {
            new BirthdayAttribute("FutureBirthday", futureDate);
        }, "Should throw IllegalArgumentException for future birthdays");
    }
}
