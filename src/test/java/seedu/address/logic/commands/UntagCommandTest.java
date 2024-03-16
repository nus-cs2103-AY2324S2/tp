package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interactions with the Model) and unit tests for UntagCommand.
 */
class UntagCommandTest {
    private static final Tag TAG_FRIEND = new Tag(VALID_TAG_FRIEND);
    private static final Tag TAG_HUSBAND = new Tag(VALID_TAG_HUSBAND);
    private static final Collection<Tag> TAGS = List.of(TAG_FRIEND, TAG_HUSBAND);

    private Model model;

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_multipleTagsSpecified_success() {
        List<Tag> tags = List.of(new Tag("owesMoney"), new Tag("friends"));
        var index = INDEX_SECOND_PERSON;
        var command = new UntagCommand(index, tags);

        var actualPerson = model.getFilteredPersonList().get(index.getZeroBased());

        var editedPerson = new PersonBuilder(actualPerson).withTags().build();
        var expectedMessage = String.format(
                UntagCommand.MESSAGE_DELETE_TAG_SUCCESS,
                Messages.format(editedPerson),
                "friends, owesMoney");

        var expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(actualPerson, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagMissing_failure() {
        var index = INDEX_FIRST_PERSON;
        List<Tag> tags = List.of(new Tag("owesMoney"), new Tag("friends"));

        var personName = model.getFilteredPersonList().get(index.getZeroBased()).getName();

        var command = new UntagCommand(index, tags);

        var expectedMessage = String.format(
                Messages.MESSAGE_MISSING_TAG,
                personName,
                "owesMoney");

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        final UntagCommand standardCommand = new UntagCommand(INDEX_FIRST_PERSON, TAGS);

        // same values -> equal
        var commandWithSameValues = new UntagCommand(INDEX_FIRST_PERSON, TAGS);
        assertEquals(standardCommand, commandWithSameValues);

        // tags in different order -> equal
        var tagsInDifferentOrder = List.of(TAG_HUSBAND, TAG_FRIEND);
        assertEquals(standardCommand, new UntagCommand(INDEX_FIRST_PERSON, tagsInDifferentOrder));

        // null -> not equal
        assertNotEquals(null, standardCommand);

        // different types -> not equal
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> not equal
        assertNotEquals(standardCommand, new UntagCommand(INDEX_SECOND_PERSON, TAGS));

        // different tags -> not equal
        assertNotEquals(standardCommand, new UntagCommand(INDEX_FIRST_PERSON, List.of(TAG_FRIEND)));
    }
}
