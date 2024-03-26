package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.testutil.TypicalAppointmentViews.ALICE_APPT_VIEW;
import static seedu.address.testutil.TypicalAppointmentViews.AMY_APPT_VIEW;
import static seedu.address.testutil.TypicalAppointmentViews.BOB_APPT_VIEW;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.AppointmentViewBuilder;

public class AppointmentContainsKeywordsPredicateTest {

    @Test
    public void test_nricFilterOnly_matchesNric() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.of(new Nric(VALID_NRIC_AMY)),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(AMY_APPT_VIEW));
        assertFalse(predicate.test(BOB_APPT_VIEW));
    }

    @Test
    public void test_dateFilterOnly_matchesDate() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.empty(),
                        Optional.of(new Date(VALID_APPOINTMENT_DATE_AMY)), Optional.empty());

        assertTrue(predicate.test(AMY_APPT_VIEW));
        assertFalse(predicate.test(BOB_APPT_VIEW));
    }

    @Test
    public void test_timeFilterOnly_matchesGreaterThanOrEqualTime() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.empty(), Optional.empty(),
                        Optional.of(new Time(VALID_APPOINTMENT_START_TIME_BOB)));

        assertTrue(predicate.test(BOB_APPT_VIEW)); // 15:00
        assertTrue(predicate.test(ALICE_APPT_VIEW)); // 16:00
    }

    @Test
    public void test_timeFilterOnly_matchesLessThanTime() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.empty(), Optional.empty(),
                        Optional.of(new Time(VALID_APPOINTMENT_START_TIME_BOB)));

        assertTrue(predicate.test(BOB_APPT_VIEW)); // 15:00
        assertFalse(predicate.test(AMY_APPT_VIEW)); // 11:00
    }

    @Test
    public void test_nricAndDateFilter_matchesNricAndDate() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.of(new Nric(VALID_NRIC_AMY)),
                        Optional.of(new Date(VALID_APPOINTMENT_DATE_AMY)), Optional.empty());

        assertTrue(predicate.test(AMY_APPT_VIEW));
        // Test for same date and same NRIC but different time
        assertTrue(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_AMY)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB).withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));

        assertFalse(predicate.test(BOB_APPT_VIEW));
        // Test for differing dates but same NRIC
        assertFalse(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME_AMY).withEndTime(VALID_APPOINTMENT_END_TIME_AMY)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));
        // Test for differing NRIC but same date
        assertFalse(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_BOB).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_BOB).withDate(VALID_APPOINTMENT_DATE_AMY)
                .withStartTime(VALID_APPOINTMENT_START_TIME_AMY).withEndTime(VALID_APPOINTMENT_END_TIME_AMY)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));
    }

    @Test
    public void test_matchesDateAndTime() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.empty(),
                        Optional.of(new Date(VALID_APPOINTMENT_DATE_AMY)),
                        Optional.of(new Time(VALID_APPOINTMENT_START_TIME_AMY)));

        assertTrue(predicate.test(AMY_APPT_VIEW));
        // Test for same date and time but different NRIC
        assertTrue(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_BOB).withDate(VALID_APPOINTMENT_DATE_AMY)
                .withStartTime(VALID_APPOINTMENT_START_TIME_AMY).withEndTime(VALID_APPOINTMENT_END_TIME_AMY)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));
        // Test for same date but different time (but time >= filter time)
        assertTrue(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_AMY)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB).withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));

        assertFalse(predicate.test(BOB_APPT_VIEW));
        // Test for different date and time but same NRIC
        assertFalse(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB).withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));

        // Test for different date or time but same NRIC
        assertFalse(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME_AMY).withEndTime(VALID_APPOINTMENT_END_TIME_AMY)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));
    }

    @Test
    public void test_matchesNricAndTime() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.of(new Nric(VALID_NRIC_AMY)),
                        Optional.empty(), Optional.of(new Time(VALID_APPOINTMENT_START_TIME_AMY)));

        assertTrue(predicate.test(AMY_APPT_VIEW));
        // Test for same NRIC and time but different date
        assertTrue(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME_AMY).withEndTime(VALID_APPOINTMENT_END_TIME_AMY)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));

        assertFalse(predicate.test(BOB_APPT_VIEW));
        // Test for different NRIC but same date and time
        assertFalse(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_BOB).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_BOB).withDate(VALID_APPOINTMENT_DATE_AMY)
                .withStartTime(VALID_APPOINTMENT_START_TIME_AMY).withEndTime(VALID_APPOINTMENT_END_TIME_AMY)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));

        // Test for different NRIC, date, and time
        assertFalse(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_BOB).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_BOB).withDate(VALID_APPOINTMENT_DATE_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB).withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));
    }

    @Test
    public void test_matchesNricDateTime() {
        AppointmentContainsKeywordsPredicate predicate =
                new AppointmentContainsKeywordsPredicate(Optional.of(new Nric(VALID_NRIC_AMY)),
                        Optional.of(new Date(VALID_APPOINTMENT_DATE_AMY)),
                        Optional.of(new Time(VALID_APPOINTMENT_START_TIME_AMY)));

        assertTrue(predicate.test(AMY_APPT_VIEW));
        // Test for same NRIC and date but different time (time >= filterTime)
        assertTrue(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_AMY)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB).withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));

        // Test for same NRIC but different date and time
        assertFalse(predicate.test(new AppointmentViewBuilder()
                .withName(VALID_NAME_AMY).withAppointment(new AppointmentBuilder()
                .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB).withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
                .withNote(VALID_APPOINTMENT_NOTE_AMY).build()).build()));

        // Test for different NRIC, date, and time
        assertFalse(predicate.test(BOB_APPT_VIEW));
    }

    @Test
    public void testEquals_sameInstance_returnsTrue() {
        AppointmentContainsKeywordsPredicate predicate = new AppointmentContainsKeywordsPredicate(
                Optional.of(new Nric("S1234567A")),
                Optional.of(new Date("2024-12-31")),
                Optional.of(new Time("10:00"))
        );
        assertTrue(predicate.equals(predicate)); // Same instance
    }

    @Test
    public void testEquals_equalInstances_returnsTrue() {
        AppointmentContainsKeywordsPredicate predicate1 = new AppointmentContainsKeywordsPredicate(
                Optional.of(new Nric("S1234567A")),
                Optional.of(new Date("2024-12-31")),
                Optional.of(new Time("10:00"))
        );
        AppointmentContainsKeywordsPredicate predicate2 = new AppointmentContainsKeywordsPredicate(
                Optional.of(new Nric("S1234567A")),
                Optional.of(new Date("2024-12-31")),
                Optional.of(new Time("10:00"))
        );
        assertTrue(predicate1.equals(predicate2)); // Equal instances
    }

    @Test
    public void testEquals_differentInstances_returnsFalse() {
        AppointmentContainsKeywordsPredicate predicate1 = new AppointmentContainsKeywordsPredicate(
                Optional.of(new Nric("S1234567A")),
                Optional.of(new Date("2024-12-31")),
                Optional.of(new Time("10:00"))
        );
        AppointmentContainsKeywordsPredicate predicate2 = new AppointmentContainsKeywordsPredicate(
                Optional.of(new Nric("T0123456A")),
                Optional.of(new Date("2024-12-30")),
                Optional.of(new Time("09:00"))
        );
        assertFalse(predicate1.equals(predicate2)); // Different instances
    }
}
