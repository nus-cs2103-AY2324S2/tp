package staffconnect.model.person.comparators;

import java.util.Comparator;

import staffconnect.model.person.Person;

/**
 * Represents a Comparator for a Person's Module code in the staff book.
 */
public class ModuleComparator implements Comparator<Person> {

    public static final ModuleComparator MODULE_COMPARATOR = new ModuleComparator();

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getModule().toString().compareToIgnoreCase(p2.getModule().toString());
    }

    @Override
    public String toString() {
        return "Module by alphanumerical order";
    }
}
