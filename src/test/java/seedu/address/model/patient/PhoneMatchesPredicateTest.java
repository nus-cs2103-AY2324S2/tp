package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PhoneMatchesPredicateTest {

    @Test
    public void equals() {
        String firstPhoneString = "99999999";
        Phone firstPhone = new Phone(firstPhoneString);

        String secondPhoneString = "88888888";
        Phone secondPhone = new Phone(secondPhoneString);

        PhoneMatchesPredicate firstPredicate = new PhoneMatchesPredicate(firstPhone);
        PhoneMatchesPredicate secondPredicate = new PhoneMatchesPredicate(secondPhone);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        String firstPhoneStringCopy = "99999999";
        Phone firstPhoneCopy = new Phone(firstPhoneStringCopy);
        PhoneMatchesPredicate firstPredicateCopy = new PhoneMatchesPredicate(firstPhoneCopy);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneMatches_returnsTrue() {
        String phoneNumber = "98765432";
        PhoneMatchesPredicate predicate = new PhoneMatchesPredicate(new Phone(phoneNumber));
        assertTrue(predicate.test(new PatientBuilder().withPhone(phoneNumber).build()));
    }

    @Test
    public void test_phoneDoesNotMatch_returnsFalse() {
        // Null check
        String phoneNumber = "98765432";
        PhoneMatchesPredicate predicate = new PhoneMatchesPredicate(new Phone(phoneNumber));
        assertFalse(predicate.test(null));

        // Non-matching phones
        assertFalse(predicate.test(new PatientBuilder().withPhone("99999999").build()));
    }

    @Test
    public void toStringMethod() {
        Phone phone = new Phone("99999999");
        PhoneMatchesPredicate predicate = new PhoneMatchesPredicate(phone);

        String expected = PhoneMatchesPredicate.class.getCanonicalName() + "{phone=" + phone + "}";
        assertEquals(expected, predicate.toString());
    }
}
