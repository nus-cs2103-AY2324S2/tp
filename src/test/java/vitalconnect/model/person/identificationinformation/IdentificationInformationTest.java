package vitalconnect.model.person.identificationinformation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdentificationInformationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IdentificationInformation(new Name(null), null));
        assertThrows(NullPointerException.class, () -> new IdentificationInformation(null, new Nric(null)));
        assertThrows(NullPointerException.class, () -> new IdentificationInformation(new Name(null), new Nric(null)));
    }

    @Test
    public void constructor_invalidIdentificationInformation_throwsIllegalArgumentException() {
        String invalidName = "";
        String invalidNric = "";
        String validName = "John";
        String validNric = "S1234567D";
        assertThrows(IllegalArgumentException.class, () -> new IdentificationInformation(invalidName, invalidNric));
        assertThrows(IllegalArgumentException.class, () -> new IdentificationInformation(invalidName, validNric));
        assertThrows(IllegalArgumentException.class, () -> new IdentificationInformation(validName, invalidNric));
    }

    @Test
    public void isValidIdentificationInformation() {
        // null name and nric
        assertThrows(NullPointerException.class, () ->
                     IdentificationInformation.isValidIdentificationInformation(null, null));

        String validNric = "S1234567D";
        String validName = "John";

        // invalid name and valid nric
        assertFalse(IdentificationInformation
                    .isValidIdentificationInformation("", validNric)); // empty string
        assertFalse(IdentificationInformation
                    .isValidIdentificationInformation(" ", validNric)); // spaces only
        assertFalse(IdentificationInformation
                    .isValidIdentificationInformation("^", validNric)); // only non-alphanumeric characters
        assertFalse(IdentificationInformation
                    .isValidIdentificationInformation("peter*", validNric)); // contains non-alphanumeric characters

        // valid name and invalid nric
        assertFalse(IdentificationInformation
                    .isValidIdentificationInformation(validName,
                                                    "S12345678A")); // improper format
        assertFalse(IdentificationInformation
                    .isValidIdentificationInformation(validName,
                                                    "S1234567A")); // contains proper format but fails checksum

        // valid name and valid nric
        assertTrue(IdentificationInformation
                   .isValidIdentificationInformation("peter jack",
                                                     "S1234567D")); // empty string
        assertTrue(IdentificationInformation
                   .isValidIdentificationInformation("12345",
                                                     "S1234567D")); // spaces only
        assertTrue(IdentificationInformation
                   .isValidIdentificationInformation("peter the 2nd",
                                                     "S1234567D")); // only non-alphanumeric characters
        assertTrue(IdentificationInformation
                   .isValidIdentificationInformation("Capital Tan",
                                                     "S1234567D")); // contains non-alphanumeric characters
        assertTrue(IdentificationInformation
                   .isValidIdentificationInformation("David Roger Jackson Ray Jr 2nd",
                                                     "S1234567D")); // contains proper format but fails checksum
    }

    @Test
    public void equals() {
        IdentificationInformation info1 = new IdentificationInformation("Valid Name",
                                                                        "S1234567D");
        IdentificationInformation info2 = new IdentificationInformation(new Name("Valid Name"),
                                                                        new Nric("S1234567D"));

        // same values -> returns true
        assertTrue(info1.equals(info2));

        // same object -> returns true
        assertTrue(info1.equals(info1));
        assertTrue(info2.equals(info2));

        // null -> returns false
        assertFalse(info1.equals(null));
        assertFalse(info2.equals(null));

        // different types -> returns false
        assertFalse(info1.equals(5.0f));
        assertFalse(info2.equals(5.0f));

        // different values -> returns false
        assertFalse(info1.equals(new IdentificationInformation(new Name("Other Valid Name"),
                                                               new Nric("S1234568B"))));
        assertFalse(info2.equals(new IdentificationInformation(new Name("Other Valid Name"),
                                                               new Nric("S1234568B"))));
    }
}
