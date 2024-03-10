package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.BOBMAINTAINER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MaintainerBuilder;

public class MaintainerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Maintainer person = new MaintainerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameStaff() {
        // same object -> returns true
        assertTrue(ALICEMAINTAINER.isSamePerson(ALICEMAINTAINER));

        // null -> returns false
        assertFalse(ALICEMAINTAINER.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new MaintainerBuilder(ALICEMAINTAINER).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICEMAINTAINER.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new MaintainerBuilder(ALICEMAINTAINER).withName(VALID_NAME_BOB).build();
        assertFalse(ALICEMAINTAINER.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new MaintainerBuilder(BOBMAINTAINER).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOBMAINTAINER.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new MaintainerBuilder(BOBMAINTAINER).withName(nameWithTrailingSpaces).build();
        assertFalse(BOBMAINTAINER.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new MaintainerBuilder(ALICEMAINTAINER).build();
        assertTrue(ALICEMAINTAINER.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICEMAINTAINER.equals(ALICEMAINTAINER));

        // null -> returns false
        assertFalse(ALICEMAINTAINER.equals(null));

        // different type -> returns false
        assertFalse(ALICEMAINTAINER.equals(5));

        // different person -> returns false
        assertFalse(ALICEMAINTAINER.equals(BOBMAINTAINER));

        // different name -> returns false
        Person editedAlice = new MaintainerBuilder(ALICEMAINTAINER).withName(VALID_NAME_BOB).build();
        assertFalse(ALICEMAINTAINER.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new MaintainerBuilder(ALICEMAINTAINER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICEMAINTAINER.equals(editedAlice));

        // different email -> returns false
        editedAlice = new MaintainerBuilder(ALICEMAINTAINER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICEMAINTAINER.equals(editedAlice));

        // different address -> returns false
        editedAlice = new MaintainerBuilder(ALICEMAINTAINER).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICEMAINTAINER.equals(editedAlice));

        /*
        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
         */
    }

    @Test
    public void toStringMethod() {
        String expected = Maintainer.class.getCanonicalName() + "{name=" + ALICEMAINTAINER.getName()
                + ", phone="
                + ALICEMAINTAINER.getPhone()
                + ", email=" + ALICEMAINTAINER.getEmail() + ", address="
                + ALICEMAINTAINER.getAddress()
                + ", tags=" + ALICEMAINTAINER.getTags()
                + ", skill=" + ALICEMAINTAINER.getSkill()
                + ", commission=" + ALICEMAINTAINER.getCommission()
                + "}";
        assertEquals(expected, ALICEMAINTAINER.toString());
    }
}
