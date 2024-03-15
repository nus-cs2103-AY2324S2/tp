package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 * Appointment NRICs match those in {@code TypicalPersons}.
 */
public class TypicalAppointments {

    public static final Appointment ALICE_APPT = new AppointmentBuilder()
            .withNric("T0123456A").withDate("2024-03-01")
            .withStartTime("16:00").withEndTime("17:00")
            .withAppointmentType("Monthly check-up")
            .withNote("Routine follow up").build();
    public static final Appointment ALICE_APPT_1 = new AppointmentBuilder()
            .withNric("T0123456A").withDate("2024-04-01")
            .withStartTime("16:00").withEndTime("17:00")
            .withAppointmentType("Monthly check-up")
            .withNote("Routine follow up").build();
    public static final Appointment BENSON_APPT = new AppointmentBuilder()
            .withNric("T0123456B").withDate("2024-03-02")
            .withStartTime("12:00").withEndTime("12:30")
            .withAppointmentType("X-Ray")
            .withNote("Fractured finger").build();

    // Manually added - Appointment's details found in {@code CommandTestUtil}
    public static final Appointment AMY_APPT = new AppointmentBuilder()
            .withNric(VALID_NRIC_AMY).withDate(VALID_APPOINTMENT_DATE_AMY)
            .withStartTime(VALID_APPOINTMENT_START_TIME_AMY).withEndTime(VALID_APPOINTMENT_END_TIME_AMY)
            .withAppointmentType(VALID_APPOINTMENT_TYPE_AMY)
            .withNote(VALID_APPOINTMENT_NOTE_AMY).build();
    public static final Appointment BOB_APPT = new AppointmentBuilder()
            .withNric(VALID_NRIC_BOB).withDate(VALID_APPOINTMENT_DATE_BOB)
            .withStartTime(VALID_APPOINTMENT_START_TIME_BOB).withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
            .withAppointmentType(VALID_APPOINTMENT_TYPE_BOB)
            .withNote(VALID_APPOINTMENT_NOTE_BOB).build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical
     * persons and appointments
     */
    public static AddressBook getTypicalAddressBookWithAppointments() {
        AddressBook ab = getTypicalAddressBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICE_APPT, ALICE_APPT_1, BENSON_APPT));
    }
}
