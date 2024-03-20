package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;


/**
 * In charge of enforcing relationship constraints between models
 */
public class RelationshipUtil {

    /**
     * Checks if a {@code Person} with the given ID exists
     * @param id personId
     * @param patients List of {@code Person}
     * @return
     */
    public static boolean personExists(int id, List<Person> patients) {
        return patients.stream().anyMatch(patient -> patient.getSid() == id);
    }

    /**
     * Checks if the given date and time is already usedby another {@code Appointment}.
     * @param dateTime DAte time to check for
     * @param appointments List of {@code Appointment}s to check against
     */
    public static boolean isAppointmentDateTimeAlreadyTaken(LocalDateTime dateTime, List<Appointment> appointments) {
        for (Appointment appointment: appointments) {
            if (appointment.getAppointmentDateTime().equals(dateTime)) {
                return true;
            }
        }
        return false;
    }
}
