package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * When no event is selected, deletes a person identified using it's displayed index from the address book.
 * When an event is selected, deletes a person identified using it's displayed index from the event.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "delp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": When no event is selected, deletes the person identified by the index number used in the displayed "
            + "person list; when an event is selected, deletes a person identified using it's displayed index from "
            + "the event.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_GLOBAL_SUCCESS = "Deleted Person: %1$s from the address book.";
    public static final String MESSAGE_DELETE_PERSON_EVENT_SUCCESS = "Deleted Person: %1$s from the selected event.";
    public static final String MESSAGE_NO_SUCH_PERSON = "This person is not found in the selected event.";


    private final Index targetIndex;

    public DeletePersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isAnEventSelected()) {
            return deleteFromGlobal(model);
        } else {
            return deleteFromEvent(model);
        }
    }

    public CommandResult deleteFromGlobal(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_GLOBAL_SUCCESS, Messages.format(personToDelete)));
    }

    public CommandResult deleteFromEvent(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isAnEventSelected()) {
            throw new CommandException(Messages.MESSAGE_SELECT_EVENT);
        }

        List<Person> lastShownList = model.getFilteredPersonListOfSelectedEvent();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (!model.isPersonInSelectedEvent(personToDelete)) {
            throw new CommandException(MESSAGE_NO_SUCH_PERSON);
        }

        model.deletePersonFromSelectedEvent(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_EVENT_SUCCESS, personToDelete.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePersonCommand)) {
            return false;
        }

        DeletePersonCommand otherDeleteCommand = (DeletePersonCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
