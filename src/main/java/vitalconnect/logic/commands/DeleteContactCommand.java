package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.Nric;



/**
 * Deletes a person's contact from the clinic.
 */
public class DeleteContactCommand extends Command {
    public static final String COMMAND_WORD = "delContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a contact of a person. "
        + "Parameters: (required field)\n"
        + PREFIX_NRIC + "NRIC\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NRIC + "S1234567D ";

    private final Nric nric;
    /**
     * Creates an AddCommand to add the specified {@code ContactInformation}
     */
    public DeleteContactCommand(Nric nric) {
        requireNonNull(nric);
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // if person not exist, throw error
        Person personToEdit = model.findPersonByNric(nric);
        if (personToEdit == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }
        // Erase the current contact information
        ContactInformation contactInformation = new ContactInformation(new Email(""), new Phone(""), new Address(""));
        model.updatePersonContactInformation(nric, contactInformation);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult("Contact deleted successfully");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteContactCommand)) {
            return false;
        }
        return nric.equals(((DeleteContactCommand) other).nric);
    }
}
