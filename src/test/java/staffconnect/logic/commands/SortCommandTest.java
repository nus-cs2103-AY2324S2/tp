package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;
import static staffconnect.testutil.TypicalPersons.getTypicalStaffBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import staffconnect.model.Model;
import staffconnect.model.ModelManager;
import staffconnect.model.UserPrefs;
import staffconnect.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStaffBook(), new UserPrefs());

    @Test
    public void equals() {

        SortCommand moduleSortCommand = new SortCommand(MODULE_COMPARATOR);
        SortCommand nameSortCommand = new SortCommand(NAME_COMPARATOR);
        SortCommand venueSortCommand = new SortCommand(VENUE_COMPARATOR);
        SortCommand phoneSortCommand = new SortCommand(PHONE_COMPARATOR);



        // same object -> returns true
        assertTrue(nameSortCommand.equals(nameSortCommand));

        // same values -> returns true
        assertTrue(nameSortCommand.equals(new SortCommand(NAME_COMPARATOR)));

        // different types -> returns false
        assertFalse(nameSortCommand.equals(1));

        // null -> returns false
        assertFalse(nameSortCommand.equals(null));

        // different attribute -> returns false
        assertFalse(nameSortCommand.equals(moduleSortCommand));
        assertFalse(nameSortCommand.equals(venueSortCommand));
        assertFalse(nameSortCommand.equals(phoneSortCommand));
    }

    @Test
    public void toStringMethod() {
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getName().toString().compareToIgnoreCase(person2.getName().toString());
            }
        };
        SortCommand sortCommand = new SortCommand(comparator);
        String expected = SortCommand.class.getCanonicalName() + "{comparator=" + comparator + "}";
        assertEquals(expected, sortCommand.toString());
    }

}
