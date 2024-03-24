package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;

import java.util.Objects;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Person;

public class AddPaymentCommand extends Command {
    public static final String COMMAND_WORD = "addpayment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a payment to a person's outstanding amount. "
            + "Parameters: "
            + PREFIX_ID + " ID "
            + PREFIX_PAYMENT + " AMOUNT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + " 000001  "
            + PREFIX_PAYMENT + " 100";

    public static final String MESSAGE_SUCCESS = "Added payment of $%2$.2f to person with ID: %1$s";
    private final Id uniqueId;
    private final double amount;

    public AddPaymentCommand(Id uniqueId, double amount) {
        requireNonNull(uniqueId);
        this.uniqueId = uniqueId;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToUpdate = model.getPersonByUniqueId(uniqueId.toString());

        if (personToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        Payment newPayment = new Payment(personToUpdate.getPayment().getAmount() + amount);
        Person updatedPerson = new Person(personToUpdate.getName(), personToUpdate.getPhone(), personToUpdate.getEmail(), personToUpdate.getAddress(), personToUpdate.getTags(), personToUpdate.getSubject(), personToUpdate.getUniqueId(), newPayment);

        model.setPerson(personToUpdate, updatedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, uniqueId, amount));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AddPaymentCommand)) {
            return false;
        }

        AddPaymentCommand otherCommand = (AddPaymentCommand) other;
        return uniqueId.equals(otherCommand.uniqueId)
                && Double.compare(amount, otherCommand.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, amount);
    }

}
