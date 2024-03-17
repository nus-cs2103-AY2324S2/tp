package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.date.Date;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.appointment.Note;
import seedu.address.model.appointment.Time;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String name;
    private final String nric;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String appointmentType;
    private final String note;
    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name, @JsonProperty("nric") String nric,
                             @JsonProperty("date") String date,
                             @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime,
                             @JsonProperty("appointmentType") String appointmentType,
                                  @JsonProperty("note") String note) {
        this.name = name;
        this.nric = nric;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentType = appointmentType;
        this.note = note;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        name = source.getName().toString();
        nric = source.getNric().value;
        date = source.getDate().toString();
        startTime = source.getTimePeriod().getStartTime().toString();
        endTime = source.getTimePeriod().getEndTime().toString();
        appointmentType = source.getAppointmentType().typeName;
        note = source.getNote().note;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);
        if (date == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(startTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelStartTime = new Time(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(endTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelEndTime = new Time(endTime);

        if (!TimePeriod.isValidTimePeriod(modelStartTime, modelEndTime)) {
            throw new IllegalValueException(TimePeriod.MESSAGE_CONSTRAINTS);
        }
        final TimePeriod modelTimePeriod = new TimePeriod(modelStartTime, modelEndTime);

        if (appointmentType == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, AppointmentType.class.getSimpleName()));
        }
        if (!AppointmentType.isValidAppointmentType(appointmentType)) {
            throw new IllegalValueException(AppointmentType.MESSAGE_CONSTRAINTS);
        }
        final AppointmentType modelAppointmentType = new AppointmentType(appointmentType);

        if (note == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        if (!Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        final Note modelNote = new Note(note);

        return new Appointment(modelName, modelNric, modelDate, modelTimePeriod,
                modelAppointmentType, modelNote);
    }

}
