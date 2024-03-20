package seedu.edulink.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.edulink.commons.core.index.Index;
import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;

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
        if (isIndexBased) {
            return handleIndexInput(model);
        } else {
            return handleStudentIdInput(model);
        }
    }

    private CommandResult handleIndexInput(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(studentToDelete)));
    }

    /**
     * Handles delete by Student ID given.
     * This method updates the list of persons in the model to show all persons,
     * then attempts to find and delete the person with the specified student ID.
     *
     * @param model The model containing the list of persons to be modified.
     * @return A CommandResult indicating the success of the delete operation.
     * @throws CommandException If the specified student ID does not match any person in the model.
     */
    public CommandResult handleStudentIdInput(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredPersonList();
        Optional<Student> personToDelete = lastShownList.stream()
            .filter(person -> person.getId().equals(targetStudentId))
            .findFirst();

        if (personToDelete.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            lastShownList = model.getFilteredPersonList();

            personToDelete = lastShownList.stream()
                .filter(person -> person.getId().equals(targetStudentId))
                .findFirst();
        }

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
