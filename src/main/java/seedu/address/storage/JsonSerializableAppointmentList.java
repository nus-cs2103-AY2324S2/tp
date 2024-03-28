package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.ReadOnlyAppointmentList;

/**
 * An Immutable PatientList that is serializable to JSON format.
 */
@JsonRootName(value = "appointmentList")
class JsonSerializableAppointmentList {

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s).";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePatientList} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAppointmentList(@JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyPatientList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientList}.
     */
    public JsonSerializableAppointmentList(ReadOnlyAppointmentList source) {
        appointments.addAll(source.getAppointmentList().stream()
                .map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PatientList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentList toModelType() throws IllegalValueException {
        AppointmentList appointmentList = new AppointmentList();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (appointmentList.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointmentList.addAppointment(appointment);
        }
        return appointmentList;
    }

}
