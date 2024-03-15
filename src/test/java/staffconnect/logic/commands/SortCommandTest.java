package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        Comparator<Person> firstComparator = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getName().toString().compareToIgnoreCase(person2.getName().toString());
            }
        };
        Comparator<Person> secondComparator = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getModule().toString().compareToIgnoreCase(person2.getModule().toString());
            }
        };

        SortCommand sortFirstCommand = new SortCommand(firstComparator);
        SortCommand sortSecondCommand = new SortCommand(secondComparator);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
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
