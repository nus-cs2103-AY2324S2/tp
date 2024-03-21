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
 * Unpairs a volunteer and a befriendee who were paired in the address book.
 */
public class UnpairCommand extends Command {
    public static final String COMMAND_WORD = "unpair";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Unpairs a volunteer and a befriendee specified"
                    + "by their index numbers used in the displayed person list.\n"
                    + "Parameters: INDEX1 INDEX2 (both must be a positive integers)\n"
                    + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_UNPAIR_SUCCESS = "Unpaired: %1$s and %2$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "Cannot unpair the same person from themselves.";
    public static final String MESSAGE_NOT_PAIRED = "The two persons are not paired, so unpairing is not possible.";

    private final Index index1;

    private final Index index2;

    /**
     * @param index1 of the first person to be paired
     * @param index2 of the second person to be paired with
     */
    public UnpairCommand(Index index1, Index index2) {
        requireNonNull(index1);
        requireNonNull(index2);

        this.index1 = index1;
        this.index2 = index2;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index1.getZeroBased() >= lastShownList.size() || index2.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnpair1 = lastShownList.get(index1.getZeroBased());
        Person personToUnpair2 = lastShownList.get(index2.getZeroBased());

        // Check if the two persons are the same person
        if (personToUnpair1.isSamePerson(personToUnpair2)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // Check if the two persons are paired
        if (!personToUnpair1.getPairedWith().equals(Optional.of(personToUnpair2.getName()))
                || !personToUnpair2.getPairedWith().equals(Optional.of(personToUnpair1.getName()))) {
            throw new CommandException(MESSAGE_NOT_PAIRED);
        }

        // Unset the pairedWith attribute of the befriendee and volunteer
        personToUnpair1.setPairedWith(Optional.empty());
        personToUnpair2.setPairedWith(Optional.empty());

        model.setPerson(lastShownList.get(index1.getZeroBased()), personToUnpair1);
        model.setPerson(lastShownList.get(index2.getZeroBased()), personToUnpair2);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(
                        MESSAGE_UNPAIR_SUCCESS, Messages.format(personToUnpair1), Messages.format(personToUnpair2)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnpairCommand)) {
            return false;
        }

        UnpairCommand otherUnpairCommand = (UnpairCommand) other;
        return index1.equals(otherUnpairCommand.index1)
                && index2.equals(otherUnpairCommand.index2);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index1", index1)
                .add("index2", index2)
                .toString();
    }
}
