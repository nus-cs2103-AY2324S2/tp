package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BROWN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * A utility class containing a list of {@code Appointments } objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().withDate("2124-03-19")
            .withDoctor((Doctor) BROWN).withPatient((Patient) ALICE).build();
    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withDate("2124-03-20")
            .withDoctor((Doctor) BROWN).withPatient((Patient) ALICE).build();
    public static final Appointment APPOINTMENT_3 = new AppointmentBuilder().withDate("2124-03-25")
            .withDoctor((Doctor) BROWN).withPatient((Patient) ALICE).build();
    public static final Appointment APPOINTMENT_4 = new AppointmentBuilder().withDate("2124-03-30")
            .withDoctor((Doctor) BROWN).withPatient((Patient) BENSON).build();
    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical appointments.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(
                APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_4
        ));
    }
}
