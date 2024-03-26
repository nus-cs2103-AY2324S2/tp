package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_CONTACT_INFO_NOT_FOUND;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_ALLERGYTAG;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_WEIGHT;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.medicalinformation.AllergyTag;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.Weight;
import vitalconnect.model.person.identificationinformation.Nric;

import java.util.Set;

/**
 * Edits the medical information of an existing person in the clinic.
 */
public class EditMedicalCommand extends Command {
    public static final String COMMAND_WORD = "editm";
    public static final String MESSAGE_SUCCESS = "Medical information updated successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit medical information of a person. "
        + "Parameters:\n"
        + PREFIX_NRIC + "NRIC "
        + PREFIX_HEIGHT + "HEIGHT "
        + PREFIX_WEIGHT + "WEIGHT "
        + PREFIX_ALLERGYTAG + "ALLERGY \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NRIC + "S1234567D "
        + PREFIX_HEIGHT + "180 "
        + PREFIX_ALLERGYTAG + "Peanuts";

    private final Nric nric;
    private final Height height;
    private final Weight weight;
    private final Set<AllergyTag> overwriteTag;
    private final Set<AllergyTag> appendTag;

    public EditMedicalCommand(Nric nric, Height height, Weight weight, Set<AllergyTag> overwriteTag, Set<AllergyTag> appendTag) {
        this.nric = nric;
        this.height = height;
        this.weight = weight;
        this.overwriteTag = overwriteTag;
        this.appendTag = appendTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person p = model.findPersonByNric(nric);
        if (p == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }
        MedicalInformation medicalInformation = p.getMedicalInformation();
        if (medicalInformation.isEmpty()) {
            throw new CommandException(MESSAGE_CONTACT_INFO_NOT_FOUND);
        }

        if (!height.isEmpty()) {
            medicalInformation.setHeight(height);
        }

        if (!weight.isEmpty()) {
            medicalInformation.setWeight(weight);
        }

        if (!overwriteTag.isEmpty()) {
            medicalInformation.setAllergyTag(overwriteTag);
        }

        if (!appendTag.isEmpty()) {
            for (AllergyTag tag : appendTag) {
                medicalInformation.appendAllergyTag(tag);
            }
        }

        model.updatePersonMedicalInformation(nric, medicalInformation);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
