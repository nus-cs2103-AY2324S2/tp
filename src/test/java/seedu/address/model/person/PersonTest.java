package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TWO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

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

        // same name, all other attributes different -> returns true
        Person editedAlice =
                new PersonBuilder(ALICE).withFirstParentPhone(VALID_PHONE_ONE_BOB)
                        .withSecondParentPhone(VALID_PHONE_TWO_BOB)
                        .withEmail(VALID_EMAIL_BOB)
                        .withAddress(VALID_ADDRESS_BOB)
                        .withTags(VALID_TAG_HUSBAND)
                        .withStudentId(VALID_STUDENT_ID_AMY).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase())
                .withStudentId(VALID_STUDENT_ID_AMY).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).withStudentId(VALID_STUDENT_ID_AMY).build();
        assertFalse(BOB.isSamePerson(editedBob));
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

        // different phone -> returns false
        editedAlice =
                new PersonBuilder(ALICE)
                        .withFirstParentPhone(VALID_PHONE_ONE_BOB)
                        .withSecondParentPhone(VALID_PHONE_TWO_BOB)
                        .build();
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
    public void hashCode_sameObject_consistentValue() {
        Person person = new PersonBuilder(ALICE).build();
        int initialHashCode = person.hashCode();

        assertEquals(initialHashCode, person.hashCode());
    }

    @Test
    public void hashCode_equalObjects_sameValue() {
        Person person1 = new PersonBuilder(ALICE).build();
        Person person2 = new PersonBuilder(ALICE).build();

        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void hashCode_differentObjects_differentValue() {
        Person person1 = new PersonBuilder(ALICE).build();
        Person person2 = new PersonBuilder(BOB).build();

        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", parent phone 1=" + ALICE.getParentPhoneOne()
                + ", parent phone 2=" + ALICE.getParentPhoneTwo()
                + ", email=" + ALICE.getEmail()
                + ", address=" + ALICE.getAddress()
                + ", student id=" + ALICE.getStudentId()
                + ", tags=" + ALICE.getTags()
                + ", class=" + ALICE.getFormClass() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
