package seedu.address.testutil;

import seedu.address.commons.core.date.Date;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.appointment.Note;
import seedu.address.model.appointment.Time;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.person.Nric;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NRIC = "T0123456I";
    public static final String DEFAULT_DATE = "2001-01-01";
    public static final String DEFAULT_START_TIME = "23:58";
    public static final String DEFAULT_END_TIME = "23:59";
    public static final String DEFAULT_APPOINTMENT_TYPE = "Health Check-up";
    public static final String DEFAULT_NOTE = "Only speaks mandarin";

    private Nric nric;
    private Date date;
    private TimePeriod timePeriod;
    private AppointmentType appointmentType;
    private Note note;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        date = new Date(DEFAULT_DATE);
        Time startTime = new Time(DEFAULT_START_TIME);
        Time endTime = new Time(DEFAULT_END_TIME);

        timePeriod = new TimePeriod(startTime, endTime);
        appointmentType = new AppointmentType(DEFAULT_APPOINTMENT_TYPE);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment apptToCopy) {
        nric = apptToCopy.getNric();
        date = apptToCopy.getDate();
        timePeriod = apptToCopy.getTimePeriod();
        appointmentType = apptToCopy.getAppointmentType();
        note = apptToCopy.getNote();
    }

    /**
     * Sets the {@code Nric} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the start time of {@code TimePeriod} of the {@code Appointment}
     * that we are building.
     */
    public AppointmentBuilder withStartTime(String startTime) {
        Time newStart = new Time(startTime);
        this.timePeriod = new TimePeriod(newStart, this.timePeriod.getEndTime());
        return this;
    }

    /**
     * Sets the end time of {@code TimePeriod} of the {@code Appointment}
     * that we are building.
     */
    public AppointmentBuilder withEndTime(String endTime) {
        Time newEnd = new Time(endTime);
        this.timePeriod = new TimePeriod(this.timePeriod.getStartTime(), newEnd);
        return this;
    }

    /**
     * Sets the {@code AppointmentType} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentType(String typeName) {
        this.appointmentType = new AppointmentType(typeName);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    public Appointment build() {
        return new Appointment(nric, date, timePeriod, appointmentType, note);
    }
}
