package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book or given student ID.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list or Student ID.\n"
            + "Parameters: INDEX (must be a positive integer) or" + "[" + PREFIX_ID + "KEYWORD]\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " " + PREFIX_ID + "A1234567Z";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final boolean isIndexBased;
    private Index targetIndex;
    private Id targetStudentId;

    /**
     * Instantiate Delete Command with target index.
     */
    public DeleteCommand(Index targetIndex) {
        this.isIndexBased = true;
        this.targetIndex = targetIndex;
    }

    /**
     * Instantiate Delete Command with given student ID.
     */
    public DeleteCommand(Id targetStudentId) {
        this.isIndexBased = false;
        this.targetStudentId = targetStudentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        
        if(isIndexBased) {
            return handleIndexInput(model);
        } else {
            return handleStudentIdInput(model);
        }
    }

    private CommandResult handleIndexInput(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    public CommandResult handleStudentIdInput(Model model) throws CommandException {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> personToDelete = lastShownList.stream()
                .filter(person -> person.getId().equals(targetStudentId))
                .findFirst();
        if (personToDelete.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_STUDENT_NOT_FOUND);
        }
        model.deletePerson(personToDelete.get());
        return new CommandResult(
                String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete.get())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return Objects.equals(targetIndex, otherDeleteCommand.targetIndex)
                && Objects.equals(targetStudentId, otherDeleteCommand.targetStudentId);
    }

    @Override
    public String toString() {
        ToStringBuilder deleteToStringBuilder = new ToStringBuilder(this);
        if (isIndexBased) {
            deleteToStringBuilder.add("targetIndex", targetIndex);
        } else {
            deleteToStringBuilder.add("targetStudentId", targetStudentId);
        }
        return deleteToStringBuilder.toString();
    }
}
