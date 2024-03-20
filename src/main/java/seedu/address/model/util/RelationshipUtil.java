package seedu.address.model.util;

import seedu.address.model.person.Person;

import java.util.List;

/**
 * In charge of enforcing relationship constraints between models
 */
public class RelationshipUtil {
    public static boolean personExists(int id, List<Person> patients) {
        return patients.stream().anyMatch(patient -> patient.getSid() == id);
    }
}
