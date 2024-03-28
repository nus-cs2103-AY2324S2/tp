package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MailCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        GroupContainsKeywordsPredicate firstPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList("TUT01"));
        GroupContainsKeywordsPredicate secondPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList("TUT02"));

        MailCommand findFirstCommand = new MailCommand(firstPredicate);
        MailCommand findSecondCommand = new MailCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        MailCommand findFirstCommandCopy = new MailCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noPredicate_success() {
        Model model = new ModelManager();
        MailCommand mailCommand = new MailCommand();
        CommandResult commandResult = mailCommand.execute(model);
        assertEquals("mailto:", commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_withMultiplePredicate_success() {
        Model model = new ModelManager();
        List<Person> personList = Arrays.asList(
                new PersonBuilder().withName("Alice").withEmail("test1@example.com").build(),
                new PersonBuilder().withName("Bob").withEmail("test2@example.com").build()
        );
        model.addPerson(personList.get(0));
        model.addPerson(personList.get(1));

        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(Arrays.asList("TUT01"));
        model.updateFilteredPersonList(predicate);

        MailCommand mailCommand = new MailCommand(predicate);
        CommandResult commandResult = mailCommand.execute(model);

        // Extract emails from personList filtered by the predicate
        List<String> emails = personList.stream()
                .filter(person -> predicate.test(person))
                .map(person -> person.getEmail().toString())
                .collect(Collectors.toList());

        // Generate the expected mailto link
        String expectedLink = "mailto:" + String.join(";", emails);

        // Ensure that the generated mailto link matches the expected one
        assertEquals(expectedLink, commandResult.getFeedbackToUser());
    }
}
