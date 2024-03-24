package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
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
import seedu.address.testutil.TagBuilder;

public class AddTagsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addTagsUnfilteredList_success() {
        List<String> tagList = List.of("car", "health");
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).addTags(tagList).build();

        AddTagsCommand command = new AddTagsCommand(INDEX_FIRST_PERSON,
                TagBuilder.build(tagList));

        String expectedMessage = String.format(AddTagsCommand.MESSAGE_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        List<String> tagList = List.of("car", "health");
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddTagsCommand command = new AddTagsCommand(outOfBoundIndex, TagBuilder.build(tagList));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        List<String> tagList = List.of("car", "health");

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTagsCommand command = new AddTagsCommand(outOfBoundIndex, TagBuilder.build(tagList));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        List<String> tagList1 = List.of("car", "health");
        List<String> tagList2 = List.of("covid", "health");

        AddTagsCommand firstCommand = new AddTagsCommand(INDEX_FIRST_PERSON, TagBuilder.build(tagList1));
        AddTagsCommand secondCommand = new AddTagsCommand(INDEX_FIRST_PERSON, TagBuilder.build(tagList2));

        assertTrue(firstCommand.equals(firstCommand));
        AddTagsCommand firstCommandCopy = new AddTagsCommand(INDEX_FIRST_PERSON, TagBuilder.build(tagList1));
        assertTrue(firstCommand.equals(firstCommandCopy));
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void toStringMethod() {
        List<String> tagList = List.of("car", "health");
        Set<Tag> tags = TagBuilder.build(tagList);
        Index index = Index.fromOneBased(1);
        AddTagsCommand command = new AddTagsCommand(index, tags);
        String expected = AddTagsCommand.class.getCanonicalName() + "{index=" + index + ", tags=" + tags + "}";
        assertEquals(expected, command.toString());
    }

}
