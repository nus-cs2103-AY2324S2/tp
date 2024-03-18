package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class RemoveTagCommandTest {

    private static final String TAG_STUB_1 = "Sometag1";

    private static final String TAG_STUB_2 = "Sometag2";

    private static final String TAG_STUB_3 = "Sometag3";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_removeTagFilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person originalPerson = new PersonBuilder(firstPerson).withTags(TAG_STUB_1, TAG_STUB_2, TAG_STUB_3).build();
        model.setPerson(firstPerson, originalPerson);
        Person editedPerson = new PersonBuilder(firstPerson).withTags(TAG_STUB_1).build();
        Set<Tag> tags = new HashSet<Tag>();
        tags.add(new Tag(TAG_STUB_2));
        tags.add(new Tag(TAG_STUB_3));
        RemoveTagCommand removeTagCommand = new RemoveTagCommand(INDEX_FIRST_PERSON, tags);

        String expectedMessage = String.format(RemoveTagCommand.MESSAGE_REMOVE_TAG_SUCCESS,
            tags.toString(), originalPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(originalPerson, editedPerson);

        assertCommandSuccess(removeTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        int outOfBoundIndex = model.getFilteredPersonList().size() + 1;
        RemoveTagCommand removeTagCommand = new RemoveTagCommand(
            Index.fromOneBased(outOfBoundIndex), new HashSet<Tag>());
        assertCommandFailure(removeTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_tagDoesNotExist_throwsCommandException() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person originalPerson = new PersonBuilder(firstPerson).withTags(TAG_STUB_1).build();
        model.setPerson(firstPerson, originalPerson);
        Set<Tag> tags = new HashSet<Tag>();
        Tag tag2 = new Tag(TAG_STUB_2);
        tags.add(tag2);
        RemoveTagCommand removeTagCommand = new RemoveTagCommand(INDEX_FIRST_PERSON, tags);
        assertCommandFailure(removeTagCommand, model,
            RemoveTagCommand.MESSAGE_TAG_DOES_NOT_EXIST + " " + tag2.toString());
    }

    @Test
    public void toString_validCommand_success() {
        Set<Tag> tags = new HashSet<Tag>();
        tags.add(new Tag(TAG_STUB_1));
        RemoveTagCommand removeTagCommand = new RemoveTagCommand(INDEX_FIRST_PERSON, tags);
        System.out.println(removeTagCommand.toString());
        assert(removeTagCommand.toString().equals(
        "seedu.address.logic.commands.RemoveTagCommand{targetIndex=seedu.address.commons.core.index."
        + "Index{zeroBasedIndex=0}, tags=[[Sometag1]]}"
        ));
    }

    @Test
    public void equals_validCommand_success() {
        Set<Tag> tags = new HashSet<Tag>();
        tags.add(new Tag(TAG_STUB_1));
        RemoveTagCommand removeTagCommand = new RemoveTagCommand(INDEX_FIRST_PERSON, tags);
        RemoveTagCommand removeTagCommand2 = new RemoveTagCommand(INDEX_FIRST_PERSON, tags);
        assertTrue(removeTagCommand.equals(removeTagCommand2));
    }


}
