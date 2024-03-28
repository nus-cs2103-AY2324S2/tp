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
import static staffconnect.testutil.TypicalPersons.BENSON;
import static staffconnect.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

public class NameComparatorTest {

    @Test
    public void doesNotEquals() {
        assertNotEquals(NAME_COMPARATOR, PHONE_COMPARATOR);
        assertNotEquals(NAME_COMPARATOR, MODULE_COMPARATOR);
        assertNotEquals(NAME_COMPARATOR, FACULTY_COMPARATOR);
        assertNotEquals(NAME_COMPARATOR, VENUE_COMPARATOR);
    }

    @Test
    public void checkComparator() {

        assertTrue(NAME_COMPARATOR.compare(ALICE, BENSON) <= -1); // ALICE < BENSON
        assertTrue(NAME_COMPARATOR.compare(BENSON, ALICE) >= 1); //  BENSON > ALICE

        assertTrue(NAME_COMPARATOR.compare(ALICE, CARL) <= -1); // ALICE < CARL
        assertTrue(NAME_COMPARATOR.compare(CARL, ALICE) >= 1); // CARL > ALICE

        assertEquals(NAME_COMPARATOR.compare(BENSON, BENSON), 0); // BENSON = BENSON
        assertEquals(NAME_COMPARATOR.compare(ALICE, ALICE), 0); // ALICE = ALICE
    }

    @Test
    public void toStringTest() {
        assertEquals(NAME_COMPARATOR.toString(), "Name by alphanumerical order");

        assertNotEquals(NAME_COMPARATOR.toString(), "Phone by ascending order");
        assertNotEquals(NAME_COMPARATOR.toString(), "Module by alphanumerical order");
        assertNotEquals(NAME_COMPARATOR.toString(), "Faculty by alphanumerical order");
        assertNotEquals(NAME_COMPARATOR.toString(), "Venue by alphanumerical order");
        assertNotEquals(NAME_COMPARATOR.toString(), "random string");
    }
}
