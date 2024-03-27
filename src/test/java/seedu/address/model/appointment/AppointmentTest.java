package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

class AppointmentTest {

    @Test
    void getDoctorNric() {
        Nric doctorNric = new Nric("S1234567A");
        Appointment appointment = new Appointment(doctorNric, new Nric("T1234567A"),
                new AppointmentDate(LocalDate.now()));
        assertEquals(doctorNric, appointment.getDoctorNric());
    }

    @Test
    void getPatientNric() {
        Nric patientNric = new Nric("T1234567A");
        Appointment appointment = new Appointment(new Nric("S1234567A"), patientNric,
                new AppointmentDate(LocalDate.now()));
        assertEquals(patientNric, appointment.getPatientNric());
    }

    @Test
    void getAppointmentId() {
        AppointmentId appointmentId = new AppointmentId();
        Appointment appointment = new Appointment(new Nric("S1234567A"), new Nric("T1234567A"),
                new AppointmentDate(LocalDate.now()), appointmentId);
        assertEquals(appointmentId, appointment.getAppointmentId());
    }

    @Test
    void getAppointmentDate() {
        AppointmentDate appointmentDate = new AppointmentDate(LocalDate.now());
        Appointment appointment = new Appointment(new Nric("S1234567A"), new Nric("T1234567A"), appointmentDate);
        assertEquals(appointmentDate, appointment.getAppointmentDate());
    }

    @Test
    void isValidAppointment_validDate_returnsTrue() {
        AppointmentDate futureDate = new AppointmentDate(LocalDate.now().plusDays(1));
        Appointment appointment = new Appointment(new Nric("S1234567A"), new Nric("T1234567A"), futureDate);

        assertTrue(appointment.isValidAppointment(futureDate));
    }

    @Test
    void isValidAppointment_pastDate_returnsFalse() {
        AppointmentDate pastDate = new AppointmentDate(LocalDate.now().minusDays(1));
        // Use assertThrows to check if IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(new Nric("S1234567A"), new Nric("T1234567A"), pastDate);
        });
    }

    @Test
    void isSameAppointment() {
        Nric doctorNric = new Nric("S1234567A");
        Nric patientNric = new Nric("T1234567A");
        AppointmentDate appointmentDate = new AppointmentDate(LocalDate.now());
        Appointment appointment1 = new Appointment(doctorNric, patientNric, appointmentDate);
        Appointment appointment2 = new Appointment(doctorNric, patientNric, appointmentDate);
        assertTrue(appointment1.isSameAppointment(appointment2));
    }

    @Test
    void appointmentContainsPerson() {
        Nric doctorNric = new Nric("S1234567A");
        Nric patientNric = new Nric("T1234567A");
        AppointmentDate appointmentDate = new AppointmentDate(LocalDate.now());
        Appointment appointment = new Appointment(doctorNric, patientNric, appointmentDate);
        Person doctor = new DoctorBuilder().withNric("S7654321A").build();
        Person patient = new PatientBuilder().withNric("T7654321A").build();
        assertFalse(appointment.appointmentContainsPerson(doctor));
        assertFalse(appointment.appointmentContainsPerson(patient));
    }

    @Test
    void testEquals() {
        Nric doctorNric = new Nric("S1234567A");
        Nric patientNric = new Nric("T1234567A");
        AppointmentDate appointmentDate = new AppointmentDate(LocalDate.now());
        Appointment appointment1 = new Appointment(doctorNric, patientNric, appointmentDate);
        Appointment appointment2 = new Appointment(doctorNric, patientNric, appointmentDate);
        assertEquals(appointment1, appointment2);
    }

    @Test
    void testToString() {
        Nric doctorNric = new Nric("S1234567A");
        Nric patientNric = new Nric("T1234567A");
        AppointmentDate appointmentDate = new AppointmentDate(LocalDate.now());
        AppointmentId appointmentId = new AppointmentId();
        Appointment appointment = new Appointment(doctorNric, patientNric, appointmentDate, appointmentId);
        String expectedString = "seedu.address.model.appointment.Appointment{Date=" + appointmentDate + ", Doctor="
                + doctorNric + ", Patient=" + patientNric + ", Id=" + appointmentId + "}";
        assertEquals(expectedString, appointment.toString());
    }
}
