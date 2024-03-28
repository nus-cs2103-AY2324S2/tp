package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BROWN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BROWN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BROWN;
import static seedu.address.testutil.TypicalPersons.DAMES;
import static seedu.address.testutil.TypicalPersons.FROWN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;

public class DoctorTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(DAMES.isSamePerson(DAMES));

        // null -> returns false
        assertFalse(DAMES.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Doctor editedDames = new DoctorBuilder(DAMES).withName(VALID_NAME_BROWN).withDoB(VALID_DOB_BROWN)
                .withPhone(VALID_PHONE_BROWN).build();
        assertTrue(DAMES.isSamePerson(editedDames));

        // different name, all other attributes same -> returns true
        editedDames = new DoctorBuilder(DAMES).withName(VALID_NAME_BROWN).build();
        assertTrue(DAMES.isSamePerson(editedDames));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new DoctorBuilder(DAMES).withName(VALID_NAME_BROWN.toLowerCase()).build();
        assertTrue(DAMES.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BROWN + " ";
        editedBob = new DoctorBuilder(DAMES).withName(nameWithTrailingSpaces).build();
        assertTrue(DAMES.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new DoctorBuilder(DAMES).build();
        assertTrue(DAMES.equals(aliceCopy));

        // same object -> returns true
        assertTrue(DAMES.equals(DAMES));

        // null -> returns false
        assertFalse(DAMES.equals(null));

        // different type -> returns false
        assertFalse(DAMES.equals(5));

        // different person -> returns false
        assertFalse(DAMES.equals(FROWN));

        // different nric -> returns false
        Person editedAlice = new DoctorBuilder(DAMES).withNric(VALID_NRIC_BOB).build();
        assertFalse(DAMES.equals(editedAlice));

        // different name -> returns false
        editedAlice = new DoctorBuilder(DAMES).withName(VALID_NAME_BOB).build();
        assertFalse(DAMES.equals(editedAlice));

        // different dob -> returns false
        editedAlice = new DoctorBuilder(DAMES).withDoB(VALID_DOB_BOB).build();
        assertFalse(DAMES.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DoctorBuilder(DAMES).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DAMES.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Doctor.class.getCanonicalName() + "{type=" + DAMES.getType() + ", nric=" + DAMES.getNric()
                + ", name=" + DAMES.getName() + ", dob=" + DAMES.getDoB() + ", phone=" + DAMES.getPhone() + "}";
        assertEquals(expected, DAMES.toString());
    }
}
