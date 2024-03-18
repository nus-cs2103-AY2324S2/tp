package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.course.Course.isValidCode;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;



/**
 * Represents a command to set the current course in the application.
 * This command updates the application's state to reflect the specified course.
 * The command follows a specific format for the course code, which should match "XX1234Y",
 * where "Y" is optional. It is designed to handle the action triggered by the user input
 * corresponding to setting a new course within the application.
 */
public class SetCourseCommand extends Command {
    public static final String COMMAND_WORD = "setcrs";

    public static final String MESSAGE_ARGUMENTS = "Course: %1$s";

    public static final String MESSAGE_CONSTRAINTS =
            "Course code should follow the format \"XX1234Y\", Y is optional";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Sets course";

    public static final String MESSAGE_SUCCESS = "Successfully updated course";


    private final Course course;

    /**
     * Creates a SetCourseCommand with the specified course.
     * This constructor initializes a new instance of SetCourseCommand with a given Course object.
     * It requires the Course object to be non-null and expects it to adhere to the defined course
     * code constraints outlined.
     *
     * @param course The course to be set by this command. Must not be null and should take the courses
     */
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
