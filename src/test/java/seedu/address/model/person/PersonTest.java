package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRSTNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRSTNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WAITER;
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

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WAITER).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different first name, same phone number -> returns true
        Person editedBob = new PersonBuilder(BOB).withFirstName(VALID_FIRSTNAME_AMY).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // different first name, same phone number -> returns true
        editedBob = new PersonBuilder(BOB).withFirstName(VALID_FIRSTNAME_BOB).withLastName(VALID_LASTNAME_AMY).build();
        assertTrue(BOB.isSamePerson(editedBob));
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

        // different first name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withFirstName(VALID_FIRSTNAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different last name -> returns false
        editedAlice = new PersonBuilder(ALICE).withLastName(VALID_LASTNAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_WAITER).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected =
            Person.class.getCanonicalName() + "{firstName=" + ALICE.getFirstName()
                + ", lastName=" + ALICE.getLastName()
                + ", phone=" + ALICE.getPhone()
                + ", sex=" + ALICE.getSex()
                + ", payRate=" + ALICE.getPayRate()
                + ", address=" + ALICE.getAddress()
                + ", bankDetails=" + ALICE.getBankDetails()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
