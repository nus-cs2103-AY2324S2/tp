package staffconnect.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.model.person.comparators.FacultyComparator.FACULTY_COMPARATOR;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.LEONARDO;
import static staffconnect.testutil.TypicalPersons.MICHAEL;

import org.junit.jupiter.api.Test;

public class FacultyComparatorTest {
    @Test
    public void doesNotEquals() {
        assertNotEquals(FACULTY_COMPARATOR, NAME_COMPARATOR);
        assertNotEquals(FACULTY_COMPARATOR, PHONE_COMPARATOR);
        assertNotEquals(FACULTY_COMPARATOR, MODULE_COMPARATOR);
        assertNotEquals(FACULTY_COMPARATOR, VENUE_COMPARATOR);
    }

    @Test
    public void checkComparator() {

        assertTrue(FACULTY_COMPARATOR.compare(ALICE, MICHAEL) <= -1); // Computing < Music
        assertTrue(FACULTY_COMPARATOR.compare(MICHAEL, ALICE) >= 1); // Music > Computing

        assertTrue(FACULTY_COMPARATOR.compare(LEONARDO, MICHAEL) <= -1); // Arts and Social Sciences < Music
        assertTrue(FACULTY_COMPARATOR.compare(MICHAEL, LEONARDO) >= 1); //  Music > Arts and Social Sciences

        assertEquals(FACULTY_COMPARATOR.compare(LEONARDO, LEONARDO), 0);
        // Arts and Social Sciences = Arts and Social Sciences
        assertEquals(FACULTY_COMPARATOR.compare(MICHAEL, MICHAEL), 0); // Music = Music
    }

    @Test
    public void toStringTest() {
        assertEquals(FACULTY_COMPARATOR.toString(), "Faculty by alphanumerical order");

        assertNotEquals(FACULTY_COMPARATOR.toString(), "Name by alphanumerical order");
        assertNotEquals(FACULTY_COMPARATOR.toString(), "Phone by ascending order");
        assertNotEquals(FACULTY_COMPARATOR.toString(), "Module by alphanumerical order");
        assertNotEquals(FACULTY_COMPARATOR.toString(), "Venue by alphanumerical order");
        assertNotEquals(FACULTY_COMPARATOR.toString(), "random string");
    }
}
