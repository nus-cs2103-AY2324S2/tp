package seedu.internhub.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_TAG_INTERVIEW;
import static seedu.internhub.testutil.TypicalPersons.ALICE;
import static seedu.internhub.testutil.TypicalPersons.BOB;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.internhub.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_INTERVIEW).build();
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

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_INTERVIEW).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getCompanyName()
                + ", phone=" + ALICE.getPhone() + ", email=" + ALICE.getEmail()
                + ", address=" + ALICE.getAddress() + ", tag=" + ALICE.getTag()
                + ", job description=" + ALICE.getJobDescription()
                + ", interview date=" + ALICE.getInterviewDate()
                + ", intern duration=" + ALICE.getInternDuration()
                + ", salary=" + ALICE.getSalary()
                + ", note=" + ALICE.getNote() + "}";
        assertEquals(expected, ALICE.toString());
    }
    @Test
    public void compareTo_differentInterviewDates() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        // Dates
        String pastDate = LocalDateTime.now().minusDays(1).format(format);
        String pastDateMinusOne = LocalDateTime.now().minusDays(2).format(format);
        String futureDate = LocalDateTime.now().plusDays(1).format(format);
        String futureDatePlusOne = LocalDateTime.now().plusDays(2).format(format);
        // Persons
        Person personWithPastDate = new PersonBuilder().withInterviewDate(pastDate).build();
        Person personWithPastDateMinusOne = new PersonBuilder().withInterviewDate(pastDateMinusOne).build();
        Person personWithFutureDate = new PersonBuilder().withInterviewDate(futureDate).build();
        Person personWithFutureDatePlusOne = new PersonBuilder().withInterviewDate(futureDatePlusOne).build();
        Person personWithNoDate = new PersonBuilder().build();
        // this is past but other is future
        assertEquals(1, personWithPastDate.compareTo(personWithFutureDate));
        // The other is past but this is future
        assertEquals(-1, personWithFutureDate.compareTo(personWithPastDate));
        // The other is later than this (both are in the future)
        assertEquals(1, personWithFutureDatePlusOne.compareTo(personWithFutureDate));
        // The other is earlier than this (both are in the future)
        assertEquals(-1, personWithFutureDate.compareTo(personWithFutureDatePlusOne));
        // Both are past dates
        assertEquals(0, personWithPastDate.compareTo(personWithPastDateMinusOne));
        // this has no date, the other has a date in the future
        assertEquals(1, personWithNoDate.compareTo(personWithFutureDate));
    }
}
