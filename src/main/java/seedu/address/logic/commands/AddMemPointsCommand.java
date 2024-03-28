package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMSHIP_PTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Adds membership points for a person in the address book.
 */
public class AddMemPointsCommand extends Command {
    public static final String COMMAND_WORD = "addmempts";

    public static final String INVALID_COMMAND_FORMAT = "Invalid command format.";
    public static final String MESSAGE_CONSTRAINTS = "Points to add must be a positive integer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add membership points to the person identified "
            + "by the name of the person in the listing. "
            + "Existing membership will be overwritten by the input.\n"
            + "Parameters: n/MEMBER_NAME " + PREFIX_MEMSHIP_PTS + "[POINTS_TO_ADD]\n"
            + "Example: " + COMMAND_WORD + " n/Alice "
            + PREFIX_MEMSHIP_PTS + "T1";
    public static final String MESSAGE_ADD_MEMBERSHIP_SUCCESS = "Added %1$d membership points to Person: %2$s";
    private final Name name;
    private final int pointsToAdd;

    /**
     * @param name of the person in the filtered person list to edit the remark
     * @param pointsToAdd of the person to be updated to
     */
    public AddMemPointsCommand(Name name, int pointsToAdd) {
        requireAllNonNull(name, pointsToAdd);
        this.name = name;
        this.pointsToAdd = pointsToAdd;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Optional<Person> personOptional = lastShownList.stream()
                .filter(person -> person.getName().equals(this.name))
                .findFirst();

        if (personOptional.isEmpty()) {
            throw new CommandException(
                    Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        Person personToEdit = personOptional.get();

        Person editedPerson = new Person(personToEdit.getName(),
                personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getMembershipPoints().addPoints(pointsToAdd),
                personToEdit.getAllergens(), personToEdit.getPoints(), personToEdit.getOrders());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_MEMBERSHIP_SUCCESS, pointsToAdd, personToEdit.getName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMemPointsCommand)) {
            return false;
        }

        // state check
        AddMemPointsCommand e = (AddMemPointsCommand) other;
        return name.equals(e.name)
                && pointsToAdd == e.pointsToAdd;
    }
}
