package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.TYPICAL_CLASS_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.CourseCode;

public class JsonAdaptedClassTest {

    private static final String INVALID_CC = "cs@1234";
    @Test
    public void toModelType_validClass_returnsClass() throws Exception {
        JsonAdaptedClass classes = new JsonAdaptedClass(TYPICAL_CLASS_1);
        assertEquals(TYPICAL_CLASS_1, classes.toModelType());
    }

    @Test
    public void toModelType_invalidCC_throwsIllegalValueException() {
        JsonAdaptedClass classes = new JsonAdaptedClass(INVALID_CC);
        String expectedMessage = CourseCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, classes::toModelType);
    }
}
