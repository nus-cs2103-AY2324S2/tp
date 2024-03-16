package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class InternshipDeleteCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internship identified by the index number used in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "Deleted Internship: %1$s";

    private final Index targetIndex;

    public InternshipDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInternship(internshipToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS,
                InternshipMessages.format(internshipToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipDeleteCommand)) {
            return false;
        }

        InternshipDeleteCommand otherDeleteCommand = (InternshipDeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
