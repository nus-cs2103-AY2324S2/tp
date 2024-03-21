package staffconnect.model.person.comparators;

import java.util.Comparator;

import staffconnect.model.person.Person;

/**
 * Represents a Comparator for a Person's Venue in the staff book.
 */
public class VenueComparator implements Comparator<Person> {

    public static final VenueComparator VENUE_COMPARATOR = new VenueComparator();

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getVenue().toString().compareToIgnoreCase(p2.getVenue().toString());
    }

    @Override
    public String toString() {
        return "Venue by alphanumerical order";
    }
}
