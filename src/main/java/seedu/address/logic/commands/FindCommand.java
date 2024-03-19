package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;


/**
 * Finds and lists all persons whose name or phone number matches any of the argument inputs.
 * Name matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names or phone numbers "
            + "matches any of the inputs (case-insensitive for names) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "KEYWORD] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "alice "
            + PREFIX_PHONE + "91234567";


    private final Predicate<Patient> namePredicate;

    private final Predicate<Patient> phonePredicate;

    public FindCommand(Predicate<Patient> namePredicate, Predicate<Patient> phonePredicate) {
        this.namePredicate = namePredicate;
        this.phonePredicate = phonePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate.and(phonePredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
        return namePredicate.equals(otherFindCommand.namePredicate)
                && phonePredicate.equals(otherFindCommand.phonePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .add("phonePredicate", phonePredicate)
                .toString();
    }
}
