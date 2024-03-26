package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RateMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;

/**
 * Rates a person identified using it's displayed name from the address book.
 */
public class RateCommand extends Command {
    public static final String COMMAND_WORD = "/rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rates the person identified by their name.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_RATING + "RATING "
            + "Example: " + COMMAND_WORD + PREFIX_NAME + "Moochie " + PREFIX_RATING + "2";

    private final Name targetName;
    private final Rating rating;

    /**
     * Creates a RateCommand to rate the specified contact specified by {@code Name}
     */
    public RateCommand(Name name, Rating rating) {
        this.targetName = name;
        this.rating = rating;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToRate = model.findByName(targetName);

        if (personToRate == null) {
            throw new CommandException(RateMessages.MESSAGE_RATE_NAME_NOT_FOUND);
        }

        Person ratedPerson = personToRate.updateRating(rating);

        model.setPerson(personToRate, ratedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(RateMessages.MESSAGE_RATE_PERSON_SUCCESS,
                RateMessages.format(personToRate)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RateCommand)) {
            return false;
        }

        RateCommand otherRateCommand = (RateCommand) other;
        return targetName.equals(otherRateCommand.targetName);

    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
