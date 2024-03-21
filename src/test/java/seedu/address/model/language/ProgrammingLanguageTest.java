package seedu.address.model.language;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProgrammingLanguageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProgrammingLanguage(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidLanguageName = "";
        assertThrows(IllegalArgumentException.class, () -> new ProgrammingLanguage(invalidLanguageName));
    }

    @Test
    public void isValidLanguageName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> ProgrammingLanguage.isValidLanguageName(null));
    }

}
