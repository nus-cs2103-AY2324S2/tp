package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPT1 = new AppointmentBuilder()
            .withId("a1577f3b-c8f6-40f3-b0df-e6af13118951")
            .withPersonId("c7cc471f-32b4-4ba5-880a-3cf25ba855a6")
            .withAppointmentTime(new AppointmentTime("10/02/2024 11am-2pm")).build();
    public static final Appointment APPT2 = new AppointmentBuilder()
            .withId("0fc8e682-4048-4c8d-9108-0f275132aad7")
            .withPersonId("d36fee3a-03ea-4ad9-af5a-e6b1e76f6251")
            .withAppointmentTime(new AppointmentTime("11/02/2024 11am-2pm")).build();
    public static final Appointment APPT3 = new AppointmentBuilder()
            .withId("d08cd77c-19d8-4c7a-8a28-aedf7a4f3c80")
            .withPersonId("60eb3b8c-247d-4dcd-b71c-9ecf9dd52237")
            .withAppointmentTime(new AppointmentTime("12/02/2024 11am-2pm")).build();

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
        return new ArrayList<>(Arrays.asList(APPT1, APPT2, APPT3));
    }
}
