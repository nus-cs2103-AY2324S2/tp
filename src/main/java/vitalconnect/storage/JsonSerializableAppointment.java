package vitalconnect.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vitalconnect.commons.exceptions.IllegalValueException;
import vitalconnect.model.Appointment;


/**
 * Represents a list of appointments that can be serialized into JSON format. This class provides a convenient way
 * to convert between a list of {@code Appointment} objects and their JSON representation, which can be used for
 * persistent storage.
 */
class JsonSerializableAppointment {
    private List<JsonAdaptedAppointment> appointments;

    /**
     * Constructs a {@code JsonSerializableAppointment} with the given list of {@code JsonAdaptedAppointment}.
     *
     * @param appointments A list of {@code JsonAdaptedAppointment} which is a JSON-friendly representation of
     *                     appointments.
     */
    @JsonCreator
    public JsonSerializableAppointment(@JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.appointments = new ArrayList<>(appointments);
    }

    /**
     * Converts this JSON-friendly adapted appointment list into the model's {@code Appointment} list.
     *
     * @return A list containing the {@code Appointment} objects corresponding to this JSON-friendly list.
     * @throws IllegalValueException If any data constraints are violated in the adapted appointments.
     */
    public List<Appointment> toModelType() throws IllegalValueException {
        List<Appointment> appointmentList = new ArrayList<>();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            appointmentList.add(appointment);
        }
        return appointmentList;
    }
}


