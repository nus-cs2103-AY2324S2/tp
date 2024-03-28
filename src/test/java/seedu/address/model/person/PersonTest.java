package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for the Person class.
 */
public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different --> return false
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB).withTags(VALID_TAG_FRIEND).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same email, all other attributes different --> return true
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB).withTags(VALID_TAG_FRIEND).build();

        assertTrue(ALICE.isSamePerson(editedAlice));

        // same studentId, all other attributes different -> returns true
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_FRIEND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different studentId, all other attributes same -> returns true
        editedAlice = new PersonBuilder(ALICE).withStudentId(VALID_STUDENT_ID_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same --> return true
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different student id -> returns false
        editedAlice = new PersonBuilder(ALICE).withStudentId(VALID_STUDENT_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", email=" + ALICE.getEmail() + ", stuId=" + ALICE.getStudentId()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
