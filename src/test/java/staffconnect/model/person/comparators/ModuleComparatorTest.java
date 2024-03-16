package staffconnect.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;

import org.junit.jupiter.api.Test;

public class ModuleComparatorTest {
    @Test
    public void doesNotEquals() {
        assertNotEquals(MODULE_COMPARATOR, NAME_COMPARATOR);
        assertNotEquals(MODULE_COMPARATOR, PHONE_COMPARATOR);
        assertNotEquals(MODULE_COMPARATOR, VENUE_COMPARATOR);
    }
}
