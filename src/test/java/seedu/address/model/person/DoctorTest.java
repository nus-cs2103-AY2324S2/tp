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
import static seedu.address.testutil.TypicalPersons.BROWN;
import static seedu.address.testutil.TypicalPersons.JAMES;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;

public class DoctorTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(JAMES.isSamePerson(JAMES));

        // null -> returns false
        assertFalse(JAMES.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Doctor editedJames = new DoctorBuilder(JAMES).withName(VALID_NAME_BROWN).withDoB(VALID_DOB_BROWN)
                .withPhone(VALID_PHONE_BROWN).build();
        assertTrue(JAMES.isSamePerson(editedJames));

        // different name, all other attributes same -> returns true
        editedJames = new DoctorBuilder(JAMES).withName(VALID_NAME_BROWN).build();
        assertTrue(JAMES.isSamePerson(editedJames));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new DoctorBuilder(JAMES).withName(VALID_NAME_BROWN.toLowerCase()).build();
        assertTrue(JAMES.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BROWN + " ";
        editedBob = new DoctorBuilder(JAMES).withName(nameWithTrailingSpaces).build();
        assertTrue(JAMES.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new DoctorBuilder(JAMES).build();
        assertTrue(JAMES.equals(aliceCopy));

        // same object -> returns true
        assertTrue(JAMES.equals(JAMES));

        // null -> returns false
        assertFalse(JAMES.equals(null));

        // different type -> returns false
        assertFalse(JAMES.equals(5));

        // different person -> returns false
        assertFalse(JAMES.equals(BROWN));

        // different nric -> returns false
        Person editedAlice = new DoctorBuilder(JAMES).withNric(VALID_NRIC_BOB).build();
        assertFalse(JAMES.equals(editedAlice));

        // different name -> returns false
        editedAlice = new DoctorBuilder(JAMES).withName(VALID_NAME_BOB).build();
        assertFalse(JAMES.equals(editedAlice));

        // different dob -> returns false
        editedAlice = new DoctorBuilder(JAMES).withDoB(VALID_DOB_BOB).build();
        assertFalse(JAMES.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DoctorBuilder(JAMES).withPhone(VALID_PHONE_BOB).build();
        assertFalse(JAMES.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Doctor.class.getCanonicalName() + "{type=" + JAMES.getType() + ", nric=" + JAMES.getNric()
                + ", name=" + JAMES.getName() + ", dob=" + JAMES.getDoB() + ", phone=" + JAMES.getPhone() + "}";
        assertEquals(expected, JAMES.toString());
    }
}
