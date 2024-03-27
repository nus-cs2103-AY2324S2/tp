package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Person;

/**
 * Marks a specified amount as paid for a person's payment.
 */
public class MarkPaymentCommand extends Command {
    public static final String COMMAND_WORD = "markpayment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a specified amount as paid for a person's payment. "
            + "Parameters: "
            + PREFIX_ID + " ID "
            + PREFIX_PAYMENT + " AMOUNT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + " 000001 "
            + PREFIX_PAYMENT + " 50";

    public static final String MESSAGE_SUCCESS = "Payment of $%2$.2f marked as paid for person with ID: %1$s";
    private final Id uniqueId;
    private final double amount;

    /**
     * Creates a MarkPaymentCommand to mark the specified {@code Payment} as paid for the person with the specified
     * {@code Id}.
     */
    public MarkPaymentCommand(Id uniqueId, double amount) {
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

        Payment newPayment = new Payment(Math.max(0, personToUpdate.getPayment().getAmount() - amount));
        Person updatedPerson = new Person(personToUpdate.getName(), personToUpdate.getPhone(),
                personToUpdate.getEmail(), personToUpdate.getAddress(), personToUpdate.getTags(),
                personToUpdate.getSubject(), personToUpdate.getUniqueId(), newPayment);

        model.setPerson(personToUpdate, updatedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, uniqueId, amount));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof MarkPaymentCommand)) {
            return false;
        }

        MarkPaymentCommand otherCommand = (MarkPaymentCommand) other;
        return uniqueId.equals(otherCommand.uniqueId)
                && Double.compare(amount, otherCommand.amount) == 0;
    }


}
