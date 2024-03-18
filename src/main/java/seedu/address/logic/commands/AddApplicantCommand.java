package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

/**
 * Adds a applicant to the address book.
 */
public class AddApplicantCommand extends Command {

    public static final String COMMAND_WORD = "add_applicant";

    //TODO
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an applicant to the address book. "
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_PHONE + " PHONE "
            + PREFIX_EMAIL + " EMAIL "
            + PREFIX_ADDRESS + " ADDRESS "
            + PREFIX_ROLE + " ROLE "
            + "[" + PREFIX_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John Doe "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_EMAIL + " johnd@example.com "
            + PREFIX_ADDRESS + " 311, Clementi Ave 2, #02-25 "
            + PREFIX_ROLE + " SWE "
            + PREFIX_TAG + " friends "
            + PREFIX_TAG + " owesMoney";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in the address book";

    private final Applicant toAddApplicant;

    /**
     * Creates an AddCommand to add the specified {@code Applicant}
     */
    public AddApplicantCommand(Applicant applicant) {
        requireNonNull(applicant);
        toAddApplicant = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAddApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.addPerson(toAddApplicant);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAddApplicant)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApplicantCommand)) {
            return false;
        }

        AddApplicantCommand otherAddCommand = (AddApplicantCommand) other;
        return toAddApplicant.equals(otherAddCommand.toAddApplicant);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAddApplicant", toAddApplicant)
                .toString();
    }
}
