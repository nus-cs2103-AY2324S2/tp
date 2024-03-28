package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;

/**
 * Deselects the currently selected exam.
 */
public class DeselectExamCommand extends Command {

    public static final String COMMAND_WORD = "deselectExam";

    public static final String MESSAGE_SUCCESS = "%s deselected";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getSelectedExam().getValue() == null) {
            throw new CommandException(Messages.MESSAGE_NO_EXAM_SELECTED);
        }
        Exam examToDeselect = model.getSelectedExam().getValue();
        model.deselectExam();
        return new CommandResult(String.format(MESSAGE_SUCCESS, examToDeselect.getName()));
    }

}
