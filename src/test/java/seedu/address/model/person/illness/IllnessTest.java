package seedu.address.model.person.illness;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IllnessTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Illness(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Illness(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Illness.isValidTagName(null));
    }

}
