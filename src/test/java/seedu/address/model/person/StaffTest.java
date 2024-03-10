package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.BOBSTAFF;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StaffBuilder;

public class StaffTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Staff person = new StaffBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameStaff() {
        // same object -> returns true
        assertTrue(ALICESTAFF.isSamePerson(ALICESTAFF));

        // null -> returns false
        assertFalse(ALICESTAFF.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new StaffBuilder(ALICESTAFF).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StaffBuilder(ALICESTAFF).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new StaffBuilder(BOBSTAFF).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOBSTAFF.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StaffBuilder(BOBSTAFF).withName(nameWithTrailingSpaces).build();
        assertFalse(BOBSTAFF.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new StaffBuilder(ALICESTAFF).build();
        assertTrue(ALICESTAFF.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICESTAFF.equals(ALICESTAFF));

        // null -> returns false
        assertFalse(ALICESTAFF.equals(null));

        // different type -> returns false
        assertFalse(ALICESTAFF.equals(5));

        // different person -> returns false
        assertFalse(ALICESTAFF.equals(BOBSTAFF));

        // different name -> returns false
        Person editedAlice = new StaffBuilder(ALICESTAFF).withName(VALID_NAME_BOB).build();
        assertFalse(ALICESTAFF.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StaffBuilder(ALICESTAFF).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICESTAFF.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StaffBuilder(ALICESTAFF).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICESTAFF.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StaffBuilder(ALICESTAFF).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICESTAFF.equals(editedAlice));

        /*
        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
         */
    }

    @Test
    public void toStringMethod() {
        String expected = Staff.class.getCanonicalName() + "{name=" + ALICESTAFF.getName() + ", phone="
                + ALICESTAFF.getPhone()
                + ", email=" + ALICESTAFF.getEmail() + ", address="
                + ALICESTAFF.getAddress()
                + ", tags=" + ALICESTAFF.getTags()
                + ", salary=" + ALICESTAFF.getSalary()
                + ", employment=" + ALICESTAFF.getEmployment()
                + "}";
        assertEquals(expected, ALICESTAFF.toString());
    }
}
