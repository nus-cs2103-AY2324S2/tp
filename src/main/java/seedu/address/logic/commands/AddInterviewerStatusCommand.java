package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AddInterviewerCommandStatus object
 */
public class AddInterviewerStatusCommand extends Command {

    public static final String COMMAND_WORD = "interviewer_status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the status of the interviewer identified "
            + "by the phone number used in the command. "
            + "Existing status will be overwritten by the input.\n"
            + "Parameters: PHONE (must be at least 3 digits) "
            + PREFIX_STATUS + "(must be either \"free\" or \"interview with [APPLICANT NAME]\") [STATUS]\n"
            + "Example: " + COMMAND_WORD + " 98362254 "
            + PREFIX_STATUS + "free";

    public static final String MESSAGE_ADD_STATUS_SUCCESS = "Added status to Interviewer: %1$s";

    private final Phone phone;

    private final InterviewerStatus status;

    /**
     * @param phone of the person in the filtered person list to edit the status
     * @param status of the person to be updated to
     */
    public AddInterviewerStatusCommand(Phone phone, InterviewerStatus status) {
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
            throw new CommandException(Messages.MESSAGE_INCORRECT_INTERVIEWER_PHONE_NUMBER);
        }

        if (!personToEdit.getPersonType().equals("INTERVIEWER")) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_STATUS_INTERVIEWER);
        }

        Interviewer editedPerson = new Interviewer(personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getRemark(), status, personToEdit.getTags());

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
        if (!(other instanceof AddInterviewerStatusCommand)) {
            return false;
        }

        // state check
        AddInterviewerStatusCommand e = (AddInterviewerStatusCommand) other;
        return phone.equals(e.phone)
                && status.equals(e.status);
    }
}
