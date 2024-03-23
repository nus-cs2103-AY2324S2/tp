package tutorpro.testutil;

import java.util.Set;

import tutorpro.logic.commands.AddCommand;
import tutorpro.logic.commands.EditCommand;
import tutorpro.logic.parser.CliSyntax;
import tutorpro.model.person.student.Student;
import tutorpro.model.person.student.Subject;
import tutorpro.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Student person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Student person) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + person.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(CliSyntax.PREFIX_LEVEL + person.getLevel().toString() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tagName + " ")
        );
        person.getSubjects().stream().forEach(
                s -> sb.append(CliSyntax.PREFIX_SUBJECT + s.toString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(CliSyntax.PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(CliSyntax.PREFIX_ADDRESS)
                .append(address.value).append(" "));
        descriptor.getLevel().ifPresent(level -> sb.append(CliSyntax.PREFIX_LEVEL).append(level).append(" "));
        if (descriptor.getSubjects().isPresent()) {
            Set<Subject> subjects = descriptor.getSubjects().get();
            if (subjects.isEmpty()) {
                sb.append(CliSyntax.PREFIX_SUBJECT);
            } else {
                subjects.forEach(s -> sb.append(CliSyntax.PREFIX_SUBJECT).append(s.toString()).append(" "));
            }
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
