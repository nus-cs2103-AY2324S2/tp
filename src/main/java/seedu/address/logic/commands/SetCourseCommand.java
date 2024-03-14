package seedu.address.logic.commands;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.course.Course.isValidCode;

public class SetCourseCommand extends Command {
    public static final String COMMAND_WORD = "setcrs";

    public static final String MESSAGE_ARGUMENTS = "Course: %1$s";

    public static final String MESSAGE_CONSTRAINTS =
            "Course code should follow the format \"XX1234Y\", Y is optional";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Sets course";

    public static final String MESSAGE_SUCCESS = "Successfully updated course";


    private final Course course;

    public SetCourseCommand(Course course) {
        requireAllNonNull(course);
        this.course = course;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!isValidCode(course.toString())) {
            throw new CommandException(MESSAGE_CONSTRAINTS);
        }
        model.changeCode(course.toString());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetCourseCommand)) {
            return false;
        }

        // state check
        SetCourseCommand e = (SetCourseCommand) other;
        return course.equals(e.course);

    }

}
