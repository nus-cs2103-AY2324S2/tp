package seedu.address.model.person.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    @Nested
    class EqualsTests {
        @Test
        public void equals_sameValues() {
            Description description1 = new Description("Test");
            Description description2 = new Description("Test");
            assertEquals(description1, description2);
        }

        @Test
        public void equals_differentValues() {
            Description description1 = new Description("Test1");
            Description description2 = new Description("Test2");
            assertNotEquals(description1, description2);
        }

        @Test
        public void equals_null() {
            Description description = new Description("Test");
            assertNotEquals(description, null);
        }

        @Test
        public void equals_differentClass() {
            Description description = new Description("Test");
            Object other = new Object();
            assertNotEquals(description, other);
        }
    }

    @Nested
    class HashCodeTests {
        @Test
        public void hashCode_same() {
            Description description1 = new Description("Test");
            Description description2 = new Description("Test");
            assertEquals(description1.hashCode(), description2.hashCode());
        }

        @Test
        public void hashCode_different() {
            Description description1 = new Description("Test1");
            Description description2 = new Description("Test2");

            assertNotEquals(description1.hashCode(), description2.hashCode());
        }
    }

    @Nested
    class ToStringTests {
        @Test
        public void toString_sucess() {
            String validDescription = "Some valid description";
            Description description = new Description(validDescription);
            assertEquals(validDescription, description.toString());
        }
    }
}
