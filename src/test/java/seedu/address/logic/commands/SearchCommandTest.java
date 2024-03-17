package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand.SearchPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SearchPersonDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SearchPersonDescriptor firstDescriptor =
                new SearchPersonDescriptorBuilder().withName("first").build();
        SearchPersonDescriptor secondDescriptor =
                new SearchPersonDescriptorBuilder().withName("second").build();

        SearchCommand searchFirstCommand = new SearchCommand(firstDescriptor);
        SearchCommand searchSecondCommand = new SearchCommand(secondDescriptor);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstDescriptor);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        SearchPersonDescriptor descriptor =
                new SearchPersonDescriptorBuilder().withName("keyword").build();
        SearchCommand command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_matchSubstring() {
        Person ALEX = new PersonBuilder().withName("ALEX Meyer").withPhone("659482224")
                .withEmail("bellewerner@example.com").withAddress("michegan ave").withComment("Good job").build();
        Person BELLE = new PersonBuilder().withName("Belle Meyer").withPhone("659482224")
                .withEmail("bellewerner@example.com").withAddress("michegan ave").withComment("Good work").build();
        expectedModel.addPerson(ALEX);
        expectedModel.addPerson(BELLE);
        model = expectedModel;
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withName("Meier").build();
        SearchCommand command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());

        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        descriptor = new SearchPersonDescriptorBuilder().withTags("friend").build();
        command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());

        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        descriptor = new SearchPersonDescriptorBuilder().withComment("Good").build();
        command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALEX, BELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_matchExact() {
        Person BELLE = new PersonBuilder().withName("Belle Meyer").withPhone("659482224")
                .withEmail("bellewerner@example.com").withAddress("michegan ave").build();
        expectedModel.addPerson(BELLE);
        model = expectedModel;

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withPhone("9482224").build();
        SearchCommand command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ELLE), model.getFilteredPersonList());

        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        descriptor = new SearchPersonDescriptorBuilder().withEmail("werner@example.com").build();
        command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFields() {
        Person BELLE = new PersonBuilder().withName("Belle Meyer").withPhone("659482224")
                .withEmail("bellewerner@example.com").withAddress("michegan ave").withComment("Good work").build();
        expectedModel.addPerson(BELLE);
        model = expectedModel;

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withName("B")
                .withComment("work").build();
        SearchCommand command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BELLE), model.getFilteredPersonList());

        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        descriptor = new SearchPersonDescriptorBuilder().withName("Belle").withEmail("werner@example.com").build();
        command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withName("keyword").build();
        SearchCommand SearchCommand = new SearchCommand(descriptor);
        String expected = SearchCommand.class.getCanonicalName() + "{searchPersonDescriptor=" + descriptor + "}";
        assertEquals(expected, SearchCommand.toString());
    }
}
