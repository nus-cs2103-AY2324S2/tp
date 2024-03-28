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
 * Selects an exam identified using it's displayed index from the address book.
 */
public class SelectExamCommand extends Command {

    public static final String COMMAND_WORD = "selectExam";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the exam identified by the index number used in the displayed exam list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_EXAM_SUCCESS = "Selected Exam: %1$s";

    private final Index targetIndex;

    public SelectExamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exam> lastShownList = model.getExamList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }

        Exam examToSelect = lastShownList.get(targetIndex.getZeroBased());
        model.selectExam(examToSelect);
        return new CommandResult(String.format(MESSAGE_SELECT_EXAM_SUCCESS, examToSelect));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SelectExamCommand)) {
            return false;
        }

        SelectExamCommand otherSelectExamCommand = (SelectExamCommand) other;
        return targetIndex.equals(otherSelectExamCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
