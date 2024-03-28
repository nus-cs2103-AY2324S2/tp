package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_LIFE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.policy.Policy;
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
                .withAddress(VALID_ADDRESS_BOB).withBirthday(VALID_BIRTHDAY_BOB).withPriority(VALID_PRIORITY_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
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

        // different birthday -> returns false
        editedAlice = new PersonBuilder(ALICE).withBirthday(VALID_BIRTHDAY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different priority -> returns false
        editedAlice = new PersonBuilder(ALICE).withPriority(VALID_PRIORITY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        //differnt policies -> return false
        editedAlice = new PersonBuilder(ALICE).withPolicies(VALID_POLICY_LIFE).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void isConflictingPolicyID() {
        // same policy id -> returns true
        assertTrue(BENSON.isConflictingPolicyId(new Policy("Health", "123")));

        // different policy id -> returns false
        assertFalse(BENSON.isConflictingPolicyId(new Policy("Saving", "789")));
    }

    @Test
    public void hasPolicyID() {
        // has policy id -> returns true
        assertTrue(BENSON.hasPolicyID("123"));

        // does not have policy id -> returns false
        assertFalse(BENSON.hasPolicyID("789"));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress()
                + ", birthday=" + ALICE.getBirthday() + ", priority=" + ALICE.getPriority()
                + ", tags=" + ALICE.getTags() + ", policies="
                + ALICE.getPolicyList() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void isOverDueLastMet() {
        Person dueAlice = new PersonBuilder(ALICE).withLastMet(LocalDate.of(2000, 1, 1)).build();
        assertTrue(dueAlice.isOverDueLastMet());
        Person noDueAlice = new PersonBuilder(ALICE).withLastMet(LocalDate.now()).build();
        assertFalse(noDueAlice.isOverDueLastMet());
        Person scheduledAlice = new PersonBuilder(ALICE).withSchedule(LocalDateTime.now().plusDays(1), false)
                .withLastMet(LocalDate.of(2000, 1, 1)).build();
        assertFalse(scheduledAlice.isOverDueLastMet());
    }

    @Test
    public void hasActiveAppointments() {
        assertFalse(ALICE.hasActiveSchedule());
        Person scheduleAlice = new PersonBuilder(ALICE).withSchedule(LocalDateTime.now().plusDays(1), false)
                .build();
        assertTrue(scheduleAlice.hasActiveSchedule());
    }

    @Test
    public void overdueLastMetStringFormat() {
        assertEquals(ALICE.overdueLastMetStringFormat(),
                ALICE.getName() + " - " + Long.toString(ALICE.getLastMet().getPeriodGap()) + "d ago");
    }

    @Test
    public void scheduleStringFormat() {
        Person notMissedAlice = new PersonBuilder(ALICE).withSchedule(LocalDateTime.now().plusDays(1), false)
                .build();
        assertEquals(notMissedAlice.getSchedule().getScheduleDateString() + " - " + ALICE.getName(),
                notMissedAlice.scheduleStringFormat());
        Person missedAlice = new PersonBuilder(ALICE).withSchedule(LocalDateTime.now().minusDays(1), false)
                .build();
        assertEquals(missedAlice.getSchedule().getScheduleDateString() + " - " + ALICE.getName() + " (Missed)",
                missedAlice.scheduleStringFormat());
    }
}
