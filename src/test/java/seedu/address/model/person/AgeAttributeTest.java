package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeAttributeTest {

    @Test
    public void testValidAge() {
        // Test with minimum valid age
        AgeAttribute validAgeMin = new AgeAttribute("MinimumAge", 0);
        assertEquals(0, validAgeMin.getValue(), "Age should be set to 0 for minimum valid age");

        // Test with maximum valid age
        AgeAttribute validAgeMax = new AgeAttribute("MaximumAge", 150);
        assertEquals(150, validAgeMax.getValue(), "Age should be set to 150 for maximum valid age");
    }

    @Test
    public void testInvalidAgeBelowRange() {
        // Test with age below valid range
        assertThrows(IllegalArgumentException.class, () -> {
            new AgeAttribute("TooYoung", -1);
        }, "Should throw IllegalArgumentException for age below 0");
    }

    @Test
    public void testInvalidAgeAboveRange() {
        // Test with age above valid range
        assertThrows(IllegalArgumentException.class, () -> {
            new AgeAttribute("TooOld", 151);
        }, "Should throw IllegalArgumentException for age above 150");
    }
}

