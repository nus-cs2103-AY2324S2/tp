package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.model.person.medicalinformation.Weight;


/**
 * Deletes a person's contact from the clinic.
 */
public class DeleteMedicalCommand extends Command {
    public static final String COMMAND_WORD = "deletem";
    public static final String MESSAGE_SUCCESS = "Medical information deleted successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the medical information of a person. "
            + "Parameter: \n"
            + PREFIX_NRIC + "NRIC\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "S1234567D ";

    private final Nric nric;
    /**
     * Creates an AddCommand to add the specified {@code ContactInformation}
     */
    public DeleteMedicalCommand(Nric nric) {
        requireNonNull(nric);
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = model.findPersonByNric(nric);
        if (personToEdit == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        MedicalInformation medicalInformation = new MedicalInformation(new Height(""), new Weight(""));
        model.updatePersonMedicalInformation(nric, medicalInformation);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteMedicalCommand)) {
            return false;
        }
        return nric.equals(((DeleteMedicalCommand) other).nric);
    }
}
