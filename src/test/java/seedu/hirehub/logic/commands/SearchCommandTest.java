package seedu.hirehub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hirehub.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.hirehub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hirehub.testutil.TypicalPersons.ALICE;
import static seedu.hirehub.testutil.TypicalPersons.BENSON;
import static seedu.hirehub.testutil.TypicalPersons.DANIEL;
import static seedu.hirehub.testutil.TypicalPersons.ELLE;
import static seedu.hirehub.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.hirehub.logic.commands.SearchCommand.SearchPersonDescriptor;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.ModelManager;
import seedu.hirehub.model.UserPrefs;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.testutil.PersonBuilder;
import seedu.hirehub.testutil.SearchPersonDescriptorBuilder;

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
        Person alex = new PersonBuilder().withName("Alex Meyer").withPhone("659482224")
                .withEmail("alex@example.com").withCountry("US").withComment("Good job").build();
        Person belle = new PersonBuilder().withName("Belle Meyer").withPhone("659482224")
                .withEmail("bellewerner@example.com").withCountry("US").withComment("Good work")
                .build();
        expectedModel.addPerson(alex);
        expectedModel.addPerson(belle);
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
        assertEquals(Arrays.asList(alex, belle), model.getFilteredPersonList());
    }

    @Test
    public void execute_matchExact() {
        Person belle = new PersonBuilder().withName("Belle Meyer").withPhone("659482224")
                .withEmail("bellewerner@example.com").withCountry("US").withComment("Good work")
                .build();
        expectedModel.addPerson(belle);
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

        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        descriptor = new SearchPersonDescriptorBuilder().withCountry("SG").build();
        command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFields() {
        Person belle = new PersonBuilder().withName("Belle Meyer").withPhone("659482224")
                .withEmail("bellewerner@example.com").withCountry("US").withComment("Good work")
                .build();
        expectedModel.addPerson(belle);
        model = expectedModel;

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withName("B")
                .withComment("work").build();
        SearchCommand command = new SearchCommand(descriptor);
        expectedModel.updateFilteredPersonList(descriptor.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(belle), model.getFilteredPersonList());

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
        SearchCommand searchCommand = new SearchCommand(descriptor);
        String expected = SearchCommand.class.getCanonicalName() + "{searchPersonDescriptor=" + descriptor + "}";
        assertEquals(expected, searchCommand.toString());
    }
}
