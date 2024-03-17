package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UPCOMING_BOB;
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
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
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

        // different upcoming -> returns false
        editedAlice = new PersonBuilder(ALICE).withUpcoming(VALID_UPCOMING_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different lastcontact -> returns false
        editedAlice = new PersonBuilder(ALICE).withLastContact(VALID_LAST_CONTACT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashCode_samePersonFields_sameHashCode() {
        // same object -> same hash code
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());

        // different object, same fields -> same hash code
        Person otherAliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), otherAliceCopy.hashCode());
    }

    @Test
    public void hashCode_differentPersonFields_differentHashCode() {
        // different person -> different hash code
        assertNotEquals(ALICE.hashCode(), BOB.hashCode());

        // different name -> different hash code
        Person editedAlice = new PersonBuilder(ALICE).withName("Different Name").build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different phone -> different hash code
        editedAlice = new PersonBuilder(ALICE).withPhone("12345678").build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different email -> different hash code
        editedAlice = new PersonBuilder(ALICE).withEmail("different@example.com").build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different address -> different hash code
        editedAlice = new PersonBuilder(ALICE).withAddress("Different Address").build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different tags -> different hash code
        editedAlice = new PersonBuilder(ALICE).withTags("differentTag").build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different upcoming -> different hash code
        editedAlice = new PersonBuilder(ALICE).withUpcoming("01-01-2024 1200").build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different lastcontact -> same hash code (lastcontact not included in hashCode)
        editedAlice = new PersonBuilder(ALICE).withLastContact("01-01-2023 1200").build();
        assertEquals(ALICE.hashCode(), editedAlice.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", tags=" + ALICE.getTags()
                + ", upcoming=" + ALICE.getUpcoming()
                + ", lastcontact=" + ALICE.getLastcontact() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
