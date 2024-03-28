package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Adds a patient to CLInic.
 */
public class AddPatientCommand extends Command {

    public static final String COMMAND_WORD = "addPatient";

    public static final String COMMAND_WORD_ALT = "ap";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the CLInic. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DOB + "DOB "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "MEDICAL ALLERGIES]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NRIC + "T0123456A "
            + PREFIX_DOB + "2001-01-30 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "insulin "
            + PREFIX_TAG + "panadol";

    public static final String MESSAGE_ADD_PATIENT_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_ADD_DUPLICATE_PATIENT_FAILURE =
            "This patient already exists in the CLInic";

    private final Patient patientToAdd;

    /**
     * Creates an AddPatientCommand to add the specified {@code Patient}
     */
    public AddPatientCommand(Patient patient) {
        requireNonNull(patient);
        patientToAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPatient(patientToAdd)) {
            throw new CommandException(MESSAGE_ADD_DUPLICATE_PATIENT_FAILURE);
        }

        model.addPatient(patientToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_PATIENT_SUCCESS, Messages.format(patientToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPatientCommand)) {
            return false;
        }

        AddPatientCommand otherAddPatientCommand = (AddPatientCommand) other;
        return patientToAdd.equals(otherAddPatientCommand.patientToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", patientToAdd)
                .toString();
    }
}
