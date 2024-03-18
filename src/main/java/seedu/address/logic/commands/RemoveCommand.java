package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class RemoveCommand extends Command{

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove the person identified by the matching name in the contacts list.\n"
            + "Parameters: EXISTING_CONTACT_NAME\n"
            + "Example: " + COMMAND_WORD + " John Doe";

    public static final String MESSAGE_PERSONS_TO_REMOVE_NOT_FOUND = "No contacts found with the given name!";
    public static final String MESSAGE_PERSONS_TO_REMOVE_FOUND = "%1$d contact(s) found with the given name. "
            + "Please specify the index of the contact to remove.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_PERSON_SUCCESS = "Removed Contact: %1$s";

    private final NameContainsKeywordsPredicate predicate;

    private final Index targetIndex;

    /**
     * Constructs the first RemoveCommand with the given predicate.
     * @param predicate Keyword to filter out the contacts that match the given name, for safe deletion.
     */
    public RemoveCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
        this.targetIndex = null;
    }

    /**
     * Constructs the second RemoveCommand with the given index.
     * @param targetIndex Index of the contact to be removed.
     */
    public RemoveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.predicate = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // First constructor is called, 1st part of RemoveCommand function - finding the contact for safe removal
        if (predicate != null && targetIndex == null) {
            model.updateFilteredPersonList(predicate);
            // if no matching names found
            if (model.getFilteredPersonList().size() == 0) {
                throw new CommandException(MESSAGE_PERSONS_TO_REMOVE_NOT_FOUND);
            } else {
                return new CommandResult(
                        String.format(MESSAGE_PERSONS_TO_REMOVE_FOUND, model.getFilteredPersonList().size()));
            }
        } else if (targetIndex != null && predicate == null) {
            // Second constructor is called, 2nd part of RemoveCommand function - actual removal of contact
            // Index called has to refer to the filtered list from part 1
            List<Person> lastShownList = model.getFilteredPersonList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToRemove = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToRemove);
            return new CommandResult(String.format(MESSAGE_REMOVE_PERSON_SUCCESS, Messages.format(personToRemove)));
        } else {
            // Should not reach here 
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }


    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        RemoveCommand otherRemoveCommand = (RemoveCommand) other;

        if (predicate != null && targetIndex == null) {
            return predicate.equals(otherRemoveCommand.predicate);
        } else{
            assert targetIndex != null;
            return targetIndex.equals(otherRemoveCommand.targetIndex);
        }
    }

    @Override
    public String toString() {

        if (predicate != null && targetIndex == null) {
            return new ToStringBuilder(this)
                    .add("predicate", predicate)
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("targetIndex", targetIndex)
                    .toString();
        }

    }

}
