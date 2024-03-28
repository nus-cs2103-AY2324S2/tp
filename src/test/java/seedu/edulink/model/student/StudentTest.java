package seedu.edulink.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.edulink.testutil.Assert.assertThrows;
import static seedu.edulink.testutil.TypicalPersons.ALICE;
import static seedu.edulink.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.edulink.testutil.PersonBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // id differs in case, all other attributes same -> returns True
        Student editedBob = new PersonBuilder(BOB).withId(VALID_ID_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameStudent(editedBob));

        // id has trailing spaces, all other attributes same -> returns True
        String idWithTrailingSpaces = VALID_ID_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(idWithTrailingSpaces).build();
        assertTrue(BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new PersonBuilder(ALICE).build();
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
        Student editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Student.class.getCanonicalName() + "{id=" + ALICE.getId()
            + ", name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
            + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", major=" + ALICE.getMajor()
            + ", intake=" + ALICE.getIntake()
            + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
