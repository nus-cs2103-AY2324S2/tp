package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.PersonBuilder;

public class StarCommandTest {

    private static final String CONTACT_STUB = "Alex Yeoh";
    private Model model = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());

    @Test
    public void execute_contactNotFound_throwsCommandException() {
        StarCommand starCommand = new StarCommand("Nonexistent Contact");
        assertCommandFailure(starCommand, model, "Error! Contact not found: Nonexistent Contact");
    }

    @Test
    public void execute_contactFoundAndStarred_successful() {
        StarCommand starCommand = new StarCommand(CONTACT_STUB);

        ReadOnlyAddressBook initialAddressBook = model.getAddressBook();
        List<Person> initialPersonList = initialAddressBook.getPersonList();
        Person contactToStar = null;
        for (Person person : initialPersonList) {
            if (person.getName().fullName.equalsIgnoreCase(CONTACT_STUB)) {
                contactToStar = person;
                break;
            }
        }

        assertTrue(contactToStar != null); // Assert that the contact is found initially

        String expectedMessage = "Nice! I have starred this contact:\n" + contactToStar.getName() + " ★";
        Person expectedStarredContact = new Person(contactToStar.getName(), contactToStar.getPhone(),
                contactToStar.getEmail(), contactToStar.getAddress(), contactToStar.getCompany(),
                contactToStar.getPriority(), true, contactToStar.getTags());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(contactToStar, expectedStarredContact);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(starCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validContactName_starsContact() throws Exception {
        // Arrange
        String contactName = "Alex Tan";
        Person personToStar = new PersonBuilder().withName(contactName).build();
        Model model = new ModelManager();
        model.addPerson(personToStar);
        StarCommand starCommand = new StarCommand(contactName);

        // Act
        CommandResult commandResult = starCommand.execute(model);

        // Assert
        Person starredPerson = model.getFilteredPersonList().get(0);
        assertEquals("Nice! I have starred this contact:\nAlex Tan ★", commandResult.getFeedbackToUser());
        assertEquals(contactName, starredPerson.getName().fullName);
        assertEquals(true, starredPerson.isStarred());
    }

    @Test
    public void equals() {
        StarCommand starCommand1 = new StarCommand("Alex");
        StarCommand starCommand2 = new StarCommand("Alex");

        // same object -> returns true
        assertEquals(starCommand1, starCommand1);

        // same values -> returns true
        assertEquals(starCommand1, starCommand2);

        // different types -> returns false
        assertNotEquals(starCommand1, 1);

        // null -> returns false
        assertNotEquals(starCommand1, null);

        // different contactName -> returns false
        StarCommand differentStarCommand = new StarCommand("John");
        assertNotEquals(starCommand1, differentStarCommand);
    }
}
