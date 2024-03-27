package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_MEDICAL_INFO_NOT_FOUND;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_APPENDTAG;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_OVERWRITETAG;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.AllergyTag;
import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.model.person.medicalinformation.Weight;

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
        + PREFIX_OVERWRITETAG + "ALLERGY "
        + PREFIX_APPENDTAG + "ALLERGY \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NRIC + "S1234567D "
        + PREFIX_HEIGHT + "180 "
        + PREFIX_APPENDTAG + "Peanuts";

    private final Nric nric;
    private final Height height;
    private final Weight weight;
    private final Set<AllergyTag> overwriteTag;
    private final Set<AllergyTag> appendTag;

    /**
     * @param nric of the person in the filtered person list to edit
     * @param height details to edit the person with
     * @param weight details to edit the person with
     * @param overwriteTag details to edit the person with
     * @param appendTag details to edit the person with
     */
    public EditMedicalCommand(Nric nric, Height height, Weight weight,
                              Set<AllergyTag> overwriteTag, Set<AllergyTag> appendTag) {
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
            throw new CommandException(MESSAGE_MEDICAL_INFO_NOT_FOUND);
        }

        if (height != null) {
            medicalInformation.setHeight(height);
        }

        if (weight != null) {
            medicalInformation.setWeight(weight);
        }

        if (overwriteTag != null) {
            medicalInformation.setAllergyTag(overwriteTag);
        }

        if (appendTag != null) {
            for (AllergyTag tag : appendTag) {
                medicalInformation.appendAllergyTag(tag);
            }
        }

        model.updatePersonMedicalInformation(nric, medicalInformation);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
