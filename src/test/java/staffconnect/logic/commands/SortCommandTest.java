package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;
import static staffconnect.testutil.TypicalPersons.getTypicalStaffBook;

import org.junit.jupiter.api.Test;

import staffconnect.model.Model;
import staffconnect.model.ModelManager;
import staffconnect.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStaffBook(), new UserPrefs());

    @Test
    public void equals() {

        SortCommand nameSortCommand = new SortCommand(NAME_COMPARATOR);

        // same object -> returns true
        assertTrue(nameSortCommand.equals(nameSortCommand));

        // same values -> returns true
        assertTrue(nameSortCommand.equals(new SortCommand(NAME_COMPARATOR)));

        // different types -> returns false
        assertFalse(nameSortCommand.equals(1));

        // null -> returns false
        assertFalse(nameSortCommand.equals(null));

        // different values -> returns false
        assertFalse(nameSortCommand.equals(new SortCommand(MODULE_COMPARATOR)));
        assertFalse(nameSortCommand.equals(new SortCommand(VENUE_COMPARATOR)));
        assertFalse(nameSortCommand.equals(new SortCommand(PHONE_COMPARATOR)));
    }

    @Test
    public void toStringMethod() {

        SortCommand sortCommand = new SortCommand(NAME_COMPARATOR);
        String expected = SortCommand.class.getCanonicalName() + "{comparator=" + NAME_COMPARATOR + "}";
        assertEquals(expected, sortCommand.toString());
    }

}
