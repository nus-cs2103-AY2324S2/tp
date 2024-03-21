package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.InternshipModel.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Remark;

/**
 * Changes the remark of an existing internship in the internship.
 */
public class InternshipRemarkCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "addremark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the internship identified "
            + "by the index number used in the last internship listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + " [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + " Has a behavioural interview.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Internship: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Internship: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the internship in the filtered internship list to edit the remark
     * @param remark of the internship to be updated to
     */
    public InternshipRemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        Internship editedInternship = new Internship(
                internshipToEdit.getCompanyName(), internshipToEdit.getContactName(),
                internshipToEdit.getContactEmail(), internshipToEdit.getContactNumber(),
                internshipToEdit.getLocation(), internshipToEdit.getApplicationStatus(),
                internshipToEdit.getDescription(), internshipToEdit.getRole(),
                remark);

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(generateSuccessMessage(editedInternship));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code internshipToEdit}.
     */
    private String generateSuccessMessage(Internship internshipToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, internshipToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipRemarkCommand)) {
            return false;
        }

        // state check
        InternshipRemarkCommand e = (InternshipRemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
