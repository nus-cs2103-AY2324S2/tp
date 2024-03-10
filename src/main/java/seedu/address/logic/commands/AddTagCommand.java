package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds one or more tags to the specified patient.
 */
public class AddTagCommand extends Command {

    // IMPORTANT : tags can only be a single word, with no space in between

    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds one or more tags (single word) to a person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "[TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "fallrisk";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Tags: %2$s";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Tags %1$s successfully added for Patient %2$s";
    public static final String MESSAGE_DUPLICATE_TAGS = "Tag %s already exists for this Patient.";

    private final Index index;
    private final Set<Tag> tags;

    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index of the patient in the filtered patient list to add the tags
     * @param editPatientDescriptor details to edit the patient with
     */
    public AddTagCommand(Index index, ) {
        requireAllNonNull(index, editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
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
        // throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), tags));

        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPatientFromNewTag(patientToEdit, editPatientDescriptor);
        // Set<Tag> patientToEditExistingTags = patientToEdit.getTags();

        Patient editedPatient = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(),
                patientToEdit.getAddress(), patientToEdit.getTags());

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(generateSuccessMessage(editedPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatientFromNewTag(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Address updatedAddress = editPatientDescriptor.getAddress().orElse(patientToEdit.getAddress());
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(patientToEdit.getTags());

        return new Patient(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    /**
     * Returns true if both add tag commands have the same index and tags to add.
     *
     * @param other Another object to compare to.
     * @return True if the other object is an AddTagCommand with the same index and tags to add.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        AddTagCommand otherTagCommand = (AddTagCommand) other;
        return index.equals(otherTagCommand.index)
                && editPatientDescriptor.equals(otherTagCommand.editPatientDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPatientDescriptor", editPatientDescriptor)
                .toString();
    }
}
