package seedu.address.model.util;

import java.util.List;

import seedu.address.model.person.Person;


/**
 * In charge of enforcing relationship constraints between models
 */
public class RelationshipUtil {
    public static boolean personExists(int id, List<Person> patients) {
        return patients.stream().anyMatch(patient -> patient.getSid() == id);
    }
}
