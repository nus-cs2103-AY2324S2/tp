package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.attendance.Week;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.checkDuplicateField(ALICE));

        // null -> returns false
        assertFalse(ALICE.checkDuplicateField(null));

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withClassGroup(VALID_CLASS_GROUP_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withGithub(VALID_GITHUB_BOB).build();
        assertFalse(ALICE.checkDuplicateField(editedAlice));
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

        // different attendance -> returns false
        editedAlice = new PersonBuilder(ALICE).build();
        editedAlice.markAbsent(new Week(Index.fromOneBased(1)));
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void markAbsent() {
        Person aliceCopy = new PersonBuilder(ALICE).build();
        aliceCopy.markAbsent(new Week(Index.fromOneBased(1)));
        assertTrue(aliceCopy.isAbsent(new Week(Index.fromOneBased(1))));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName()
                + "{name=" + ALICE.getName() + ", classGroup=" + ALICE.getClassGroup()
                + ", email=" + ALICE.getEmail() + ", phone="
                + (ALICE.getPhone().isPresent() ? ALICE.getPhone().get().value : "") + ", telegram="
                + (ALICE.getTelegram().isPresent() ? ALICE.getTelegram().get().telegramId : "") + ", github="
                + (ALICE.getGithub().isPresent() ? ALICE.getGithub().get().githubId : "") + ", attendance="
                + (ALICE.getAttendance().toString()) + "}";
        assertEquals(expected, ALICE.toString());
    }
}
