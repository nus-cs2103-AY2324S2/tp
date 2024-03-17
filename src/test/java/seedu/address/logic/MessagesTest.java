package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.DoctorBuilder;

class MessagesTest {

    @Test
    public void format_validAppointment_stringFormattedAppointment() {
        Appointment a = new AppointmentBuilder().build();
        assertEquals(Messages.format(a), "Date: "
                + a.getAppointmentDate()
                + "; Doctor: "
                + a.getDoctoNric()
                + "; Patient: "
                + a.getPatientNric()
                + "; ID: "
                + a.getAppointmentId());
    }
}