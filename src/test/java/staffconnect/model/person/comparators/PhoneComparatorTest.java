package staffconnect.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.model.person.comparators.FacultyComparator.FACULTY_COMPARATOR;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;
import static staffconnect.testutil.TypicalPersons.BENSON;
import static staffconnect.testutil.TypicalPersons.CARL;
import static staffconnect.testutil.TypicalPersons.ELLE;

import org.junit.jupiter.api.Test;

public class PhoneComparatorTest {
    @Test
    public void doesNotEquals() {
        assertNotEquals(PHONE_COMPARATOR, NAME_COMPARATOR);
        assertNotEquals(PHONE_COMPARATOR, MODULE_COMPARATOR);
        assertNotEquals(PHONE_COMPARATOR, FACULTY_COMPARATOR);
        assertNotEquals(PHONE_COMPARATOR, VENUE_COMPARATOR);
    }

    @Test
    public void checkComparator() {

        assertTrue(PHONE_COMPARATOR.compare(ELLE, BENSON) <= -1); // 9482224 < 98765432
        assertTrue(PHONE_COMPARATOR.compare(BENSON, ELLE) >= 1); //  98765432 > 9482224

        assertTrue(PHONE_COMPARATOR.compare(ELLE, CARL) <= -1); // 9482224 < 95352563
        assertTrue(PHONE_COMPARATOR.compare(CARL, ELLE) >= 1); // 95352563 > 9482224

        assertEquals(PHONE_COMPARATOR.compare(BENSON, BENSON), 0); // 98765432 = 98765432
        assertEquals(PHONE_COMPARATOR.compare(ELLE, ELLE), 0); // 9482224 = 9482224
    }

    @Test
    public void toStringTest() {
        assertEquals(PHONE_COMPARATOR.toString(), "Phone by ascending order");

        assertNotEquals(PHONE_COMPARATOR.toString(), "Name by alphanumerical order");
        assertNotEquals(PHONE_COMPARATOR.toString(), "Module by alphanumerical order");
        assertNotEquals(PHONE_COMPARATOR.toString(), "Faculty by alphanumerical order");
        assertNotEquals(PHONE_COMPARATOR.toString(), "Venue by alphanumerical order");
        assertNotEquals(PHONE_COMPARATOR.toString(), "random string");
    }
}
