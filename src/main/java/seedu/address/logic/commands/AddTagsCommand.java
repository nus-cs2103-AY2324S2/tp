package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPatient;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Adds one or more tags to the specified patient.
 * Repeated tags in command will be added as a single tag.
 * If the patient already has the tag, it will not be added.
 */
public class AddTagsCommand extends Command {

    public static final String COMMAND_WORD = "addt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds one or more tags to the patient identified "
            + "by the index number used in the last patient listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "[TAG]+ \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "fall risk";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added the tag: %2$s for Patient: %1$s"
            + " successfully";
    public static final String MESSAGE_DUPLICATE_TAG = "The tag: %2$s already exists"
            + " for Patient: %1$s";

    private static final Logger logger = LogsCenter.getLogger(AddTagsCommand.class);
    private final Index index;
    private final Set<Tag> tagsToAdd;
    private final EditPatientDescriptor editPatientDescriptor;


    /**
     * @param index of the patient in the filtered patient list to add the tags
     * @param tagsToAdd  to be added to the patient
     */
    public AddTagsCommand(Index index, Set<Tag> tagsToAdd) {
        requireAllNonNull(index, tagsToAdd);

        this.index = index;
        this.tagsToAdd = tagsToAdd;
        this.editPatientDescriptor = new EditPatientDescriptor();
    }

    /**
     * Executes the add tag command to add one or more tags to the patient.
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
            logger.log(Level.WARNING, "Invalid patient index for Add Tags Command: " + index);
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());

        // Create new Hashset to add in new tags as Patient.getTags() return unmodifiableSet
        Set<Tag> tagSet = new HashSet<>(patientToEdit.getTags());
        Pair<Set<Tag>, String> result = addTagsToPatient(patientToEdit, tagSet, tagsToAdd);
        Set<Tag> newTagSet = result.getKey();
        String commandResultString = result.getValue();

        editPatientDescriptor.setTags(newTagSet);

        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        logger.log(Level.INFO, "Successfully called AddTagsCommand for patient: " + editedPatient.getName());
        return new CommandResult(commandResultString);
    }

    /**
     * Adds the specified tags to a patient's tag set.
     *
     * @param patient    The patient whose tags are being modified.
     * @param tagSet     The current set of tags for the patient.
     * @param toAddTags  The Tags to be added.
     * @return A Pair containing the updated tag set and a string describing the outcome of the deletion.
     */
    public Pair<Set<Tag>, String> addTagsToPatient(Patient patient, Set<Tag> tagSet,
                                                        Set<Tag> toAddTags) {
        requireAllNonNull(tagSet, toAddTags);

        StringBuilder commandOutcome = new StringBuilder();

        for (Tag tag : toAddTags) {
            assert tag != null : "Tag cannot be null";
            if (tagSet.contains(tag)) {
                commandOutcome.append(String.format(MESSAGE_DUPLICATE_TAG, patient.getName(), tag)).append("\n");
            } else {
                tagSet.add(tag);
                commandOutcome.append(String.format(MESSAGE_ADD_TAG_SUCCESS, patient.getName(), tag)).append("\n");
            }
        }
        return new Pair<>(tagSet, commandOutcome.toString());
    }

    /**
     * Returns true if both add tag commands have the same index and tags to add.
     *
     * @param other Another object to compare to.
     * @return True if the other object is an AddTagsCommand with the same index and tags to add.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddTagsCommand)) {
            return false;
        }

        AddTagsCommand otherTagCommand = (AddTagsCommand) other;
        return index.equals(otherTagCommand.index)
                && tagsToAdd.equals(otherTagCommand.tagsToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tags", tagsToAdd)
                .toString();
    }
}
