package seedu.address.model.cca;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CcaTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cca(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidCcaName = "";
        assertThrows(IllegalArgumentException.class, () -> new Cca(invalidCcaName));
    }

    @Test
    public void isValidCcaName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Cca.isValidCcaName(null));
    }

}
