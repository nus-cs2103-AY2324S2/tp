package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOMNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withRoomNumber(VALID_ROOMNUMBER_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withBirthday(VALID_BIRTHDAY_BOB).build();
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

        // different room number -> returns false
        editedAlice = new PersonBuilder(ALICE).withRoomNumber(VALID_ROOMNUMBER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void equals_someOptionalFieldsAreNull_success() {
        Person aliceCopyWithoutEmail = new Person(
                ALICE.getName(),
                ALICE.getPhone(),
                null,
                ALICE.getRoomNumber(),
                ALICE.getTelegram(),
                ALICE.getBirthday(),
                ALICE.getTags());
        assertFalse(ALICE.equals(aliceCopyWithoutEmail));
        assertFalse(aliceCopyWithoutEmail.equals(ALICE));

        Person aliceCopyWithoutRoomNumber = new Person(
                ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                null,
                ALICE.getTelegram(),
                ALICE.getBirthday(),
                ALICE.getTags());
        assertFalse(ALICE.equals(aliceCopyWithoutRoomNumber));
        assertFalse(aliceCopyWithoutRoomNumber.equals(ALICE));

        Person aliceCopyWithoutTelegram = new Person(
                ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getRoomNumber(),
                null,
                ALICE.getBirthday(),
                ALICE.getTags());
        assertFalse(ALICE.equals(aliceCopyWithoutTelegram));
        assertFalse(aliceCopyWithoutTelegram.equals(ALICE));

        Person aliceCopyWithoutBirthday = new Person(
                ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getRoomNumber(),
                ALICE.getTelegram(),
                null,
                ALICE.getTags());
        assertFalse(ALICE.equals(aliceCopyWithoutBirthday));
        assertFalse(aliceCopyWithoutBirthday.equals(ALICE));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", tags=[[Mon:1000-1400]]" + ", email=" + ALICE.getEmail() + ", roomNumber=" + ALICE.getRoomNumber()
                + ", telegram=" + ALICE.getTelegram() + ", birthday=" + ALICE.getBirthday() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
