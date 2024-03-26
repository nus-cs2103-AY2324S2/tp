package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.NAME_SORT_TYPE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.InvalidSortTypeException;
import seedu.address.testutil.PersonBuilder;

public class SortCommandTest {
    @Test
    public void constructor_invalidSortType_throwsInvalidSortTypeException() {
        final String invalidSortType = "hello";
        assertThrows(InvalidSortTypeException.class, () -> new SortCommand(invalidSortType));
    }

    @Test
    public void constructor_validSortType_success() throws ParseException {
        new SortCommand(SortCommand.MONEY_SORT_TYPE);
    }

    @Test
    public void equals() throws InvalidSortTypeException {
        SortCommand sortByBirthday = new SortCommand(SortCommand.BIRTHDAY_SORT_TYPE);
        SortCommand sortByBirthdayCopy = new SortCommand(SortCommand.BIRTHDAY_SORT_TYPE);
        SortCommand sortByName = new SortCommand(NAME_SORT_TYPE);

        // same object -> returns true
        assertEquals(sortByBirthday, sortByBirthday);

        // same values -> returns true
        assertEquals(sortByBirthday, sortByBirthdayCopy);

        // different sorting methods -> returns false
        assertNotEquals(sortByBirthday, sortByName);

        // null -> returns false
        assertNotEquals(sortByBirthday, null);
    }

    @Test
    public void execute_sortByBirthday() throws ParseException {
        // This is the correct order after sorting
        Person[] persons = new Person[]{
                new PersonBuilder(ALICE)
                        .withBirthday(LocalDate.now().withYear(1999).plusDays(1)
                                .format(DateTimeFormatter.ofPattern(Birthday.BIRTHDAY_FORMAT))).build(),
                new PersonBuilder(BOB)
                        .withBirthday(LocalDate.now().withYear(1990).plusDays(2)
                                .format(DateTimeFormatter.ofPattern(Birthday.BIRTHDAY_FORMAT))).build(),
                new PersonBuilder(CARL)
                        .withBirthday(LocalDate.now().withYear(1989).plusDays(-10)
                                .format(DateTimeFormatter.ofPattern(Birthday.BIRTHDAY_FORMAT))).build(),
                new PersonBuilder(DANIEL)
                        .withBirthday(LocalDate.now().withYear(2000).plusDays(-2)
                                .format(DateTimeFormatter.ofPattern(Birthday.BIRTHDAY_FORMAT))).build(),
                new PersonBuilder(ELLE)
                        .withBirthday("").build(),
                new PersonBuilder(GEORGE)
                        .withBirthday("").build()
        };

        // Add to address book in random order
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(persons[5]);
        addressBook.addPerson(persons[2]);
        addressBook.addPerson(persons[3]);
        addressBook.addPerson(persons[4]);
        addressBook.addPerson(persons[0]);
        addressBook.addPerson(persons[1]);

        final Model model = new ModelManager(new AddressBook(addressBook), new UserPrefs());
        final Model expectedModel = new ModelManager(new AddressBook(addressBook), new UserPrefs());
        final String expectedMessage = String.format(Messages.MESSAGE_SORTED_OVERVIEW, SortCommand.BIRTHDAY_SORT_TYPE);
        expectedModel.updatePersonComparator(Birthday.BIRTHDAY_COMPARATOR);
        SortCommand command = new SortCommand(SortCommand.BIRTHDAY_SORT_TYPE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(persons), model.getFilteredPersonList());
    }

    @Test
    public void execute_sortByName() throws ParseException {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(BOB);
        addressBook.addPerson(ELLE);
        addressBook.addPerson(ALICE);
        addressBook.addPerson(CARL);
        addressBook.addPerson(DANIEL);

        final Model model = new ModelManager(addressBook, new UserPrefs());
        final Model expectedModel = new ModelManager(addressBook, new UserPrefs());
        final String expectedMessage = String.format(Messages.MESSAGE_SORTED_OVERVIEW, NAME_SORT_TYPE);
        expectedModel.updatePersonComparator(Name.NAME_COMPARATOR);
        SortCommand command = new SortCommand(NAME_SORT_TYPE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BOB, CARL, DANIEL, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_sortByMoneyOwed() throws ParseException {
        // This is the correct order after sorting
        Person[] persons = new Person[]{
                new PersonBuilder(ALICE).withMoneyOwed("-2").build(),
                new PersonBuilder(BOB).withMoneyOwed("-1.5").build(),
                new PersonBuilder(CARL).withMoneyOwed("4").build(),
                new PersonBuilder(HOON).withMoneyOwed("4").build(),
                new PersonBuilder(DANIEL).withMoneyOwed("3").build(),
                new PersonBuilder(ELLE).withMoneyOwed("0").build(),
                new PersonBuilder(GEORGE).withMoneyOwed("0").build()
        };

        // Add to address book in random order
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(persons[4]);
        addressBook.addPerson(persons[2]);
        addressBook.addPerson(persons[3]);
        addressBook.addPerson(persons[5]);
        addressBook.addPerson(persons[0]);
        addressBook.addPerson(persons[6]);
        addressBook.addPerson(persons[1]);

        final Model model = new ModelManager(addressBook, new UserPrefs());
        final Model expectedModel = new ModelManager(addressBook, new UserPrefs());
        final String expectedMessage = String.format(Messages.MESSAGE_SORTED_OVERVIEW, SortCommand.MONEY_SORT_TYPE);
        expectedModel.updatePersonComparator(MoneyOwed.MONEY_COMPARATOR);
        SortCommand command = new SortCommand(SortCommand.MONEY_SORT_TYPE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(persons), model.getFilteredPersonList());
    }

    @Test
    public void execute_clearSort() throws ParseException {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(BOB);
        addressBook.addPerson(ELLE);
        addressBook.addPerson(ALICE);
        addressBook.addPerson(CARL);
        addressBook.addPerson(DANIEL);

        final Model model = new ModelManager(addressBook, new UserPrefs());
        final Model expectedModel = new ModelManager(addressBook, new UserPrefs());
        final String expectedSortMessage = String.format(Messages.MESSAGE_SORTED_OVERVIEW, NAME_SORT_TYPE);
        final String expectedClearMessage = Messages.MESSAGE_SORT_CLEARED;

        expectedModel.updatePersonComparator(Name.NAME_COMPARATOR);
        SortCommand sortByNameCommand = new SortCommand(NAME_SORT_TYPE);
        assertCommandSuccess(sortByNameCommand, model, expectedSortMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BOB, CARL, DANIEL, ELLE), model.getFilteredPersonList());

        expectedModel.updatePersonComparator(null);
        SortCommand clearSortCommand = new SortCommand(SortCommand.CLEAR_SORT_TYPE);
        assertCommandSuccess(clearSortCommand, model, expectedClearMessage, expectedModel);
        assertEquals(Arrays.asList(BOB, ELLE, ALICE, CARL, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() throws ParseException {
        SortCommand command = new SortCommand(NAME_SORT_TYPE);
        String expected = SortCommand.class.getCanonicalName() + "{sortType=" + NAME_SORT_TYPE + "}";
        assertEquals(expected, command.toString());
    }
}
