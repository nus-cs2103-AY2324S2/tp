package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.TYPICAL_CLASS_1;
import static seedu.address.testutil.TypicalPersons.getTypicalClassBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;

public class ClassBookTest {


    private final ClassBook classBook = new ClassBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), classBook.getClassList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classBook.resetData((null)));
    }

    @Test
    public void resetData_withValidReadOnlyClassBook_replacesData() {
        ClassBook newData = getTypicalClassBook();
        classBook.resetData(newData);
        assertEquals(newData, classBook);
    }

    @Test
    public void hasClass_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classBook.hasClass(null));
    }

    @Test
    public void hasClass_classNotInClassBook_returnsFalse() {
        assertFalse(classBook.hasClass(TYPICAL_CLASS_1));
    }

    @Test
    public void hasClass_classInClassBook_returnsTrue() {
        classBook.createClass(TYPICAL_CLASS_1);
        assertTrue(classBook.hasClass(TYPICAL_CLASS_1));
    }

    @Test
    public void toStringMethod() {
        String expected = ClassBook.class.getCanonicalName() + "{classes=" + classBook.getClassList() + "}";
        assertEquals(expected, classBook.toString());
    }

    @Test
    public void createClass_newClass_success() {
        Classes sampleClass = new Classes(new CourseCode("CS101"), new AddressBook());
        ClassBook classBook = new ClassBook();
        classBook.createClass(sampleClass);
        assertTrue(classBook.hasClass(sampleClass));
    }
}
