package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all patients in address book whose name OR NRIC contains the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindPatientCommand extends Command {

    public static final String COMMAND_WORD = "findPatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all patients whose names OR nric start with "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME(S) OR  "
            + PREFIX_NRIC + "NRIC \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Alex"
            + " OR " + COMMAND_WORD + " " + PREFIX_NRIC + "T0123456A";

    public static final String MESSAGE_MULTIPLE_FIELDS_FAILURE = "Find by either NRIC or name, not both!";

    private final Predicate<Person> predicate;

    public FindPatientCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENT_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindPatientCommand)) {
            return false;
        }

        FindPatientCommand otherFindPatientCommand = (FindPatientCommand) other;
        return predicate.equals(otherFindPatientCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
