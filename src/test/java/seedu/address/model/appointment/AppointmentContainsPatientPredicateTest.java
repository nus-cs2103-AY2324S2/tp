package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AppointmentContainsPatientPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AppointmentContainsPatientPredicate firstPredicate =
                new AppointmentContainsPatientPredicate(firstPredicateKeywordList);
        AppointmentContainsPatientPredicate secondPredicate =
                new AppointmentContainsPatientPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentContainsPatientPredicate firstPredicateCopy =
                new AppointmentContainsPatientPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_predicateReturnsAppointment() {
        List<String> firstPredicateKeywordList = Collections.singletonList("S1234567A");
        AppointmentContainsPatientPredicate firstPredicate =
                new AppointmentContainsPatientPredicate(firstPredicateKeywordList);

        // Object Appointment -> returns True
        Appointment p = APPOINTMENT_1;
        assertTrue(firstPredicate.test(p));
    }

    @Test
    public void test_appointmentContainsKeywords_returnsTrue() {
        List<String> firstPredicateKeywordList = Collections.singletonList("S1234567A");
        AppointmentContainsPatientPredicate firstPredicate =
                new AppointmentContainsPatientPredicate(firstPredicateKeywordList);

        // Object Appointment with Alice -> returns True
        Appointment p = APPOINTMENT_1;
        assertTrue(firstPredicate.test(p));

        // Object Appointment without Alice -> returns False
        Appointment p1 = APPOINTMENT_4;
        assertFalse(firstPredicate.test(p1));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        List<String> firstPredicateKeywordList = Collections.singletonList("S1111111A");
        AppointmentContainsPatientPredicate firstPredicate =
                new AppointmentContainsPatientPredicate(firstPredicateKeywordList);

        // Object Appointment with Alice -> returns True
        Appointment p = APPOINTMENT_1;
        assertFalse(firstPredicate.test(p));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        AppointmentContainsPatientPredicate predicate = new AppointmentContainsPatientPredicate(keywords);

        String expected = AppointmentContainsPatientPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
