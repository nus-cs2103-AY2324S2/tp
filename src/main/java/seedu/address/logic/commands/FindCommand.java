package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Finds and lists all persons in address book who matches the given predicate.
 * Text matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds students based on specified criteria.\n"
            + "Parameters: FIELD KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " name Alex Alice\n"
            + "Example: " + COMMAND_WORD + " major Computer Science\n"
            + "Example: " + COMMAND_WORD + " star < 1\n\n"
            + "Available fields:\n"
            + "- name: Searches students by name using keywords (case-insensitive).\n"
            + "- major: Searches students by major.\n"
            + "- star: Searches students by number of stars using comparison operators (<, <=, >, >=, =).\n"
            + "- bolt: Searches students by number of bolts using comparison operators (<, <=, >, >=, =).\n"
            + "- tags: Searches students by tag using.\n\n"
            + "Note:\n"
            + "- For name field, multiple keywords can be used to perform a keyword search.\n"
            + "- For star/bolt fields, use comparison operators (<, <=, >, >=, =) followed by a number.";

    private final Predicate<Student> predicate;

    public FindCommand(Predicate<Student> predicate) {
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
