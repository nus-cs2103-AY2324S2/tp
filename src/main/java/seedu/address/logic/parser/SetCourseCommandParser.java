package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SetCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;

/**
 * Parses input arguments and creates a new {@code SetCourseCommand} object
 */
public class SetCourseCommandParser implements Parser<SetCourseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetCourseCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Course course;
        try {
            course = ParserUtil.parseCourse(args);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCourseCommand.MESSAGE_CONSTRAINTS), ive);
        }

        return new SetCourseCommand(course);
    }
}