package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MailCommandTest {

    @Test
    public void execute_validEmails_success() {
        // Create persons with valid email addresses
        Person alice = new PersonBuilder().withName("Alice").withEmail("alice@example.com").build();
        Person bob = new PersonBuilder().withName("Bob").withEmail("bob@example.com").build();
        Person charlie = new PersonBuilder().withName("Charlie").withEmail("charlie@example.com").build();

        // Create model with initial persons
        Model model = new ModelManager();
        model.addPerson(alice); // Add Alice to avoid duplicate person exception
        model.addPerson(bob);
        model.addPerson(charlie);

        // Execute the mail command
        CommandResult result = new MailCommand().execute(model);
        String expectedLink = "mailto:alice@example.com;bob@example.com;charlie@example.com";

        // Assert that the feedback message contains the correct mailto link
        assertEquals(expectedLink, result.getFeedbackToUser());
    }
}
