package seedu.findvisor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.logic.commands.CommandTestUtil.ARRAY_OF_MULTIPLE_VALID_TAG_STRINGS;
import static seedu.findvisor.logic.commands.CommandTestUtil.ARRAY_OF_SINGLE_VALID_TAG_STRING;
import static seedu.findvisor.logic.commands.CommandTestUtil.SET_OF_VALID_TAG;
import static seedu.findvisor.logic.commands.CommandTestUtil.SET_OF_VALID_TAGS;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.findvisor.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.findvisor.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.Messages;
import seedu.findvisor.model.AddressBook;
import seedu.findvisor.model.Model;
import seedu.findvisor.model.ModelManager;
import seedu.findvisor.model.UserPrefs;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.tag.Tag;
import seedu.findvisor.testutil.PersonBuilder;
public class AddtagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddtagCommand(null, SET_OF_VALID_TAGS));
    }

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddtagCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexUnfilteredListAddMultipleTags_success() {
        Person personToAddTagTo = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddtagCommand addtagCommand = new AddtagCommand(INDEX_FIRST_PERSON, SET_OF_VALID_TAGS);

        PersonBuilder personBuilder = new PersonBuilder(personToAddTagTo).addTags(ARRAY_OF_MULTIPLE_VALID_TAG_STRINGS);
        Person editedPerson = personBuilder.build();

        String expectedMessage = String.format(AddtagCommand.MESSAGE_ADD_TAGS_TO_PERSON_SUCCESS,
                Messages.format(editedPerson));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(addtagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredListAddMultipleTags_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).addTags(
                ARRAY_OF_MULTIPLE_VALID_TAG_STRINGS).build();
        AddtagCommand addtagCommand = new AddtagCommand(INDEX_FIRST_PERSON, SET_OF_VALID_TAGS);

        String expectedMessage = String.format(AddtagCommand.MESSAGE_ADD_TAGS_TO_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(addtagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListAddSingleTag_success() {
        Person personToAddTagTo = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddtagCommand addtagCommand = new AddtagCommand(INDEX_FIRST_PERSON, SET_OF_VALID_TAG);

        PersonBuilder personBuilder = new PersonBuilder(personToAddTagTo).addTags(ARRAY_OF_SINGLE_VALID_TAG_STRING);
        Person editedPerson = personBuilder.build();

        String expectedMessage = String.format(AddtagCommand.MESSAGE_ADD_TAGS_TO_PERSON_SUCCESS,
                Messages.format(editedPerson));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(addtagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredListAddSingleTag_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).addTags(
                ARRAY_OF_SINGLE_VALID_TAG_STRING).build();
        AddtagCommand addtagCommand = new AddtagCommand(INDEX_FIRST_PERSON, SET_OF_VALID_TAG);

        String expectedMessage = String.format(AddtagCommand.MESSAGE_ADD_TAGS_TO_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(addtagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredListIndexOutOfBounds_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        AddtagCommand addtagCommand = new AddtagCommand(INDEX_THIRD_PERSON, SET_OF_VALID_TAG);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertCommandFailure(addtagCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final AddtagCommand standardAddtagCommand = new AddtagCommand(INDEX_FIRST_PERSON, SET_OF_VALID_TAGS);

        // same values -> returns true
        AddtagCommand addtagCommandWithSameValues = new AddtagCommand(INDEX_FIRST_PERSON, SET_OF_VALID_TAGS);
        assertTrue(standardAddtagCommand.equals(addtagCommandWithSameValues));

        // same object -> returns true
        assertTrue(standardAddtagCommand.equals(standardAddtagCommand));

        // null -> returns false
        assertFalse(standardAddtagCommand.equals(null));

        // different command type -> returns false
        assertFalse(standardAddtagCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardAddtagCommand.equals(new AddtagCommand(INDEX_SECOND_PERSON, SET_OF_VALID_TAGS)));

        // different tag set -> returns false
        assertFalse(standardAddtagCommand.equals(new AddtagCommand(INDEX_FIRST_PERSON, SET_OF_VALID_TAG)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Set<Tag> setOfTags = SET_OF_VALID_TAGS;
        AddtagCommand addTagCommand = new AddtagCommand(index, setOfTags);
        String expected = AddtagCommand.class.getCanonicalName() + "{index=" + index + ", tags=" + setOfTags + "}";
        assertEquals(expected, addTagCommand.toString());
    }
}
