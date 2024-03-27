package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.TYPICAL_CLASS_1;
import static seedu.address.testutil.TypicalPersons.TYPICAL_CLASS_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClassBuilder;

public class ClassesTest {

    @Test
    public void isSameClass() {
        assertTrue(TYPICAL_CLASS_1.isSameClass(TYPICAL_CLASS_1));

        assertFalse(TYPICAL_CLASS_1.isSameClass(null));
    }

    @Test
    public void equals() {
        Classes copy = new ClassBuilder(TYPICAL_CLASS_1).build();
        assertTrue(TYPICAL_CLASS_1.equals(copy));

        assertTrue(TYPICAL_CLASS_2.equals(TYPICAL_CLASS_2));

        assertFalse(TYPICAL_CLASS_2.equals(null));

        assertFalse(TYPICAL_CLASS_2.equals(5));

        assertFalse(TYPICAL_CLASS_2.equals(TYPICAL_CLASS_1));

    }

    @Test
    public void toStringMethod() {
        String expected = Classes.class.getCanonicalName() + "{courseCode=" + TYPICAL_CLASS_1.getCourseCode() + "}";
        assertEquals(expected, TYPICAL_CLASS_1.toString());
    }
}
