package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.LastContact;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class LastContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void equals() {
        LastContact lastContact1 = new LastContact("13-03-2024 0600");
        LastContact lastContact2 = new LastContact("13-03-2024 0700");
        LastContactCommand lastContactCommand1 = new LastContactCommand("Alice", lastContact1);
        LastContactCommand lastContactCommand2 = new LastContactCommand("Bob", lastContact2);

        // same object -> returns true
        assertEquals(lastContactCommand1, lastContactCommand1);

        // same values -> returns true
        LastContactCommand lastContactCommand1Copy = new LastContactCommand("Alice", lastContact1);
        assertTrue(lastContactCommand1.equals(lastContactCommand1Copy));

        // different types -> returns false
        assertFalse(lastContactCommand1.equals("-"));

        // null -> returns false
        assertFalse(lastContactCommand1.equals(null));

        // different dateTime -> returns false
        assertFalse(lastContactCommand2.equals(lastContactCommand1));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastContactCommand("Alice", null));
    }

    @Test
    public void execute_personNotFound_fail() throws Exception {
        String expectedMessage = LastContactCommand.generateFailedMessage("Alice bob");
        String name = "Alice bob";
        String lastContact = "13-03-2024 0600";
        Person person = new PersonBuilder().withName(name).withLastContact(lastContact).build();
        if (model.hasPerson(person)) {
            model.deletePerson(person);
        }
        String actualMessage = new LastContactCommand(name, new LastContact(lastContact)).execute(model).getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void execute_personFound_success() throws Exception {
        String name = "Alice bob";
        String lastContact = "13-03-2024 0600";
        Person person = new PersonBuilder().withName(name).withLastContact(lastContact).build();
        String expectedMessage = LastContactCommand.generateSuccessMessage(person);
        if (!model.hasPerson(person)) {
            model.addPerson(person);
        }
        String actualMessage = new LastContactCommand(name, new LastContact(lastContact)).execute(model).getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
    }

}
