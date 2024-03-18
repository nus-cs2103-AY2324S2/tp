package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION_OUTPUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

public class DeleteTagsCommandTest {

    private final Set<Tag> validTagSet = Set.of(new Tag("test"));
    private Model model;
    private final UserPrefs userPrefs = new UserPrefs();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), userPrefs);
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagsCommand(null, validTagSet));
    }

    @Test
    public void constructor_nullTagSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagsCommand(INDEX_FIRST_PATIENT, null));
    }

    @Test
    public void constructor_nullIndexAndNullTagSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagsCommand(null, null));
    }

    @Test
    public void execute_validIndexAndTags_success() {
        Index index = INDEX_FIRST_PATIENT;
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_DEPRESSION));

        DeleteTagsCommand deleteTagsCommand = new DeleteTagsCommand(index, tagsToDelete);

        try {
            CommandResult commandResult = deleteTagsCommand.execute(model);
            Patient editedPatient = model.getFilteredPatientList().get(index.getZeroBased());
            String expectedMessage = String.format(DeleteTagsCommand.MESSAGE_DELETE_TAG_SUCCESS,
                    editedPatient.getName(), VALID_TAG_DEPRESSION_OUTPUT) + "\n";
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());

            Set<Tag> expectedTagsSet = new HashSet<>(editedPatient.getTags());

            assertFalse(expectedTagsSet.contains(new Tag(VALID_TAG_DEPRESSION)));
        } catch (CommandException e) {
            // The command should not throw an exception in this test
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_DEPRESSION));
        DeleteTagsCommand deleteTagsCommand = new DeleteTagsCommand(outOfBoundIndex, tagsToDelete);

        CommandException exception = assertThrows(CommandException.class, () -> deleteTagsCommand.execute(model));
        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_invalidTags_logsOutcome() {
        Index index = INDEX_SECOND_PATIENT;
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag("nonExistentTag"));
        DeleteTagsCommand deleteTagsCommand = new DeleteTagsCommand(index, tagsToDelete);

        try {
            CommandResult commandResult = deleteTagsCommand.execute(model);
            String expectedMessage = String.format(DeleteTagsCommand.MESSAGE_INVALID_TAG,
                    model.getFilteredPatientList().get(index.getZeroBased()).getName(), "[nonExistentTag]") + "\n";
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }


    @Test
    public void equals() {
        final Index index = INDEX_FIRST_PATIENT;
        final Set<Tag> firstTags = new HashSet<>();
        firstTags.add(new Tag(VALID_TAG_DEPRESSION));
        final Set<Tag> secondTags = new HashSet<>();
        secondTags.add(new Tag(VALID_TAG_DEPRESSION));

        final DeleteTagsCommand standardCommand = new DeleteTagsCommand(index, firstTags);

        // same values -> returns true
        DeleteTagsCommand commandWithSameValues = new DeleteTagsCommand(index, secondTags);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteTagsCommand(INDEX_SECOND_PATIENT, firstTags)));

        // different tags -> returns false
        assertFalse(standardCommand.equals(new DeleteTagsCommand(index, new HashSet<>())));
    }

    @Test
    public void toStringTest() {
        Index index = Index.fromOneBased(1);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_DEPRESSION));
        tags.add(new Tag(VALID_TAG_DIABETES));

        DeleteTagsCommand deleteTagsCommand = new DeleteTagsCommand(index, tags);

        String expected = DeleteTagsCommand.class.getCanonicalName() + "{targetPatientIndex=" + index + ", tags="
                + tags + "}";

        assertEquals(expected, deleteTagsCommand.toString());
    }

}
