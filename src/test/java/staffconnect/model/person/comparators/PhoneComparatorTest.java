package staffconnect.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;

import org.junit.jupiter.api.Test;

public class PhoneComparatorTest {
    @Test
    public void doesNotEquals() {
        assertNotEquals(PHONE_COMPARATOR, MODULE_COMPARATOR);
        assertNotEquals(PHONE_COMPARATOR, NAME_COMPARATOR);
        assertNotEquals(PHONE_COMPARATOR, VENUE_COMPARATOR);
    }
}
