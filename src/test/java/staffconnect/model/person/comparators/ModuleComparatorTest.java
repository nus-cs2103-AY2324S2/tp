package staffconnect.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.BENSON;
import static staffconnect.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

public class ModuleComparatorTest {
    @Test
    public void doesNotEquals() {
        assertNotEquals(MODULE_COMPARATOR, NAME_COMPARATOR);
        assertNotEquals(MODULE_COMPARATOR, PHONE_COMPARATOR);
        assertNotEquals(MODULE_COMPARATOR, VENUE_COMPARATOR);
    }

    @Test
    public void checkComparator() {
        assertEquals(MODULE_COMPARATOR.compare(ALICE, BENSON), -1); // CS1101S < CS1231S
        assertEquals(MODULE_COMPARATOR.compare(BENSON, ALICE), 1); //  CS1231S > CS1101S

        assertEquals(MODULE_COMPARATOR.compare(ALICE, CARL), -1); // CS1101S < CS2030S
        assertEquals(MODULE_COMPARATOR.compare(CARL, ALICE), 1); // CS2030S > CS1101S

        assertEquals(MODULE_COMPARATOR.compare(BENSON, BENSON), 0); // CS1231S = CS1231S
        assertEquals(MODULE_COMPARATOR.compare(ALICE, ALICE), 0); // CS1101S = CS1101S

    }
}
