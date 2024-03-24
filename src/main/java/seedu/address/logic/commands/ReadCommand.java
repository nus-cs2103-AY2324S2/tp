package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Reads the details of an existing person in the address book.
 */
public class ReadCommand extends Command {

    public static final String COMMAND_WORD = "read";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reads the details of the person identified "
            + "by the NRIC specified. "
            + "Example: " + COMMAND_WORD
            + PREFIX_NRIC + "T0123456A";

    public static final String MESSAGE_READ_PERSON_SUCCESS = "Read Person: %1$s";
    public static final String MESSAGE_NOT_READ = "NRIC to be specified.";
    private final Nric nric;

    /**
     * @param nric of the person to read
     */
    public ReadCommand(Nric nric) {
        requireNonNull(nric);

        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(Person.createPersonWithNric(nric))) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        model.updateFilteredPersonList(new NricContainsKeywordsPredicate(nric.toString()));
        Person readPerson = model.getFilteredPersonList().get(0);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_READ_PERSON_SUCCESS, Messages.formatRead(readPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReadCommand)) {
            return false;
        }

        ReadCommand otherReadCommand = (ReadCommand) other;
        return this.nric.equals(otherReadCommand.nric);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nric", nric)
                .toString();
    }
}
