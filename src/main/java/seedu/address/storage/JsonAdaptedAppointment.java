package seedu.address.storage;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    public final UUID id;
    public final UUID personId;
    public final String appointmentTime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("id") UUID id,
                             @JsonProperty("personId") UUID personId,
                             @JsonProperty("appointmentTime") String appointmentTime) {
        this.id = id;
        this.personId = personId;
        this.appointmentTime = appointmentTime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        id = source.getId();
        personId = source.getPersonId();
        appointmentTime = source.getAppointmentTimeStringForJson();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        }
        final UUID modelId = id;

        if (personId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "personId"));
        }
        final UUID modelPersonId = personId;

        if (appointmentTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "appointmentTime"));
        }

        final String modelAppointmentTime = appointmentTime;

        return new Appointment(modelId, modelPersonId, new AppointmentTime(modelAppointmentTime));
    }
}
