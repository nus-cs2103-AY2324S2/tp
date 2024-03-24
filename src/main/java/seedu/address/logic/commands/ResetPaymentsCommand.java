package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.Objects;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.person.Payment;

public class ResetPaymentsCommand extends Command {
    public static final String COMMAND_WORD = "resetpayments";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets the payments for a person to $0. "
            + "Parameters: "
            + PREFIX_ID + " ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + " 000001 ";

    public static final String MESSAGE_SUCCESS = "Payments reset for person with ID: %1$s";
    private final Id uniqueId;

    public ResetPaymentsCommand(Id uniqueId) {
        requireNonNull(uniqueId);
        this.uniqueId = uniqueId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToUpdate = model.getPersonByUniqueId(uniqueId.toString());

        if (personToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        Payment newPayment = new Payment(0);
        Person updatedPerson = new Person(personToUpdate.getName(), personToUpdate.getPhone(), personToUpdate.getEmail(), personToUpdate.getAddress(), personToUpdate.getTags(), personToUpdate.getSubject(), personToUpdate.getUniqueId(), newPayment);

        model.setPerson(personToUpdate, updatedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, uniqueId));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ResetPaymentsCommand)) {
            return false;
        }

        ResetPaymentsCommand otherCommand = (ResetPaymentsCommand) other;
        return uniqueId.equals(otherCommand.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }

}
