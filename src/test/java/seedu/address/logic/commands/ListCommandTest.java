package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.ListCommandPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    private final ClassGroup classGroupA1 = new ClassGroup("A-1");

    private final ClassGroup classGroupB1 = new ClassGroup("B-1");

    private final ClassGroup nonExistentClassGroup = new ClassGroup("NonExistentClassGroup");

    @Test
    public void equals() {
        ListCommandPredicate firstPredicate =
                new ListCommandPredicate(Optional.of(Collections.singletonList(classGroupA1)));
        ListCommandPredicate secondPredicate =
                new ListCommandPredicate(Optional.of(Collections.singletonList(classGroupB1)));

        ListCommand listFirstCommand = new ListCommand(firstPredicate);
        ListCommand listSecondCommand = new ListCommand(secondPredicate);

        // same object -> returns true
        assertEquals(listFirstCommand, listFirstCommand);

        // same values -> returns true
        ListCommand listFirstCommandCopy = new ListCommand(firstPredicate);
        assertEquals(listFirstCommand, listFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, listFirstCommand);

        // null -> returns false
        assertNotEquals(null, listFirstCommand);

        // different class group -> returns false
        assertNotEquals(listFirstCommand, listSecondCommand);
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        int expectedSize = model.getFilteredPersonList().size();
        String expectedMessage = String.format(ListCommand.MESSAGE_LIST_ALL_SUCCESS, expectedSize);
        assertCommandSuccess(new ListCommand(new ListCommandPredicate(Optional.empty())),
                model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singleClassGroup_showsSingleClassGroup() {
        List<Person> expectedPersons = getTypicalPersons().stream()
                .filter(person -> person.getClassGroup().equals(classGroupA1))
                .collect(Collectors.toList());
        String expectedMessage = String.format(ListCommand.MESSAGE_LIST_CLASS_SUCCESS,
                expectedPersons.size(), classGroupA1);
        ListCommandPredicate predicate = new ListCommandPredicate(Optional.of(List.of(classGroupA1)));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListCommand(predicate), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleClassGroup_showsMultipleClassGroups() {
        List<ClassGroup> classGroups = List.of(classGroupA1, classGroupB1);
        ListCommandPredicate predicate = new ListCommandPredicate(Optional.of(classGroups));
        List<Person> expectedPersons = getTypicalPersons().stream()
                .filter(person -> classGroups.contains(person.getClassGroup()))
                .collect(Collectors.toList());
        String classGroupsString = classGroups.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        String expectedMessage = String.format(ListCommand.MESSAGE_LIST_CLASS_SUCCESS,
                expectedPersons.size(), classGroupsString);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListCommand(predicate), model, expectedMessage, expectedModel);
        assertEquals(expectedPersons, model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentClassGroup_showsEmptyList() {
        String expectedMessage = String.format(ListCommand.MESSAGE_LIST_CLASS_SUCCESS, 0, nonExistentClassGroup);
        ListCommandPredicate predicate = new ListCommandPredicate(Optional.of(List.of(nonExistentClassGroup)));
        ListCommand command = new ListCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        ListCommandPredicate predicate = new ListCommandPredicate(Optional.of(List.of(classGroupA1)));
        ListCommand listCommand = new ListCommand(predicate);
        String expected = ListCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, listCommand.toString());
    }

}
