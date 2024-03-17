package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_ALREADY_EXIST;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.*;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

/**
 * Adds a person to the clinic.
 */
public class AddMedInfoCommand extends Command {
    public static final String COMMAND_WORD = "addMI";
    public static final String MESSAGE_SUCCESS = "Medical Information added successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a medical information of a person. "
            + "Parameters: (required field)\n"
            + PREFIX_HEIGHT + "Height "
            + PREFIX_WEIGHT + "Weight "
            + "(optional but at least specify one)\n"
            + PREFIX_ALLERGYTAG + "Allergy\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_HEIGHT + "175 "
            + PREFIX_WEIGHT + "60 "
            + PREFIX_ALLERGYTAG + "MergeConflict ";

    private final Nric nric;
    private final MedicalInformation medicalInformation;

    /**
     * Creates an AddCommand to add the specified {@code ContactInformation}
     */
    public AddMedInfoCommand(Nric nric, MedicalInformation medicalInformation) {
        requireNonNull(medicalInformation);
        this.nric = nric;
        this.medicalInformation = medicalInformation;
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

        if (!p.hasMedicalInformation()) {
            throw new CommandException(MESSAGE_PERSON_ALREADY_EXIST);
        } else {
            MedicalInformation mi = p.getMedicalInformation();
            // add the contact information to the person
            model.updatePersonMedicalInformation(nric, medicalInformation);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            // update the person to the model
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
