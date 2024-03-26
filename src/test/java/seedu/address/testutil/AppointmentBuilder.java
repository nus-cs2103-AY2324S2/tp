package seedu.address.testutil;

import java.util.UUID;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final UUID DEFAULT_ID = UUID.fromString("a1577f3b-c8f6-40f3-b0df-e6af13118951");
    public static final UUID DEFAULT_PERSONID = UUID.fromString("c7cc471f-32b4-4ba5-880a-3cf25ba855a6");
    public static final AppointmentTime DEFAULT_APPOINTMENTTIME = new AppointmentTime("10/02/2024 11am-2pm");

    private UUID id;
    private UUID personId;
    private AppointmentTime appointmentTime;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        id = DEFAULT_ID;
        personId = DEFAULT_PERSONID;
        appointmentTime = DEFAULT_APPOINTMENTTIME;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        id = appointmentToCopy.getId();
        personId = appointmentToCopy.getPersonId();
        appointmentTime = appointmentToCopy.getAppointmentTime();

    }

    /**
     * Sets the {@code id} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withId(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    /**
     * Sets the {@code personId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPersonId(String personId) {
        this.personId = UUID.fromString(personId);
        return this;
    }

    /**
     * Sets the {@code appointmentTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentTime(AppointmentTime appointmentTime) {
        this.appointmentTime = appointmentTime;
        return this;
    }

    /**
     * Builds an appointment object
     * @return an appointment object
     */
    public Appointment build() {
        return new Appointment(id, personId, appointmentTime);
    }
}
