package staffconnect.model.person.comparators;

import java.util.Comparator;

import staffconnect.model.person.Person;

/**
 * Represents a Comparator for a Person's Phone number in the staff book.
 */
public class PhoneComparator implements Comparator<Person> {

    public static final PhoneComparator PHONE_COMPARATOR = new PhoneComparator();

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getPhone().toString().compareToIgnoreCase(p2.getPhone().toString());
    }

    @Override
    public String toString() {
        return "Phone by ascending order";
    }
}
