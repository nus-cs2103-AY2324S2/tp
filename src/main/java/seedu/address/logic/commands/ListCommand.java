package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "querystudents";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all students that meets the condition "
            + "Optional Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_ALIAS + "ALIAS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ALIAS + "Johnny "
            + PREFIX_ALIAS + "owesMoney";


    private final Predicate<Patient> predicates;

    public ListCommand(Predicate<Patient> predicates) {
        this.predicates = predicates;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(predicates);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
