package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentViewBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";

    //for appointment
    public static final String DEFAULT_NRIC = "T0123456I";
    public static final String DEFAULT_DATE = "2001-01-01";
    public static final String DEFAULT_START_TIME = "23:58";
    public static final String DEFAULT_END_TIME = "23:59";
    public static final String DEFAULT_APPOINTMENT_TYPE = "Health Check-up";
    public static final String DEFAULT_NOTE = "Only speaks mandarin";

    private Name name;
    private Appointment appt;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentViewBuilder() {
        name = new Name(DEFAULT_NAME);
        appt = new AppointmentBuilder().withNric(DEFAULT_NRIC).withDate(DEFAULT_DATE).withStartTime(DEFAULT_START_TIME)
        .withEndTime(DEFAULT_END_TIME).withAppointmentType(DEFAULT_APPOINTMENT_TYPE)
        .withNote(DEFAULT_NOTE).build();
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentViewBuilder(AppointmentView apptViewToCopy) {
        name = apptViewToCopy.getName();
        appt = apptViewToCopy.getAppointment();
    }

    /**
     * Sets the {@code Nric} of the {@code AppointmentView} that we are building.
     */
    public AppointmentViewBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code AppointmentView} that we are building.
     */
    public AppointmentViewBuilder withAppointment(Appointment appt) {
        this.appt = appt;
        return this;
    }

    public AppointmentView build() {
        return new AppointmentView(name, appt);
    }
}
