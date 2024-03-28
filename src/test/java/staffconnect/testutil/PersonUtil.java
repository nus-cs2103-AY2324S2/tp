package staffconnect.testutil;

import static staffconnect.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Set;

import staffconnect.logic.commands.AddCommand;
import staffconnect.logic.commands.EditCommand.EditPersonDescriptor;
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Person;
import staffconnect.model.tag.Tag;

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
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_FACULTY + person.getFaculty().toString() + " ");
        sb.append(PREFIX_VENUE + person.getVenue().value + " ");
        sb.append(PREFIX_MODULE + person.getModule().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getAvailabilities().stream().forEach(
            s -> sb.append(PREFIX_AVAILABILITY + s.value + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getFaculty().ifPresent(faculty -> sb.append(PREFIX_FACULTY).append(faculty.value).append(" "));
        descriptor.getVenue().ifPresent(venue -> sb.append(PREFIX_VENUE).append(venue.value).append(" "));
        descriptor.getModule().ifPresent(module -> sb.append(PREFIX_MODULE).append(module.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getAvailabilities().isPresent()) {
            Set<Availability> availabilities = descriptor.getAvailabilities().get();
            if (availabilities.isEmpty()) {
                sb.append(PREFIX_AVAILABILITY);
            } else {
                availabilities.forEach(s -> sb.append(PREFIX_AVAILABILITY).append(s.value).append(" "));
            }
        }
        return sb.toString();
    }
}
