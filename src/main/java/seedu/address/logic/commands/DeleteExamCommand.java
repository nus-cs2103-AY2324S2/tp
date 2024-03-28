package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;

/**
 * Deletes an exam identified using it's displayed index from the address book.
 */
public class DeleteExamCommand extends Command {

    public static final String COMMAND_WORD = "deleteExam";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the exam identified by the index number used in the displayed exam list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXAM_SUCCESS = "Deleted Exam: %1$s";

    private final Index targetIndex;

    public DeleteExamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exam> examList = model.getExamList();

        if (targetIndex.getZeroBased() >= examList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }

        Exam examToDelete = examList.get(targetIndex.getZeroBased());
        model.deleteExam(examToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXAM_SUCCESS, Messages.format(examToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteExamCommand)) {
            return false;
        }

        DeleteExamCommand otherDeleteExamCommand = (DeleteExamCommand) other;
        return targetIndex.equals(otherDeleteExamCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
