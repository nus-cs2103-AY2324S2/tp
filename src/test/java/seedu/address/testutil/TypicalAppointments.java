package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.person.Person;

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
            .withAppointmentDescription("Second Appointment")
            .withHasAttended(false).build();

    public static final Appointment THIRD_APPOINTMENT = new AppointmentBuilder().withAppointmentId(3)
            .withAppointmentDateTime(LocalDateTime.of(2022, 12, 12, 12, 12))
            .withStudentId(3)
            .withAppointmentDescription("Third Appointment")
            .withHasAttended(false).build();


    public static final Appointment FORTH_APPOINTMENT = new AppointmentBuilder().withAppointmentId(4)
            .withAppointmentDateTime(LocalDateTime.of(2022, 12, 12, 12, 12))
            .withStudentId(3)
            .withAppointmentDescription("Second Appointment")
            .withHasAttended(false).build();

    /**
     * Returns an {@code AppointmentList} with all the typical persons.
     */
    public static AppointmentList getTypicalAppointmentList() {
        AppointmentList appointmentList = new AppointmentList();
        for (Appointment appointment : getTypicalAppointments()) {
            appointmentList.addAppointment(appointment);
        }
        return appointmentList;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(UNATTENDED_FIRST_APPOINTMENT, ATTENDED_SECOND_APPOINTMENT,
                THIRD_APPOINTMENT, FORTH_APPOINTMENT));
    }
}
