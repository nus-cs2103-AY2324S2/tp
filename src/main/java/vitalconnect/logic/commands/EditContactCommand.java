package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_CONTACT_INFO_NOT_FOUND;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_PHONE;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.Nric;

/**
 * Edits the details of an existing person in the clinic.
 */
public class EditContactCommand extends Command {
    public static final String COMMAND_WORD = "editc";
    public static final String MESSAGE_SUCCESS = "Contact information updated successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit contact of a person. "
        + "Parameters:\n"
        + PREFIX_NRIC + "NRIC "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_ADDRESS + "ADDRESS \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NRIC + "S1234567D "
        + PREFIX_PHONE + "98765432 ";

    private final Nric nric;
    private final Email email;
    private final Phone phone;
    private final Address address;

    /**
     * @param nric of the person in the filtered person list to edit
     * @param email details to edit the person with
     * @param phone details to edit the person with
     * @param address details to edit the person with
     */
    public EditContactCommand(Nric nric, Email email, Phone phone, Address address) {
        this.nric = nric;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // check if current person exists
        // if person not exist, throw error
        Person p = model.findPersonByNric(nric);
        if (p == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }
        // fetch current person contact information, update fields.
        ContactInformation ci = p.getContactInformation();
        // check if current person has contanct information
        if (ci.isEmptyContact()) {
            throw new CommandException(MESSAGE_CONTACT_INFO_NOT_FOUND);
        }
        // update the contact info
        if (email != null) {
            ci.updateEmail(email);
        }
        if (phone != null) {
            ci.updatePhone(phone);
        }
        if (address != null) {
            ci.updateAddress(address);
        }
        // update the person's contact
        model.updatePersonContactInformation(nric, ci);
        // store into the list
        model.updateFilteredPersonList(model.getCurrentPredicate());
        return new CommandResult(MESSAGE_SUCCESS);
    }


}
