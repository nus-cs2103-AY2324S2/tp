package seedu.address.logic.commands;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
public class SetCourseCommand extends Command {
    public static final String COMMAND_WORD = "setcrs";

    public static final String MESSAGE_ARGUMENTS = "Course: %1$s";

    public static final String MESSAGE_CONSTRAINTS =
            "Course code should follow the format \"XX1234Y\", Y is optional";

    private final Course course;

    public SetCourseCommand(Course course) {
        requireAllNonNull(course);
        this.course = course;
    }


    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Course command not implemented yet";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET + course);
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
