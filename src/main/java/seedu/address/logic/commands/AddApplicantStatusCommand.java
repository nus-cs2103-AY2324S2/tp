package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AddApplicantCommandStatus object
 */
public class AddApplicantStatusCommand extends Command {

    public static final String COMMAND_WORD = "applicant_status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the status of the applicant identified "
            + "by the phone number used in the command. "
            + "Existing status will be overwritten by the input.\n"
            + "Parameters: PHONE (must be at least 3 digits) "
            + PREFIX_STATUS + "(must be either \"resume review\", \"pending interview\", \"completed interview\""
            + "\"waiting list\", \"accepted\", or \"rejected\") [STATUS]\n"
            + "Example: " + COMMAND_WORD + " 98362254 "
            + PREFIX_STATUS + "accepted";

    public static final String MESSAGE_ADD_STATUS_SUCCESS = "Added status to Applicant: %1$s";

    private final Phone phone;

    private final ApplicantStatus status;

    /**
     * @param phone of the person in the filtered person list to edit the status
     * @param status of the person to be updated to
     */
    public AddApplicantStatusCommand(Phone phone, ApplicantStatus status) {
        requireAllNonNull(phone, status);

        this.phone = phone;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToEdit;
        try {
            personToEdit = lastShownList
                    .stream()
                    .filter(person -> person.getPhone().equals(phone))
                    .reduce((person, prev) -> person).get();
        } catch (NoSuchElementException e) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER);
        }

        if (!personToEdit.getPersonType().equals("APPLICANT")) {
            throw new CommandException(Messages.MESSAGE_INCORRENT_STATUS_APPLICANT);
        }

        Applicant editedPerson = new Applicant(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getRemark(), status, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message for adding the status
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_STATUS_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApplicantStatusCommand)) {
            return false;
        }

        // state check
        AddApplicantStatusCommand e = (AddApplicantStatusCommand) other;
        return phone.equals(e.phone)
                && status.equals(e.status);
    }
}
