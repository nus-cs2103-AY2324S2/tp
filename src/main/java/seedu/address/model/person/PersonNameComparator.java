package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their name.
 */
public class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().fullName.compareTo(p2.getName().fullName);
    }
}
