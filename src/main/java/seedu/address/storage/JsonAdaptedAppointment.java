package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link seedu.address.model.person.Appointment}.
 */
class JsonAdaptedAppointment {

    private final String appointment;

    /**
     * Constructs a {@code JsonAdaptedAddress} with the given {@code appointment}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointment) {
        this.appointment = appointment;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointment = source.value;
    }

    @JsonValue
    public String getappointment() {
        return appointment;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (!Appointment.isValidAppointment(appointment)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Appointment(appointment);
    }

}
