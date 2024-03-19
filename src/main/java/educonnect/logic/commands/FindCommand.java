package educonnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.function.Predicate;

import educonnect.commons.util.ToStringBuilder;
import educonnect.logic.Messages;
import educonnect.model.Model;
import educonnect.model.student.Student;

/**
 * Finds and lists all students in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose attributes match "
            + "the specified attributed keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: [n/NAME] [s/STUDENT_ID] [h/TELEGRAM_HANDLE] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " n/alice t/tutorial-1";

    private final Collection<Predicate<Student>> predicates;

    public FindCommand(Collection<Predicate<Student>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicates);
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
        return predicates.equals(otherFindCommand.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicates)
                .toString();
    }
}
