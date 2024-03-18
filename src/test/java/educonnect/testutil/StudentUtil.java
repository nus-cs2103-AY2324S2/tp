package educonnect.testutil;

import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_NAME;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TAG;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE;

import java.util.Set;

import educonnect.logic.commands.AddCommand;
import educonnect.logic.commands.EditCommand.EditStudentDescriptor;
import educonnect.model.student.Student;
import educonnect.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME).append(student.getName().fullName).append(" ");
        sb.append(PREFIX_STUDENT_ID).append(student.getStudentId().value).append(" ");
        sb.append(PREFIX_EMAIL).append(student.getEmail().value).append(" ");
        sb.append(PREFIX_TELEGRAM_HANDLE).append(student.getTelegramHandle().value).append(" ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
        sb.append(PREFIX_TIMETABLE).append(student.getTimetable().convertToCommandString());

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getStudentId().ifPresent(phone -> sb.append(PREFIX_STUDENT_ID).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTelegramHandle().ifPresent(
                address -> sb.append(PREFIX_TELEGRAM_HANDLE).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getTimetable().ifPresent(
                timetable -> sb.append(PREFIX_TIMETABLE).append(timetable.convertToCommandString()));

        return sb.toString();
    }
}
