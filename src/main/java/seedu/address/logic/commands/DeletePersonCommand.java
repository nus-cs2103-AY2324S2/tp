package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

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

    private final Index targetIndex;

    /**
     * Constructor of DeletePersonCommand
     *
     * @param targetIndex Index of person user wants to delete.
     */
    public DeletePersonCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isAnEventSelected()) {
            return deleteFromEvent(model);
        } else {
            return deleteFromGlobal(model);
        }
    }

    /**
     * Handles the deletion of a person from the global list. This method is called when no event is currently
     * selected in the model.
     *
     * @param model The model from which the person will be deleted globally. Must not be null.
     * @return A {@link CommandResult} object containing the success message of the global deletion.
     * @throws CommandException If the target index is invalid, i.e., if it is out of bounds of the list size.
     */
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

    /**
     * Handles the deletion of a person from the list of a currently selected event. This method is called
     * when an event is selected in the model.
     *
     * @param model The model from which the person will be deleted from the selected event. Must not be null.
     * @return A {@link CommandResult} object containing the success message of the event-specific deletion.
     * @throws CommandException If no event is selected, if the target index is invalid (out of bounds of the
     *      list size), or if the person is not part of the selected event.
     */
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
