package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOLT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Bolt;
import seedu.address.model.person.Person;

/**
 * Adds a bolt to a student in the address book.
 * @code bolt 1 b/3
 */
public class BoltCommand extends Command {

    public static final String COMMAND_WORD = "bolt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the number of lightning bolts to "
            + "the person identified by the index number used in the last person listing. "
            + "The number of lightning bolts corresponds to the number of absences. "
            + "Updates the existing absence count by adding the input number.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_BOLT + "[BOLT]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_BOLT + "3";

    public static final String MESSAGE_ADD_BOLT_SUCCESS = "Added bolts to Person: %1$s";

    private final Index index; // Index to give bolt
    private final Bolt bolt; // Number of bolts given

    /**
     * @param index of the person in the filtered person list to give the bolt.
     * @param bolt number of bolts given to student.
     */
    public BoltCommand(Index index, Bolt bolt) {
        requireNonNull(index);
        requireNonNull(bolt);
        this.index = index;
        this.bolt = bolt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getCorrectPersonList(); // get the list of persons

        if (index.getZeroBased() >= lastShownList.size()) { // if index out of range
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased()); // get Person indexed
        Bolt boltToEdit = personToEdit.getBolt(); // return the bolts
        Bolt editedBolt = new Bolt(boltToEdit.numOfBolts + this.bolt.numOfBolts); // new Bolt to be added

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getMajor(), personToEdit.getStar(), editedBolt, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_BOLT_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BoltCommand)) {
            return false;
        }

        BoltCommand e = (BoltCommand) other;
        return index.equals(e.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", this.index)
                .add("bolt", this.bolt)
                .toString();
    }
}
