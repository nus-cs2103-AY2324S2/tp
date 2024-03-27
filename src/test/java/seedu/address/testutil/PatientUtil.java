package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOBBY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Patient.
 */
public class PatientUtil {

    /**
     * Returns an add command string for adding the {@code patient}.
     */
    public static String getAddCommand(Patient patient) {
        return AddCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PID + patient.getPatientHospitalId().patientHospitalId + " ");
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_PREFERRED_NAME + patient.getPreferredName().preferredName + " ");
        sb.append(PREFIX_FOOD_PREFERENCE + patient.getFoodPreference().foodPreference + " ");
        sb.append(PREFIX_FAMILY_CONDITION + patient.getFamilyCondition().familyCondition + " ");
        sb.append(PREFIX_HOBBY + patient.getHobby().hobby + " ");
        patient.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPatientHospitalId().ifPresent(patientHospitalId -> sb.append(PREFIX_PID)
            .append(patientHospitalId.patientHospitalId).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPreferredName().ifPresent(preferredName -> sb.append(PREFIX_PREFERRED_NAME)
            .append(preferredName.preferredName).append(" "));
        descriptor.getFoodPreference().ifPresent(foodPreference -> sb.append(PREFIX_FOOD_PREFERENCE)
            .append(foodPreference.foodPreference).append(" "));
        descriptor.getFamilyCondition().ifPresent(familyCondition -> sb.append(PREFIX_FAMILY_CONDITION)
            .append(familyCondition.familyCondition).append(" "));
        descriptor.getHobby().ifPresent(hobby -> sb.append(PREFIX_HOBBY).append(hobby.hobby).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
