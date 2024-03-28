package staffconnect.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.model.person.comparators.FacultyComparator.FACULTY_COMPARATOR;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;
import static staffconnect.testutil.TypicalPersons.CARL;
import static staffconnect.testutil.TypicalPersons.ELLE;
import static staffconnect.testutil.TypicalPersons.GEORGE;

import org.junit.jupiter.api.Test;

public class VenueComparatorTest {
    @Test
    public void doesNotEquals() {
        assertNotEquals(VENUE_COMPARATOR, NAME_COMPARATOR);
        assertNotEquals(VENUE_COMPARATOR, PHONE_COMPARATOR);
        assertNotEquals(VENUE_COMPARATOR, MODULE_COMPARATOR);
        assertNotEquals(VENUE_COMPARATOR, FACULTY_COMPARATOR);
    }

    @Test
    public void checkComparator() {

        assertTrue(VENUE_COMPARATOR.compare(ELLE, CARL) <= -1); // michegan ave < wall street
        assertTrue(VENUE_COMPARATOR.compare(CARL, ELLE) >= 1); // wall street > michegan ave

        assertTrue(VENUE_COMPARATOR.compare(GEORGE, ELLE) <= 1); //  4th street > michegan ave
        assertTrue(VENUE_COMPARATOR.compare(ELLE, GEORGE) >= -1); // michegan ave < 4th street

        assertEquals(VENUE_COMPARATOR.compare(ELLE, ELLE), 0); // michegan ave = michegan ave
        assertEquals(VENUE_COMPARATOR.compare(GEORGE, GEORGE), 0); // 4th street = 4th street
    }

    @Test
    public void toStringTest() {
        assertEquals(VENUE_COMPARATOR.toString(), "Venue by alphanumerical order");

        assertNotEquals(VENUE_COMPARATOR.toString(), "Name by alphanumerical order");
        assertNotEquals(VENUE_COMPARATOR.toString(), "Phone by ascending order");
        assertNotEquals(VENUE_COMPARATOR.toString(), "Module by alphanumerical order");
        assertNotEquals(VENUE_COMPARATOR.toString(), "Faculty by alphanumerical order");
        assertNotEquals(VENUE_COMPARATOR.toString(), "random string");
    }
}
