package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;


/**
 * In charge of enforcing relationship constraints between models
 */
public class RelationshipUtil {
    public static boolean personExists(int id, List<Person> patients) {
        return patients.stream().anyMatch(patient -> patient.getSid() == id);
    }

    public static boolean isAppointmentDateTimeAlreadyTaken(LocalDateTime dateTime, List<Appointment> appointments) {
        for (Appointment appointment: appointments) {
            if (appointment.getAppointmentDateTime().equals(dateTime)) {
                return true;
            }
        }
        return false;
    }
}
