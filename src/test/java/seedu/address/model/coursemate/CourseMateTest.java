package seedu.address.model.coursemate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CourseMateBuilder;

public class CourseMateTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        CourseMate courseMate = new CourseMateBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> courseMate.getSkills().remove(0));
    }

    @Test
    public void isSameCourseMate() {
        // same object -> returns true
        assertTrue(ALICE.isSameCourseMate(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameCourseMate(null));

        // same name, all other attributes different -> returns true
        CourseMate editedAlice = new CourseMateBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_JAVA).build();
        assertTrue(ALICE.isSameCourseMate(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CourseMateBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameCourseMate(editedAlice));

        // name differs in case, all other attributes same -> returns false
        CourseMate editedBob = new CourseMateBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameCourseMate(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CourseMateBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameCourseMate(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        CourseMate aliceCopy = new CourseMateBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different courseMate -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        CourseMate editedAlice = new CourseMateBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CourseMateBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CourseMateBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CourseMateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different skills -> returns false
        editedAlice = new CourseMateBuilder(ALICE).withSkills(VALID_SKILL_JAVA).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = CourseMate.class.getCanonicalName()
                + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress()
                + ", skills=" + ALICE.getSkills() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
