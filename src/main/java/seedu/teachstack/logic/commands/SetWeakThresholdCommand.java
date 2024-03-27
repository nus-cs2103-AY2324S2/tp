package seedu.teachstack.logic.commands;

import seedu.teachstack.logic.commands.exceptions.CommandException;
import seedu.teachstack.model.Model;
import seedu.teachstack.model.group.Group;
import seedu.teachstack.model.person.Grade;
import seedu.teachstack.model.person.StudentId;

import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Sets threshold grade for weak students.
 */
public class SetWeakThresholdCommand {
    public static final String COMMAND_WORD = "set weak";

    public static final String MESSAGE_SET_THRESHOLD_SUCCESS = "Weakness Threshold was updated";

    private Grade newThreshold;

    /**
     * @param grade new threshold grade
     */
    public SetWeakThresholdCommand(String grade) {
        requireNonNull(grade);
        newThreshold = new Grade(grade);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

    }
}
