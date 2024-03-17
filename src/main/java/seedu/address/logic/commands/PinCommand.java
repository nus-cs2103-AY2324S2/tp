package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSID;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;



public class PinCommand extends Command {
    public static final String COMMAND_WORD = "pin";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Pin a student to the top of the address book. "
            + "Parameters: "
            + PREFIX_NUSID + "NUSID ";

    public static final String MESSAGE_PIN_PERSON_SUCCESS = "Student has been pinned: %1$s";


    private final NusId nusId;

    public PinCommand(NusId nusId) {
        this.nusId = nusId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToPin = lastShownList.stream().filter(person -> person.getNusId().equals(nusId))
                .findFirst().orElse(null);

        if (personToPin == null) {
            throw new CommandException(Messages.MESSAGE_NON_EXISTENT_PERSON);
        }

        model.pinPerson(personToPin);
        return new CommandResult(String.format(MESSAGE_PIN_PERSON_SUCCESS, Messages.format(personToPin)));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PinCommand)) {
            return false;
        }

        PinCommand otherDeleteCommand = (PinCommand) other;
        return nusId.equals(otherDeleteCommand.nusId);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetnusId", nusId.toString())
                .toString();
    }


}
