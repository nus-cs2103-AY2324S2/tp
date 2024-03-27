package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_MEDICAL_INFO_ALREADY_EXIST;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_ALLERGYTAG;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

/**
 * Adds medication information for a person.
 */
public class AddMedInfoCommand extends Command {
    public static final String COMMAND_WORD = "addm";
    public static final String MESSAGE_SUCCESS = "Medical information added successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the medical information of a person.\n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_HEIGHT + "Height(in cm) "
            + PREFIX_WEIGHT + "Weight(in kg) "
            + "[" + PREFIX_ALLERGYTAG + "Allergy]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_HEIGHT + "175 "
            + PREFIX_WEIGHT + "60 "
            + PREFIX_ALLERGYTAG + "Amoxicillin ";

    private final Nric nric;
    private final MedicalInformation medicalInformation;

    /**
     * Creates an AddMedInfoCommand to add the specified {@code MedicalInformation}
     */
    public AddMedInfoCommand(Nric nric, MedicalInformation medicalInformation) {
        requireNonNull(medicalInformation);
        this.nric = nric;
        this.medicalInformation = medicalInformation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person p = model.findPersonByNric(nric);
        if (p == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        if (p.hasMedicalInformation()) {
            throw new CommandException(MESSAGE_MEDICAL_INFO_ALREADY_EXIST);
        } else {
            model.updatePersonMedicalInformation(nric, medicalInformation);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            return new CommandResult(MESSAGE_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMedInfoCommand // instanceof handles nulls
                && nric.equals(((AddMedInfoCommand) other).nric)
                && medicalInformation.equals(((AddMedInfoCommand) other).medicalInformation));
    }

    @Override
    public String toString() {
        return "addMedInfo" + nric + medicalInformation;
    }
}
