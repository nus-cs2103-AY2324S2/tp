package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_BOB_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.realodex.testutil.Assert.assertThrows;
import static seedu.realodex.testutil.TypicalPersons.ALICE;
import static seedu.realodex.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.realodex.testutil.PersonBuilder;

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
        Person editedAlice = new PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withIncome(VALID_INCOME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withFamily(VALID_FAMILY_BOB)
                .withTags(VALID_TAG_AMY)
                .withRemark(VALID_REMARK_AMY)
                .build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB_FIRST_LETTER_CAPS).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB_FIRST_LETTER_CAPS.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB_FIRST_LETTER_CAPS + " ";
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
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB_FIRST_LETTER_CAPS).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        //different income -> returns false
        editedAlice = new PersonBuilder(ALICE).withIncome(VALID_INCOME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different family size -> returns false
        editedAlice = new PersonBuilder(ALICE).withIncome(VALID_FAMILY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags("buyer").withTags("seller").build();
        assertFalse(ALICE.equals(editedAlice));

        // different remarks -> returns false
        editedAlice = new PersonBuilder(ALICE).withRemark(VALID_REMARK_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", income=" + ALICE.getIncome()
                + ", email=" + ALICE.getEmail()
                + ", address=" + ALICE.getAddress()
                + ", family=" + ALICE.getFamily()
                + ", tags=" + ALICE.getTags()
                + ", remark=" + ALICE.getRemark() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
