package staffconnect.model.person.comparators;

import java.util.Comparator;

import staffconnect.model.person.Person;


/**
 * Represents a Comparator for a Person's Venue in the staff book.
 */
public class FacultyComparator implements Comparator<Person> {

    public static final FacultyComparator FACULTY_COMPARATOR = new FacultyComparator();

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getFaculty().toString().compareToIgnoreCase(p2.getFaculty().toString());
    }

    @Override
    public String toString() {
        return "Faculty by alphanumerical order";
    }
}
