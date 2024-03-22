package tutorpro.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutorpro.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static tutorpro.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tutorpro.model.person.exceptions.DuplicatePersonException;
import tutorpro.model.person.exceptions.PersonNotFoundException;
import tutorpro.model.person.student.Student;
import tutorpro.testutil.Assert;
import tutorpro.testutil.StudentBuilder;
import tutorpro.testutil.TypicalStudents;

public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(TypicalStudents.ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(TypicalStudents.ALICE);
        assertTrue(uniquePersonList.contains(TypicalStudents.ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePersonList.add(TypicalStudents.ALICE);
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalStudents.ALICE);
        Assert.assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(TypicalStudents.ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList
                .setPerson(null, TypicalStudents.ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList
                .setPerson(TypicalStudents.ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        Assert.assertThrows(PersonNotFoundException.class, () -> uniquePersonList
                .setPerson(TypicalStudents.ALICE, TypicalStudents.ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(TypicalStudents.ALICE);
        uniquePersonList.setPerson(TypicalStudents.ALICE, TypicalStudents.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalStudents.ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(TypicalStudents.ALICE);
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        uniquePersonList.setPerson(TypicalStudents.ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(TypicalStudents.ALICE);
        uniquePersonList.setPerson(TypicalStudents.ALICE, TypicalStudents.BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalStudents.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalStudents.ALICE);
        uniquePersonList.add(TypicalStudents.BOB);
        Assert.assertThrows(DuplicatePersonException.class, () -> uniquePersonList
                .setPerson(TypicalStudents.ALICE, TypicalStudents.BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        Assert.assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(TypicalStudents.ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(TypicalStudents.ALICE);
        uniquePersonList.remove(TypicalStudents.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(TypicalStudents.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalStudents.BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Student>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(TypicalStudents.ALICE);
        List<Student> personList = Collections.singletonList(TypicalStudents.BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalStudents.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Student> listWithDuplicatePersons = Arrays.asList(TypicalStudents.ALICE, TypicalStudents.ALICE);
        Assert.assertThrows(DuplicatePersonException.class, () -> uniquePersonList
                .setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePersonList.asUnmodifiableObservableList().toString(), uniquePersonList.toString());
    }
}
