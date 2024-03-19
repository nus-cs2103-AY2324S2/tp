package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonFieldsContainKeywordPredicate;

/**
 * Represents a Command to find and list all persons in the address book whose fields match the criteria specified
 * by the {@link PersonFieldsContainKeywordPredicate} provided at creation. This command allows for searching
 * persons based on various fields such as categories and descriptions, making it flexible for user queries.
 * <p>
 * The command uses a {@code PersonFieldsContainKeywordPredicate} to filter matching persons, which are then
 * displayed to the user as a list with index numbers.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds all persons based on the specified category and description "
        + "and displays them as a list with index numbers. Use prefixes 'c/' for category and 'd/' for description.\n"
        + "Parameters: c/CATEGORY d/DESCRIPTION\n"
        + "Example: " + COMMAND_WORD + " c/email d/johndoe@example.com";
    private final PersonFieldsContainKeywordPredicate predicate;
    /**
     * Creates a FindCommand to find all persons whose fields match the criteria specified by the provided
     * {@code PersonFieldsContainKeywordPredicate}.
     *
     * @param predicate The predicate used to determine the matching persons.
     */
    public FindCommand(PersonFieldsContainKeywordPredicate predicate) {
        this.predicate = predicate;
    }
    /**
     * Executes the find command and updates the filtered person list in the model to reflect the persons
     * that match the criteria specified by this command's predicate.
     *
     * @param model The model in which the filtered person list will be updated.
     * @return A {@code CommandResult} object that contains the result message of executing this command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
    /**
     * Compares this command with another object for equality.
     *
     * @param other The object to compare this command against.
     * @return True if the other object is an instance of {@code FindCommand}
     *     and its predicate is equal to this command's predicate.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FindCommand)) {
            return false;
        }
        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }
    /**
     * Returns a string representation of this find command, including the details of its predicate.
     *
     * @return A string representation of this command.
     */
    @Override
    public String toString() {
        return "FindCommand{"
            + "predicate="
            + predicate
            + '}';
    }
}
