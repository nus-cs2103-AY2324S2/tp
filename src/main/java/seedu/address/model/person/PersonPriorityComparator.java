package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their priority.
 */
public class PersonPriorityComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getPriority(), p2.getPriority());
    }
}
