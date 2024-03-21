package seedu.address.model.language;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void constructor_validLanguageName_equals() {
        // Two instances with the same valid language name should be equal
        String languageName = "Java";
        ProgrammingLanguage language1 = new ProgrammingLanguage(languageName);
        ProgrammingLanguage language2 = new ProgrammingLanguage(languageName);
        assertEquals(language1, language2);
    }

    @Test
    public void isValidLanguageName_validLanguageName_returnsTrue() {
        // Valid language name
        String validLanguageName = "Java";
        assertTrue(ProgrammingLanguage.isValidLanguageName(validLanguageName));
    }

    @Test
    public void equals_sameInstance_returnsTrue() {
        ProgrammingLanguage language = new ProgrammingLanguage("Java");
        assertTrue(language.equals(language));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        ProgrammingLanguage language = new ProgrammingLanguage("Java");
        assertFalse(language.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        ProgrammingLanguage language = new ProgrammingLanguage("Java");
        assertFalse(language.equals("Java"));
    }

    @Test
    public void hashCode_sameInstance_returnsEqualHashCodes() {
        ProgrammingLanguage language1 = new ProgrammingLanguage("Java");
        ProgrammingLanguage language2 = new ProgrammingLanguage("Java");
        assertEquals(language1.hashCode(), language2.hashCode());
    }
}
