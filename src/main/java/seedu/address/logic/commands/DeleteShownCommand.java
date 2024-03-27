package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes all persons in the shown list.
 */
public class DeleteShownCommand extends Command {
    public static final String COMMAND_WORD = "deleteshown";

    public static final String MESSAGE_SUCCESS = "Deleted %d shown person(s). Listing %d remaining person(s).";
    public static final String MESSAGE_NO_PERSONS = "No persons to delete.";
    public static final String MESSAGE_NO_FILTER =
            "For safety, all persons cannot be deleted. Use the 'clear' command instead.";

    /**
     * Deletes all persons in the last shown list.
     * @param model {@code Model} which the command should operate on.
     * @return a command result in which the success message is displayed
     * @throws CommandException if the list is empty
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Person> lastShownList = model.getFilteredPersonList();
        checkForEmptyList(lastShownList);

        ensureNotAllAreDeleted(model, lastShownList);

        int deletedCount = getFilteredSize(lastShownList);
        int leftOver = getLeftOverSize(model, lastShownList);

        deletePersons(model, lastShownList);

        showRemainingPersons(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                deletedCount, leftOver));
    }

    /**
     * Checks if the list is empty.
     * @param list the list
     * @throws CommandException if the list is empty
     */
    private static void checkForEmptyList(ObservableList<Person> list) throws CommandException {
        if (list.isEmpty()) {
            throw new CommandException(MESSAGE_NO_PERSONS);
        }
    }

    /**
     * Shows the remaining persons in the address book.
     * @param model the model
     */
    private static void showRemainingPersons(Model model) {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Deletes all persons in the last shown list.
     * @param model the model
     * @param lastShownList the last shown list
     */
    private static void deletePersons(Model model, ObservableList<Person> lastShownList) {
        for (int i = lastShownList.size() - 1; i >= 0; i--) {
            model.deletePerson(lastShownList.get(i));
        }
    }

    /**
     * Returns the number of persons in the filtered list.
     * @param lastShownList the last shown list
     * @return the number of persons in the filtered list
     */
    private static int getFilteredSize(ObservableList<Person> lastShownList) {
        return lastShownList.size();
    }

    /**
     * Returns the total number of persons in the address book.
     * @param model the model
     * @return the total number of persons in the address book
     */
    private static int getTotalSize(Model model) {
        AddressBook addressBook = new AddressBook(model.getAddressBook());

        return addressBook.getPersonList().size();
    }

    /**
     * Returns the number of persons that will be left over after deletion.
     * @param model the model
     * @param lastShownList the last shown list
     * @return the number of persons that will be left over after deletion
     */
    private static int getLeftOverSize(Model model, ObservableList<Person> lastShownList) {
        return getTotalSize(model) - getFilteredSize(lastShownList);
    }

    /**
     * Ensures that not all persons are deleted.
     * @param model the model
     * @param lastShownList the last shown list
     * @throws CommandException if all persons are at risk of being deleted
     */
    private static void ensureNotAllAreDeleted(Model model, ObservableList<Person> lastShownList)
            throws CommandException {
        if (getFilteredSize(lastShownList) == getTotalSize(model)) {
            throw new CommandException(MESSAGE_NO_FILTER);
        }
    }
}
