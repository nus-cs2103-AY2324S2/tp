package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOBBY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Adds a patient to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the address book. "
            + "Parameters: "
            + PREFIX_PID + "PATIENT_HOSPITAL_ID"
            + PREFIX_NAME + "NAME "
            + PREFIX_PREFERRED_NAME + "PREFERRED_NAME"
            + PREFIX_FOOD_PREFERENCE + "FOOD_PREFERENCE "
            + PREFIX_FAMILY_CONDITION + "FAMILY_CONDITION "
            + PREFIX_HOBBY + "HOBBY "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PID + "12345"
            + PREFIX_NAME + "Alex Yeoh Jia Jun "
            + PREFIX_PREFERRED_NAME + "Alex "
            + PREFIX_FOOD_PREFERENCE + "Curry Chicken "
            + PREFIX_FAMILY_CONDITION + "Stable, Has 2 sons visit him regularly "
            + PREFIX_HOBBY + "Singing karaoke "
            + PREFIX_TAG + "Diabetes ";

    public static final String MESSAGE_SUCCESS =
        "Information for patient %1$s with ID %2$s has been successfully added!";

    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the address book";

    private final Patient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addPatient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getName(), toAdd.getPatientHospitalId()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
