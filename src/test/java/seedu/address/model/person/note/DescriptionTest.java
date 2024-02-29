package seedu.address.model.person.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Nested
    class ConstructorTests {
        @Test
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Description(null));
        }

        @Test
        public void constructor_invalidAddress_throwsIllegalArgumentException() {
            String invalid = "";
            assertThrows(IllegalArgumentException.class, () -> new Description(invalid));
        }
    }

    @Nested
    class IsValidTests {
        @Test
        public void isValid_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> Description.isValid(null));
        }

        @Test
        public void isValid_invalid() {
            assertFalse(Description.isValid(""));
        }

        @Test
        public void isValid_valid() {
            assertTrue(Description.isValid("Blk 456, Den Road, #01-355"));
        }
    }
}
