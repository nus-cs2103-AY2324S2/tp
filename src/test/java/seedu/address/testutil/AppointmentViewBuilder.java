package seedu.address.testutil;

import seedu.address.commons.core.date.Date;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.appointment.Note;
import seedu.address.model.appointment.Time;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;

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
        Nric nric = new Nric(DEFAULT_NRIC);
        Date date = new Date(DEFAULT_DATE);
        Time startTime = new Time(DEFAULT_START_TIME);
        Time endTime = new Time(DEFAULT_END_TIME);

        TimePeriod timePeriod = new TimePeriod(startTime, endTime);
        AppointmentType appointmentType = new AppointmentType(DEFAULT_APPOINTMENT_TYPE);
        Note note = new Note(DEFAULT_NOTE);
        appt = new Appointment(nric, date, timePeriod, appointmentType, note);
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
