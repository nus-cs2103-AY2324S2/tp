package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALICE_MICROSOFT;
import static seedu.address.testutil.TypicalInternships.BENSON_GOOGLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.model.internship.exceptions.InternshipNotFoundException;
import seedu.address.testutil.InternshipBuilder;

public class UniqueInternshipListTest {

    private final UniqueInternshipList uniqueInternshipList = new UniqueInternshipList();

    @Test
    public void contains_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.contains(null));
    }

    @Test
    public void contains_internshipNotInList_returnsFalse() {
        assertFalse(uniqueInternshipList.contains(ALICE_MICROSOFT));
    }

    @Test
    public void contains_internshipInList_returnsTrue() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        assertTrue(uniqueInternshipList.contains(ALICE_MICROSOFT));
    }

    @Test
    public void contains_internshipWithSameIdentityFieldsInList_returnsTrue() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        Internship editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withApplicationStatus("rejected")
                .withContactName("Not Alice").withContactEmail("notalice@example.com")
                .withContactNumber("98765432").build();
        assertTrue(uniqueInternshipList.contains(editedAlice));
    }

    @Test
    public void add_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.add(null));
    }

    @Test
    public void add_duplicateInternship_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.add(ALICE_MICROSOFT));
    }

    @Test
    public void setInternship_nullTargetInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(null, ALICE_MICROSOFT));
    }

    @Test
    public void setInternship_nullEditedInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(ALICE_MICROSOFT, null));
    }

    @Test
    public void setInternship_targetInternshipNotInList_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.setInternship(ALICE_MICROSOFT,
                ALICE_MICROSOFT));
    }

    @Test
    public void setInternship_editedInternshipIsSameInternship_success() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        uniqueInternshipList.setInternship(ALICE_MICROSOFT, ALICE_MICROSOFT);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(ALICE_MICROSOFT);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasSameIdentity_success() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        Internship editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withApplicationStatus("rejected")
                .withContactName("Not Alice").withContactEmail("notalice@example.com")
                .withContactNumber("98765432").build();
        uniqueInternshipList.setInternship(ALICE_MICROSOFT, editedAlice);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(editedAlice);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasDifferentIdentity_success() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        uniqueInternshipList.setInternship(ALICE_MICROSOFT, BENSON_GOOGLE);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BENSON_GOOGLE);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasNonUniqueIdentity_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        uniqueInternshipList.add(BENSON_GOOGLE);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.setInternship(ALICE_MICROSOFT,
                BENSON_GOOGLE));
    }

    @Test
    public void remove_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.remove(null));
    }

    @Test
    public void remove_internshipDoesNotExist_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.remove(ALICE_MICROSOFT));
    }

    @Test
    public void remove_existingInternship_removesInternship() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        uniqueInternshipList.remove(ALICE_MICROSOFT);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullUniqueInternshipList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueInternshipList.setInternships((UniqueInternshipList) null));
    }

    @Test
    public void setInternships_uniqueInternshipList_replacesOwnListWithProvidedUniqueInternshipList() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BENSON_GOOGLE);
        uniqueInternshipList.setInternships(expectedUniqueInternshipList);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternships((List<Internship>) null));
    }

    @Test
    public void setInternships_list_replacesOwnListWithProvidedList() {
        uniqueInternshipList.add(ALICE_MICROSOFT);
        List<Internship> internshipList = Collections.singletonList(BENSON_GOOGLE);
        uniqueInternshipList.setInternships(internshipList);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BENSON_GOOGLE);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_listWithDuplicateInternships_throwsDuplicateInternshipException() {
        List<Internship> listWithDuplicateInternships = Arrays.asList(ALICE_MICROSOFT, ALICE_MICROSOFT);
        assertThrows(DuplicateInternshipException.class, () ->
                uniqueInternshipList.setInternships(listWithDuplicateInternships));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueInternshipList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueInternshipList.asUnmodifiableObservableList().toString(), uniqueInternshipList.toString());
    }


    @Test
    public void testContains() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        assertTrue(internshipList.contains(ALICE_MICROSOFT));
    }

    @Test
    public void testAddInternship() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        assertTrue(internshipList.contains(ALICE_MICROSOFT));
    }

    @Test
    public void testSetInternship() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        internshipList.setInternship(ALICE_MICROSOFT, BENSON_GOOGLE);
        assertFalse(internshipList.contains(ALICE_MICROSOFT));
        assertTrue(internshipList.contains(BENSON_GOOGLE));
    }

    @Test
    public void testRemoveInternship() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        internshipList.remove(ALICE_MICROSOFT);
        assertFalse(internshipList.contains(ALICE_MICROSOFT));
    }

    @Test
    public void testSetInternships() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        UniqueInternshipList replacement = new UniqueInternshipList();
        replacement.add(BENSON_GOOGLE);
        internshipList.setInternships(replacement);
        assertFalse(internshipList.contains(ALICE_MICROSOFT));
        assertTrue(internshipList.contains(BENSON_GOOGLE));
    }

    @Test
    public void testEquals() {
        UniqueInternshipList internshipList1 = new UniqueInternshipList();
        UniqueInternshipList internshipList2 = new UniqueInternshipList();
        internshipList1.add(ALICE_MICROSOFT);
        internshipList2.add(ALICE_MICROSOFT);
        assertEquals(internshipList1, internshipList2);
    }

    @Test
    public void asUnmodifiableObservableList() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        assertEquals(internshipList.asUnmodifiableObservableList().size(), 1);
    }

    @Test
    public void testHashCode() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        assertEquals(internshipList.hashCode(), internshipList.asUnmodifiableObservableList().hashCode());
    }

    @Test
    public void testToString() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        internshipList.add(ALICE_MICROSOFT);
        assertEquals(internshipList.toString(), internshipList.asUnmodifiableObservableList().toString());
    }
}
