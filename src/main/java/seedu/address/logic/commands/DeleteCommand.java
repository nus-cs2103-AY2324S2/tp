package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;

/**
 * Deletes a courseMate identified using it's displayed index from the contact list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the courseMate identified by the index number used in the displayed courseMate list.\n"
            + "NAME can be specified either by full name or by the '#' notation.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " #1";

    public static final String MESSAGE_DELETE_COURSE_MATE_SUCCESS = "Deleted CourseMate";

    private final QueryableCourseMate queryableCourseMate;

    public DeleteCommand(QueryableCourseMate queryableCourseMate) {
        this.queryableCourseMate = queryableCourseMate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CourseMate> courseMateToDeleteList;

        try {
            courseMateToDeleteList = model.findCourseMate(queryableCourseMate);
        } catch (CourseMateNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        }

        //If there are more than 1 matching names
        if (courseMateToDeleteList.size() > 1) {
            return new SimilarNameCommand(queryableCourseMate).execute(model);
        }

        CourseMate courseMateToDelete = courseMateToDeleteList.get(0);
        model.deleteCourseMate(courseMateToDelete);
        model.setRecentlyProcessedCourseMate(courseMateToDelete);
        return new CommandResult(MESSAGE_DELETE_COURSE_MATE_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return queryableCourseMate.equals(otherDeleteCommand.queryableCourseMate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("queryableCourseMateIndex", queryableCourseMate.getIndex())
                .toString();
    }
}
