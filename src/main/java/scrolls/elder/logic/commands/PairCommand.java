package scrolls.elder.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.model.Model;
import scrolls.elder.model.person.Person;

/**
 * Pairs a volunteer and a befriendee in the address book.
 */
public class PairCommand extends Command {

    public static final String COMMAND_WORD = "pair";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Pairs a volunteer and a befriendee specified "
            + "by their index numbers used in the displayed person list.\n"
            + "Parameters: INDEX1 INDEX2 (both must be a positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_PAIR_SUCCESS = "Paired: %1$s and %2$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "Cannot pair the same person with themselves.";
    public static final String MESSAGE_DIFFERENT_PERSON_TYPE =
            "Pairing can only be done between a volunteer and a befriendee.";

    public static final String MESSAGE_ALREADY_PAIRED =
            "One or both of the persons are already paired, unpair and try again.";

    private final Index index1;

    private final Index index2;

    /**
     * @param index1 of the first person to be paired
     * @param index2 of the second person to be paired with
     */
    public PairCommand(Index index1, Index index2) {
        requireNonNull(index1);
        requireNonNull(index2);

        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownBList = model.getFilteredBefriendeeList();
        List<Person> lastShownVList = model.getFilteredVolunteerList();

        if (index1.getZeroBased() >= lastShownBList.size() || index2.getZeroBased() >= lastShownVList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToPair1 = lastShownBList.get(index1.getZeroBased());
        Person personToPair2 = lastShownVList.get(index2.getZeroBased());

        // Check if the two persons are the same person
        if (personToPair1.isSamePerson(personToPair2)
                && model.hasPerson(personToPair1)
                && model.hasPerson(personToPair2)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // Check if the two persons are of different types (eg. volunteer and befriendee)
        if (personToPair1.getRole().equals(personToPair2.getRole())) {
            throw new CommandException(MESSAGE_DIFFERENT_PERSON_TYPE);
        }

        // Check if any of the persons are already paired
        if (personToPair1.isPaired() || personToPair2.isPaired()) {
            throw new CommandException(MESSAGE_ALREADY_PAIRED);
        }

        // Set the pairedWith attribute of the befriendee and volunteer
        personToPair1.setPairedWith(Optional.of(personToPair2.getName()));
        personToPair2.setPairedWith(Optional.of(personToPair1.getName()));

        model.setPerson(lastShownBList.get(index1.getZeroBased()), personToPair1);
        model.setPerson(lastShownVList.get(index2.getZeroBased()), personToPair2);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(MESSAGE_PAIR_SUCCESS, Messages.format(personToPair1), Messages.format(personToPair2)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PairCommand)) {
            return false;
        }

        PairCommand otherPairCommand = (PairCommand) other;
        return index1.equals(otherPairCommand.index1)
                && index2.equals(otherPairCommand.index2);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index1", index1)
                .add("index2", index2)
                .toString();
    }
}
