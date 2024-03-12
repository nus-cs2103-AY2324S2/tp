package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;

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

public class ContactListTest {

    private final ContactList contactList = new ContactList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), contactList.getCourseMateList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyContactList_replacesData() {
        ContactList newData = getTypicalContactList();
        contactList.resetData(newData);
        assertEquals(newData, contactList);
    }

    @Test
    public void resetData_withDuplicateCourseMates_throwsDuplicateCourseMateException() {
        // Two courseMates with the same identity fields
        CourseMate editedAlice = new CourseMateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_JAVA)
                .build();
        List<CourseMate> newCourseMates = Arrays.asList(ALICE, editedAlice);
        ContactListStub newData = new ContactListStub(newCourseMates);

        assertThrows(DuplicateCourseMateException.class, () -> contactList.resetData(newData));
    }

    @Test
    public void hasCourseMate_nullCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.hasCourseMate(null));
    }

    @Test
    public void hasCourseMate_courseMateNotInAContactList_returnsFalse() {
        assertFalse(contactList.hasCourseMate(ALICE));
    }

    @Test
    public void hasCourseMate_courseMateInContactList_returnsTrue() {
        contactList.addCourseMate(ALICE);
        assertTrue(contactList.hasCourseMate(ALICE));
    }

    @Test
    public void hasCourseMate_courseMateWithSameIdentityFieldsInContactList_returnsTrue() {
        contactList.addCourseMate(ALICE);
        CourseMate editedAlice = new CourseMateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_JAVA)
                .build();
        assertTrue(contactList.hasCourseMate(editedAlice));
    }

    @Test
    public void getCourseMateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> contactList.getCourseMateList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ContactList.class.getCanonicalName() + "{courseMates=" + contactList.getCourseMateList() + "}";
        assertEquals(expected, contactList.toString());
    }

    /**
     * A stub ReadOnlyContactList whose courseMates list can violate interface constraints.
     */
    private static class ContactListStub implements ReadOnlyContactList {
        private final ObservableList<CourseMate> courseMates = FXCollections.observableArrayList();

        ContactListStub(Collection<CourseMate> courseMates) {
            this.courseMates.setAll(courseMates);
        }

        @Override
        public ObservableList<CourseMate> getCourseMateList() {
            return courseMates;
        }
    }

}
