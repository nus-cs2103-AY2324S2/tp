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
        assertNotEquals(VENUE_COMPARATOR, FACULTY_COMPARATOR);
        assertNotEquals(VENUE_COMPARATOR, MODULE_COMPARATOR);
        assertNotEquals(VENUE_COMPARATOR, NAME_COMPARATOR);
        assertNotEquals(VENUE_COMPARATOR, PHONE_COMPARATOR);
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
}
