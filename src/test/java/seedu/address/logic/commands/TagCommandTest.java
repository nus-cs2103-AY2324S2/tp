package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

/**
 * Contains integration tests (interactions with the Model) and unit tests for TagCommand.
 */
class TagCommandTest {
    private static final Tag TAG_OWES_MONEY = new Tag("owesMoney");
    private static final Tag TAG_FRIENDS = new Tag("friends");
    private static final Collection<Tag> TAGS = List.of(TAG_OWES_MONEY, TAG_FRIENDS);

    private Model model;

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagCommand(null, null));
    }

    @Test
    public void execute_multipleTagsSpecified_success() {
        Index index = INDEX_SECOND_PERSON;
        TagCommand command = new TagCommand(index, TAGS);
        Person actualPerson = model.getFilteredPersonList().get(index.getZeroBased());

        Person editedPerson = new PersonBuilder(actualPerson).withTags().build();
        String expectedMessage = String.format(
                TagCommand.MESSAGE_TAG_CONTACT_SUCCESS,
                Messages.format(editedPerson));

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(actualPerson, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexOutOfBounds_failure() {
        Index index = Index.fromOneBased(999);
        TagCommand command = new TagCommand(index, TAGS);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON, TAGS);

        // same object -> equal
        assertEquals(standardCommand, standardCommand);

        // same values -> equal
        var commandWithSameValues = new TagCommand(INDEX_FIRST_PERSON, TAGS);
        assertEquals(standardCommand, commandWithSameValues);

        // tags in different order -> equal
        var tagsInDifferentOrder = List.of(TAG_FRIENDS, TAG_OWES_MONEY);
        assertEquals(standardCommand, new TagCommand(INDEX_FIRST_PERSON, tagsInDifferentOrder));

        // null -> not equal
        assertNotEquals(null, standardCommand);

        // different index -> not equal
        assertNotEquals(standardCommand, new TagCommand(INDEX_SECOND_PERSON, TAGS));

        // different tags -> not equal
        assertNotEquals(standardCommand, new TagCommand(INDEX_FIRST_PERSON, List.of(TAG_OWES_MONEY)));
    }
}
