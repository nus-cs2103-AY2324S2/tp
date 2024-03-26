package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their interview time.
 */
public class PersonInterviewTimeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getDateTime().dateTime == null && p2.getDateTime().dateTime == null) {
            return 0; // Both dates are null, retain the order
        } else if (p1.getDateTime().dateTime == null) {
            return 1; // p1 has null date, so it should come after p2
        } else if (p2.getDateTime().dateTime == null) {
            return -1; // p2 has null date, so it should come after p1
        } else {
            // Compare non-null dates
            return p1.getDateTime().dateTime.compareTo(p2.getDateTime().dateTime);
        }
    }
}

