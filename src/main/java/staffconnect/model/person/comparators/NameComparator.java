package staffconnect.model.person.comparators;

import java.util.Comparator;

import staffconnect.model.person.Person;

/**
 * Represents a Comparator for a Person's Name in the staff book.
 */
public class NameComparator implements Comparator<Person> {

    public static final NameComparator NAME_COMPARATOR = new NameComparator();
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().toString().compareToIgnoreCase(p2.getName().toString());
    }

    @Override
    public String toString() {
        return "Name by alphanumerical order";
    }
}
