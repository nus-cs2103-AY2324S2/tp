package seedu.edulink.testutil;

import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_INTAKE;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.edulink.logic.commands.AddCommand;
import seedu.edulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;


/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ID + student.getId().id + " ");
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_MAJOR + student.getMajor().major + " ");
        sb.append(PREFIX_INTAKE + student.getIntake().toString() + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + student.getAddress().value + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.id).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getMajor().ifPresent(major -> sb.append(PREFIX_MAJOR).append(major.major)
            .append(" "));
        descriptor.getIntake().ifPresent(intake -> sb.append(PREFIX_INTAKE)
            .append(intake.intake.toString()).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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
