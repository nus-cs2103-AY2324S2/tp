package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPatient;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Deletes given Tags from a patient identified using it's displayed index from the address book.
 */
public class DeleteTagsCommand extends Command {

    public static final String COMMAND_WORD = "deletet";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified tags to the patient identified "
            + "by the index number used in the last patient listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "[TAG]+ \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "fallRisk";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted the tag: %2$s for Patient: %1$s"
            + " successfully";
    public static final String MESSAGE_INVALID_TAG = "The tag: %2$s does not exists"
        + " for Patient: %1$s";

    private final Index index;
    private final Set<Tag> tagsToDelete;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Constructs a DeleteTagsCommand to delete the specified {@code Tag} using
     * from the Patient with id {@code index}
     * @param index of the patient in the filtered patient list to delete the tags
     * @param tagsToDelete  to be deleted from the patient
     */
    public DeleteTagsCommand(Index index, Set<Tag> tagsToDelete) {
        requireAllNonNull(index, tagsToDelete);

        this.index = index;
        this.tagsToDelete = tagsToDelete;
        this.editPatientDescriptor = new EditPatientDescriptor();
    }

    /**
     * Executes the delete tag command to delete one or more tags to the patient.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A command result message indicating the success of the operation.
     * @throws CommandException If there is an error executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());

        // Create new Hashset for tags as Patient.getTags() return unmodifiableSet
        Set<Tag> tagSet = new HashSet<>(patientToEdit.getTags());

        Pair<Set<Tag>, String> result = deleteTagsFromPatient(patientToEdit, tagSet, tagsToDelete);
        Set<Tag> newTagSet = result.getKey();
        String commandResultString = result.getValue();

        editPatientDescriptor.setTags(newTagSet);

        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(commandResultString);
    }

    /**
     * Deletes specified tags from a patient's tag set.
     *
     * @param patient       The patient whose tags are being modified.
     * @param tagSet        The current set of tags for the patient.
     * @param toDeleteTags  The Tags to be deleted.
     * @return A Pair containing the updated tag set and a string describing the outcome of the deletion.
     * @throws CommandException if tag does not exist.
     */
    public Pair<Set<Tag>, String> deleteTagsFromPatient(Patient patient, Set<Tag> tagSet,
                                                       Set<Tag> toDeleteTags) throws CommandException {
        requireAllNonNull(tagSet, toDeleteTags);

        StringBuilder commandOutcome = new StringBuilder();

        for (Tag tag : toDeleteTags) {
            if (!tagSet.contains(tag)) {
                commandOutcome.append(String.format(MESSAGE_INVALID_TAG, patient.getName(), tag)).append("\n");
            } else {
                tagSet.remove(tag);
                commandOutcome.append(String.format(MESSAGE_DELETE_TAG_SUCCESS, patient.getName(), tag)).append("\n");
            }
        }
        return new Pair<>(tagSet, commandOutcome.toString());
    }

    /**
     * Returns true if both delete tag commands have the same index and tags to delete.
     *
     * @param other Another object to compare to.
     * @return True if the other object is an DeleteTagsCommand with the same index and tags to add.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagsCommand)) {
            return false;
        }

        DeleteTagsCommand otherTagCommand = (DeleteTagsCommand) other;
        return index.equals(otherTagCommand.index)
                && tagsToDelete.equals(otherTagCommand.tagsToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetPatientIndex", index)
                .add("tags", tagsToDelete)
                .toString();
    }
}
