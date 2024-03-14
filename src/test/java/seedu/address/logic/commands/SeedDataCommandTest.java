package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

public class SeedDataCommandTest {

    private static final Person[] sampleData = SampleDataUtil.getSamplePersons();

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = model;

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithSomeContacts_success() {
        Model model = new ModelManager();
        model.addPerson(sampleData[0]);
        model.addPerson(sampleData[1]);
        model.addPerson(sampleData[3]);
        Model expectedModel = model;

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithAllContacts_failure() {
        Model model = new ModelManager();
        for (Person person: sampleData) {
            model.addPerson(person);
        }
        Model expectedModel = model;

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_FAILURE, expectedModel);
    }


}
