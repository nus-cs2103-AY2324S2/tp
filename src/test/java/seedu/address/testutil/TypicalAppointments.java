package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment UNATTENDED_FIRST_APPOINTMENT = new AppointmentBuilder().withAppointmentId(1)
            .withAppointmentDateTime(LocalDateTime.of(2022, 12, 12, 12, 12))
            .withStudentId(1)
            .withAppointmentDescription("First Appointment")
            .withHasAttended(false).build();

    public static final Appointment ATTENDED_FIRST_APPOINTMENT = new AppointmentBuilder().withAppointmentId(2)
            .withAppointmentDateTime(LocalDateTime.of(2022, 12, 12, 12, 12))
            .withStudentId(2)
            .withAppointmentDescription("First Appointment")
            .withHasAttended(true).build();

    public static final Appointment ATTENDED_SECOND_APPOINTMENT = new AppointmentBuilder().withAppointmentId(2)
            .withAppointmentDateTime(LocalDateTime.of(2022, 12, 12, 12, 12))
            .withStudentId(2)
            .withAppointmentDescription("First Appointment")
            .withHasAttended(false).build();


}
