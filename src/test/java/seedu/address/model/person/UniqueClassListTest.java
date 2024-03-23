package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.TYPICAL_CLASS_1;
import static seedu.address.testutil.TypicalPersons.TYPICAL_CLASS_2;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;

public class UniqueClassListTest {

    private final UniqueClassList uniqueClassList = new UniqueClassList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.contains(null));
    }

    @Test
    public void contains_classNotInList_returnsFalse() {
        assertFalse(uniqueClassList.contains(TYPICAL_CLASS_1));
    }

    @Test
    public void contains_classInList_returnsTrue() {
        uniqueClassList.add(TYPICAL_CLASS_1);
        assertTrue(uniqueClassList.contains(TYPICAL_CLASS_1));
    }

    @Test
    public void create_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.add(null));
    }

    @Test
    public void setClass_nullTargetClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClass(null, TYPICAL_CLASS_1));
    }

    @Test
    public void setClass_nullEditedClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClass(TYPICAL_CLASS_1, null));
    }

    @Test
    public void setClass_editedClassIsTheSameClass_success() {
        uniqueClassList.add(TYPICAL_CLASS_1);
        uniqueClassList.setClass(TYPICAL_CLASS_1, TYPICAL_CLASS_1);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(TYPICAL_CLASS_1);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void remove_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.remove(null));
    }

    @Test
    public void remove_existingClass_success() {
        uniqueClassList.add(TYPICAL_CLASS_1);
        uniqueClassList.remove(TYPICAL_CLASS_1);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClasses_nullUniqueClassList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClasses((UniqueClassList) null));
    }

    @Test
    public void setClasses_uniqueClassList_replaceSuccessful() {
        uniqueClassList.add(TYPICAL_CLASS_1);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(TYPICAL_CLASS_2);
        uniqueClassList.setClasses(expectedUniqueClassList);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClasses((List<Classes>) null));
    }

    @Test
    public void setClasses_list_replaces() {
        uniqueClassList.add(TYPICAL_CLASS_1);
        List<Classes> classList = Collections.singletonList(TYPICAL_CLASS_2);
        uniqueClassList.setClasses(classList);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(TYPICAL_CLASS_2);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueClassList.asUnmodifiableObservableList().toString(), uniqueClassList.toString());
    }

    @Test
    public void add_validClass_success() {
        Classes sampleClass = new Classes(new CourseCode("CS101"), new AddressBook());
        UniqueClassList uniqueClassList = new UniqueClassList();
        uniqueClassList.add(sampleClass);
        assertTrue(uniqueClassList.contains(sampleClass));
    }


}
