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

public class AppointmentContainsDoctorPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AppointmentContainsDoctorPredicate firstPredicate =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);
        AppointmentContainsDoctorPredicate secondPredicate =
                new AppointmentContainsDoctorPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentContainsDoctorPredicate firstPredicateCopy =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);
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
        List<String> firstPredicateKeywordList = Collections.singletonList("S2378593A");
        AppointmentContainsDoctorPredicate firstPredicate =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);

        // Object Appointment -> returns True
        Appointment p = APPOINTMENT_1;
        assertTrue(firstPredicate.test(p));
    }

    @Test
    public void test_appointmentContainsKeywords_returnsTrue() {
        List<String> firstPredicateKeywordList = Collections.singletonList("S2378593A");
        AppointmentContainsDoctorPredicate firstPredicate =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);

        // Object Appointment with Brown -> returns True
        Appointment p = APPOINTMENT_1;
        assertTrue(firstPredicate.test(p));

        // Object Appointment without Brown -> returns False
        Appointment p1 = APPOINTMENT_4;
        assertFalse(firstPredicate.test(p1));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        List<String> firstPredicateKeywordList = Collections.singletonList("S1111111A");
        AppointmentContainsDoctorPredicate firstPredicate =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);

        // Object Appointment with Brown -> returns True
        Appointment p = APPOINTMENT_1;
        assertFalse(firstPredicate.test(p));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        AppointmentContainsDoctorPredicate predicate = new AppointmentContainsDoctorPredicate(keywords);

        String expected = AppointmentContainsDoctorPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
