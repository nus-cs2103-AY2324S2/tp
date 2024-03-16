package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NICE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

public class AddTagsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTagsUnfilteredList_success() {
        Index index = INDEX_FIRST_PATIENT;
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_NICE));

        AddTagsCommand addTagsCommand = new AddTagsCommand(index, tagsToAdd);

        try {
            CommandResult commandResult = addTagsCommand.execute(model);
            Patient editedPatient = model.getFilteredPatientList().get(index.getZeroBased());
            String expectedMessage = String.format(AddTagsCommand.MESSAGE_ADD_TAG_SUCCESS, editedPatient.getName());
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());

            Set<Tag> expectedTagsSet = new HashSet<>(editedPatient.getTags());
            expectedTagsSet.add(new Tag(VALID_TAG_NICE));

            assertTrue(editedPatient.getTags().equals(expectedTagsSet));
        } catch (CommandException e) {
            // The command should not throw an exception in this test
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    @Test
    public void execute_addDuplicateTagsUnfilteredList_success() {
        Index index = INDEX_FIRST_PATIENT;
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_NICE));
        tagsToAdd.add(new Tag(VALID_TAG_NICE)); // Adding a duplicate tag should result in one unique tag

        AddTagsCommand addTagsCommand = new AddTagsCommand(index, tagsToAdd);

        try {
            CommandResult commandResult = addTagsCommand.execute(model);
            Patient editedPatient = model.getFilteredPatientList().get(index.getZeroBased());
            String expectedMessage = String.format(AddTagsCommand.MESSAGE_ADD_TAG_SUCCESS, editedPatient.getName());
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());

            Set<Tag> expectedTagsSet = new HashSet<>(editedPatient.getTags());
            expectedTagsSet.add(new Tag(VALID_TAG_NICE));

            assertTrue(editedPatient.getTags().equals(expectedTagsSet));
        } catch (CommandException e) {
            // The command should not throw an exception in this test
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    @Test
    public void execute_addPreExistingTagsUnfilteredList_success() {
        Index index = INDEX_FIRST_PATIENT;
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_FRIENDS));

        AddTagsCommand addTagsCommand = new AddTagsCommand(index, tagsToAdd);

        try {
            CommandResult commandResult = addTagsCommand.execute(model);
            Patient editedPatient = model.getFilteredPatientList().get(index.getZeroBased());
            String expectedMessage = String.format(AddTagsCommand.MESSAGE_ADD_TAG_SUCCESS, editedPatient.getName());
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());

            Set<Tag> expectedTagsSet = new HashSet<>(editedPatient.getTags());

            assertTrue(editedPatient.getTags().equals(expectedTagsSet));
        } catch (CommandException e) {
            // The command should not throw an exception in this test
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_FRIENDS));
        AddTagsCommand addTagsCommand = new AddTagsCommand(outOfBoundIndex, tagsToAdd);

        CommandException exception = assertThrows(CommandException.class, () -> addTagsCommand.execute(model));
        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void equals() {
        final Index index = INDEX_FIRST_PATIENT;
        final Set<Tag> firstTags = new HashSet<>();
        firstTags.add(new Tag(VALID_TAG_FRIENDS));
        final Set<Tag> secondTags = new HashSet<>();
        secondTags.add(new Tag(VALID_TAG_FRIENDS));

        final AddTagsCommand standardCommand = new AddTagsCommand(index, firstTags);

        // same values -> returns true
        AddTagsCommand commandWithSameValues = new AddTagsCommand(index, secondTags);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTagsCommand(INDEX_SECOND_PATIENT, firstTags)));

        // different tags -> returns false
        assertFalse(standardCommand.equals(new AddTagsCommand(index, new HashSet<>())));
    }

    @Test
    public void toStringTest() {
        Index index = Index.fromOneBased(1);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_FRIENDS));
        tags.add(new Tag(VALID_TAG_NICE));

        AddTagsCommand addTagsCommand = new AddTagsCommand(index, tags);

        String expected = AddTagsCommand.class.getCanonicalName() + "{index=" + index + ", tags="
                + tags + "}";

        assertEquals(expected, addTagsCommand.toString());
    }

}
