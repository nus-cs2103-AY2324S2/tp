package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_ALREADY_EXIST;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.Nric;

/**
 * Adds a person to the clinic.
 */
public class AddContactCommand extends Command {
    public static final String COMMAND_WORD = "addContact";
    public static final String MESSAGE_SUCCESS = "Contact added successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact of a person. "
        + "Parameters: (required field)\n"
        + PREFIX_NRIC + "NRIC "
        + "(optional but at least specify one)\n"
        + PREFIX_PHONE + "PHONE "
        + PREFIX_EMAIL + "EMAIL "
        + PREFIX_ADDRESS + "ADDRESS \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NRIC + "A12345678 "
        + PREFIX_PHONE + "98765432 ";

    private final Nric nric;
    private final ContactInformation contactInformation;

    /**
     * Creates an AddCommand to add the specified {@code ContactInformation}
     */
    public AddContactCommand(Nric nric, ContactInformation contactInformation) {
        requireNonNull(contactInformation);
        this.nric = nric;
        this.contactInformation = contactInformation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // if person not exist, throw error
        Person p = model.findPersonByNric(nric);
        if (p == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }
        // if person already has contact information, throw error
        ContactInformation ci = p.getContactInformation();
        if (!ci.isEmptyContact()) {
            throw new CommandException(MESSAGE_PERSON_ALREADY_EXIST);
        }
        // add the contact information to the person
        model.updatePersonContactInformation(nric, contactInformation);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // update the person to the model
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddContactCommand // instanceof handles nulls
            && nric.equals(((AddContactCommand) other).nric)
            && contactInformation.equals(((AddContactCommand) other).contactInformation));
    }

    @Override
    public String toString() {
        return "addContact" + nric + contactInformation;
    }
}
