package vitalconnect.testutil;

import static vitalconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_ALLERGYTAG;

import java.util.Set;

import vitalconnect.logic.commands.AddCommand;
import vitalconnect.logic.commands.EditCommand.EditPersonDescriptor;
import vitalconnect.model.person.Person;
import vitalconnect.model.allergytag.AllergyTag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getIdentificationInformation().getName().fullName + " ");
        sb.append(PREFIX_NRIC + person.getIdentificationInformation().getNric().nric + " ");
        person.getMedicalInformation().getAllergyTag().stream().forEach(
            s -> sb.append(PREFIX_ALLERGYTAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getNric().ifPresent(ic -> sb.append(PREFIX_NRIC).append(ic.nric).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<AllergyTag> allergyTags = descriptor.getTags().get();
            if (allergyTags.isEmpty()) {
                sb.append(PREFIX_ALLERGYTAG);
            } else {
                allergyTags.forEach(s -> sb.append(PREFIX_ALLERGYTAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
