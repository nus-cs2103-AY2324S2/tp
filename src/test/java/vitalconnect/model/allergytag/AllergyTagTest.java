package vitalconnect.model.allergytag;

import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import vitalconnect.model.person.medicalinformation.AllergyTag;

public class AllergyTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AllergyTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new AllergyTag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null allergytag name
        assertThrows(NullPointerException.class, () -> AllergyTag.isValidTagName(null));
    }

}
