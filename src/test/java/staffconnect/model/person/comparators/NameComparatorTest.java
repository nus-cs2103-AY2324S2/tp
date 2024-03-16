package staffconnect.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;

import org.junit.jupiter.api.Test;

public class NameComparatorTest {

    @Test
    public void doesNotEquals() {
        assertNotEquals(NAME_COMPARATOR, MODULE_COMPARATOR);
        assertNotEquals(NAME_COMPARATOR, PHONE_COMPARATOR);
        assertNotEquals(NAME_COMPARATOR, VENUE_COMPARATOR);
    }
}
