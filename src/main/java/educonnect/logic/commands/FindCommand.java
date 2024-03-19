package educonnect.logic.commands;

import static java.util.Objects.requireNonNull;

import educonnect.commons.util.ToStringBuilder;
import educonnect.logic.Messages;
import educonnect.model.Model;
import educonnect.model.student.predicates.NameContainsKeywordsPredicate;

/**
 * Finds and lists all students in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose criteria matches the "
            + "the specified keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: [n/NAME] [s/STUDENT_ID] [h/TELEGRAM_HANDLE] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " n/alice t/tutorial-1";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
