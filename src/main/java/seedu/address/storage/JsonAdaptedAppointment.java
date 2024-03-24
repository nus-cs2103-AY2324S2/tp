package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.DoB;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String doctorNric;
    private final String patientNric;
    private final String appointmentDate;
    private final String appointmentId;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("doctorNric") String doctorNric, @JsonProperty("patientNric") String patientNric,
                             @JsonProperty("appointmentDate") String appointmentDate,
                                  @JsonProperty("appointmentId") String appointmentId) {
        this.doctorNric = doctorNric;
        this.patientNric = patientNric;
        this.appointmentDate = appointmentDate;
        this.appointmentId = appointmentId;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        doctorNric = source.getDoctorNric().toString();
        patientNric = source.getPatientNric().toString();
        appointmentDate = source.getAppointmentDate().appointmentDate.toString();
        appointmentId = source.getAppointmentId().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (doctorNric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(doctorNric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelDoctorNric = new Nric(doctorNric);

        if (patientNric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(patientNric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelPatientNric = new Nric(patientNric);

        if (appointmentDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentDate.class.getSimpleName()));
        }
        if (!AppointmentDate.isValidDate(appointmentDate)) {
            throw new IllegalValueException(AppointmentDate.MESSAGE_CONSTRAINTS);
        }

        final AppointmentDate modelAppointmentDate = new AppointmentDate(appointmentDate);


        if (appointmentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentId.class.getSimpleName()));
        }
        if (!AppointmentId.isValidApptId(appointmentId)) {
            throw new IllegalValueException(AppointmentId.MESSAGE_CONSTRAINTS);
        }

        final AppointmentId modelAppointmentId = new AppointmentId(appointmentId);

        return new Appointment(modelDoctorNric, modelPatientNric, modelAppointmentDate, modelAppointmentId);
    }

}
