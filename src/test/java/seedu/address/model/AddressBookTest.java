package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.exceptions.DuplicateCourseMateException;
import seedu.address.testutil.CourseMateBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getCourseMateList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateCourseMates_throwsDuplicateCourseMateException() {
        // Two courseMates with the same identity fields
        CourseMate editedAlice = new CourseMateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_JAVA)
                .build();
        List<CourseMate> newCourseMates = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newCourseMates);

        assertThrows(DuplicateCourseMateException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasCourseMate_nullCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasCourseMate(null));
    }

    @Test
    public void hasCourseMate_courseMateNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCourseMate(ALICE));
    }

    @Test
    public void hasCourseMate_courseMateInAddressBook_returnsTrue() {
        addressBook.addCourseMate(ALICE);
        assertTrue(addressBook.hasCourseMate(ALICE));
    }

    @Test
    public void hasCourseMate_courseMateWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addCourseMate(ALICE);
        CourseMate editedAlice = new CourseMateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_JAVA)
                .build();
        assertTrue(addressBook.hasCourseMate(editedAlice));
    }

    @Test
    public void getCourseMateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getCourseMateList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{courseMates=" + addressBook.getCourseMateList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose courseMates list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<CourseMate> courseMates = FXCollections.observableArrayList();

        AddressBookStub(Collection<CourseMate> courseMates) {
            this.courseMates.setAll(courseMates);
        }

        @Override
        public ObservableList<CourseMate> getCourseMateList() {
            return courseMates;
        }
    }

}
