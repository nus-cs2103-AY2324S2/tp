package seedu.teachstack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.teachstack.logic.commands.exceptions.CommandException;
import seedu.teachstack.model.Model;
import seedu.teachstack.model.person.Grade;

/**
 * Sets threshold grade for weak students.
 */
public class SetWeakThresholdCommand extends Command {
    public static final String COMMAND_WORD = "setweak";

    public static final String MESSAGE_SET_THRESHOLD_SUCCESS = "Weakness Threshold was updated";
    public static final String MESSAGE_SET_THRESHOLD_FAIL = "ba";
    private Grade newThreshold;

    /**
     * @param grade new threshold grade
     */
    public SetWeakThresholdCommand(Grade grade) {
        requireNonNull(grade);
        newThreshold = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Grade.modifyThreshold(newThreshold);
        return new CommandResult(String.format(MESSAGE_SET_THRESHOLD_SUCCESS));
    }
}
