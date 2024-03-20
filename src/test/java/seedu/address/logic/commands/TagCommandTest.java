package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

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
    public void execute_indexOutOfBounds_failure() {
        Index index = Index.fromOneBased(999);
        TagCommand command = new TagCommand(index, TAGS);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(command, model, new CommandHistory(), expectedMessage);
    }
    @Test
    public void showTags_singleTag_returnsSingleTag() {
        // Create a collection containing a single tag
        Collection<Tag> tags = Arrays.asList(new Tag("friend"));

        // Call the showTags method
        String result = TagCommand.showTags(tags);

        // Verify that the result is the same as the single tag
        assertEquals("friend", result);
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
