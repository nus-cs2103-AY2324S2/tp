package vitalconnect.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vitalconnect.commons.exceptions.IllegalValueException;
import vitalconnect.model.Appointment;

/**
 * A Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    private final String patientName;
    private final String patientIc;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patientName") String patientName,
                                  @JsonProperty("patientIc") String patientIc,
                                  @JsonProperty("dateTime") String dateTime) {
        this.patientName = patientName;
        this.patientIc = patientIc;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        patientName = source.getPatientName();
        patientIc = source.getPatientIc();
        dateTime = source.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (patientName == null || patientIc == null || dateTime == null) {
            throw new IllegalValueException("Missing fields in Appointment data.");
        }

        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("Incorrect format for appointment dateTime. Expected dd/MM/yyyy HHmm.");
        }

        return new Appointment(patientName, patientIc, localDateTime);
    }
}

