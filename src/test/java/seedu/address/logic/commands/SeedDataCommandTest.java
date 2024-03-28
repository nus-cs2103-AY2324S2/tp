package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class SeedDataCommandTest {

    private static final Person[] sampleData = SampleDataUtil.getSamplePersons();

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();

        Model expectedModel = new ModelManager();
        for (Person person: sampleData) {
            expectedModel.addPerson(person);
        }

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithSomeContacts_success() {
        Model model = new ModelManager();
        model.addPerson(sampleData[0]);
        model.addPerson(sampleData[1]);
        model.addPerson(sampleData[3]);

        Model expectedModel = new ModelManager();

        // pre-existing contacts
        expectedModel.addPerson(sampleData[0]);
        expectedModel.addPerson(sampleData[1]);
        expectedModel.addPerson(sampleData[3]);
        // new contacts added
        for (int i = 0; i < sampleData.length; i++) {
            if (i != 0 && i != 1 && i != 3) {
                expectedModel.addPerson(sampleData[i]);
            }
        }

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithAllContacts_failure() {
        Model model = new ModelManager();
        for (Person person: sampleData) {
            model.addPerson(person);
        }

        assertCommandFailure(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_FAILURE);
    }

}
