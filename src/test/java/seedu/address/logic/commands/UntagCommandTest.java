package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
 * Contains integration tests (interactions with the Model) and unit tests for UntagCommand.
 */
class UntagCommandTest {
    private static final Tag TAG_OWES_MONEY = new Tag("owesMoney");
    private static final Tag TAG_FRIENDS = new Tag("friends");
    private static final Collection<Tag> TAGS = List.of(TAG_OWES_MONEY, TAG_FRIENDS);

    private Model model;

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_tagMissing_failure() {
        var index = INDEX_FIRST_PERSON;
        var personName = model.getFilteredPersonList().get(index.getZeroBased()).getName();
        var command = new UntagCommand(index, TAGS);

        var expectedMessage = String.format(
                Messages.MESSAGE_MISSING_TAG,
                personName,
                "owesMoney");

        assertCommandFailure(command, model, new CommandHistory(), expectedMessage);
    }

    @Test
    public void execute_indexOutOfBounds_failure() {
        var index = Index.fromOneBased(999);
        var command = new UntagCommand(index, TAGS);

        var expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(command, model, new CommandHistory(), expectedMessage);
    }

    @Test
    public void equals() {
        final UntagCommand standardCommand = new UntagCommand(INDEX_FIRST_PERSON, TAGS);

        // same object -> equal
        assertEquals(standardCommand, standardCommand);

        // same values -> equal
        var commandWithSameValues = new UntagCommand(INDEX_FIRST_PERSON, TAGS);
        assertEquals(standardCommand, commandWithSameValues);

        // tags in different order -> equal
        var tagsInDifferentOrder = List.of(TAG_FRIENDS, TAG_OWES_MONEY);
        assertEquals(standardCommand, new UntagCommand(INDEX_FIRST_PERSON, tagsInDifferentOrder));

        // null -> not equal
        assertNotEquals(null, standardCommand);

        // different types -> not equal
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> not equal
        assertNotEquals(standardCommand, new UntagCommand(INDEX_SECOND_PERSON, TAGS));

        // different tags -> not equal
        assertNotEquals(standardCommand, new UntagCommand(INDEX_FIRST_PERSON, List.of(TAG_OWES_MONEY)));
    }
}
