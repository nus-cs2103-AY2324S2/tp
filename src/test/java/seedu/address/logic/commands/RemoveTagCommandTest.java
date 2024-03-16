package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class RemoveTagCommandTest {

    private static final String TAG_STUB_1 = "Some tag 1";

    private static final String TAG_STUB_2 = "Some tag 2";

    private static final String TAG_STUB_3 = "Some tag 3";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_removeTagUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person originalPerson = new PersonBuilder(firstPerson).withTags(TAG_STUB_1, TAG_STUB_2, TAG_STUB_3).build();
        Person editedPerson = new PersonBuilder(firstPerson).withTags(TAG_STUB_1).build();
        Set<Tag> tags = new HashSet<Tag>();
        tags.add(new Tag(TAG_STUB_3));
        tags.add(new Tag(TAG_STUB_2));
        RemoveTagCommand removeTagCommand = new RemoveTagCommand(INDEX_FIRST_PERSON, tags);

        String expectedMessage = String.format(RemoveTagCommand.MESSAGE_REMOVE_TAG_SUCCESS,
            tags.toString(), originalPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(originalPerson, editedPerson);

        assertCommandSuccess(removeTagCommand, model, expectedMessage, expectedModel);
    }

}
