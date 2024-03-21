package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

class MessagesTest {

    @Test
    public void format_validPatient_stringFormattedPerson() {
        Person patient = new PatientBuilder().build();
        assertEquals(Messages.format(patient), "; NRIC: "
                + patient.getNric()
                + "; Name: "
                + patient.getName()
                + "; DoB: "
                + patient.getDoB()
                + "; Phone: "
                + patient.getPhone()
                + ";");
    }

    @Test
    public void format_validDoctor_stringFormattedPerson() {
        Person doctor = new DoctorBuilder().build();
        assertEquals(Messages.format(doctor), "; NRIC: "
                + doctor.getNric()
                + "; Name: "
                + doctor.getName()
                + "; DoB: "
                + doctor.getDoB()
                + "; Phone: "
                + doctor.getPhone()
                + ";");
    }

    @Test
    public void format_validAppointment_stringFormattedAppointment() {
        Appointment a = new AppointmentBuilder().build();
        assertEquals(Messages.format(a), "Date: "
                + a.getAppointmentDate()
                + "; Doctor: "
                + a.getDoctorNric()
                + "; Patient: "
                + a.getPatientNric()
                + "; ID: "
                + a.getAppointmentId());
    }
}
