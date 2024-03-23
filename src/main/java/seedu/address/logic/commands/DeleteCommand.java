package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the NRIC.\n"
            + "Parameters: NRIC\n"
            + "Example: " + COMMAND_WORD + " S1234567B";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Patient: %1$s";

    private final Nric targetNric;
    //TODO test cases
    public DeleteCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    //TODO test cases
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Person> persons = model.getFilteredPersonList();
        if (!model.hasPerson(Person.createPersonWithNric(targetNric))) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
        //Difference between filteredPersons.contains and model.hasPerson: first checks if the instance is in the list,
        //second checks if the NRIC is in the list
        Person personToDelete = persons.filtered(person -> person.getNric().equals(targetNric)).get(0);
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
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
        return targetNric.equals(otherDeleteCommand.targetNric);
    }

    //TODO test cases
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetNric", targetNric)
                .toString();
    }
}
