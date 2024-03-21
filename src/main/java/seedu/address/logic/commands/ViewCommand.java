package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ViewPredicate;

/**
 * Displays the policy details of a person specified by their index in the address book.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    /** Usage message for the view command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": View the policy details of the person specified by the index.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    /** Success message displayed after viewing the person's details. */
    public static final String MESSAGE_VIEW_SUCCESS = "Viewed Person Successfully: %1$s";
    private static final Logger logger = Logger.getLogger(ViewCommand.class.getName());
    private final Index index;

    /**
     * Constructs a ViewCommand with the specified index.
     * @param index The index of the person whose details are to be viewed.
     */
    public ViewCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
    }

    /**
     * Executes the view command to display the policy details of the specified person.
     * @param model The current model containing the address book data.
     * @return The result of the command execution.
     * @throws CommandException If the index is invalid or if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        // Update the filtered person list to display all persons
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();
        logger.info("Curr list is: " + lastShownList);
        logger.info("Curr index: " + lastShownList.size() + " WHILE OUR INDEX IS : " + index);

        // Check if the index is valid
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // Retrieve the person with the specified index
        Person person = lastShownList.get(index.getZeroBased());

        // Create an View predicate to filter the list to only contain the specified person
        ViewPredicate pred = new ViewPredicate(index, person);
        model.updateFilteredPersonList(pred);

        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, person.getName()));
    }

    /**
     * Checks if this ViewCommand is equal to another object.
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand e = (ViewCommand) other;
        return index.equals(e.index);
    }
}

