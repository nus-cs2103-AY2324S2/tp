package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CreateClassCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Classes;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Attendance;

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

    public static String getClassCommand(Classes classes) {
        return CreateClassCommand.COMMAND_WORD + " " + PREFIX_CLASS + classes.getCourseCode();
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_STUDENTID + person.getStudentId().value + " ");
        person.getAttendances().stream().forEach(
            s -> sb.append(PREFIX_ATTENDANCE_RECORD + s.attendanceName.getDate() + ", "
                    + s.attendanceName.getStatus() + " ")
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
        descriptor.getStudentId().ifPresent(address -> sb.append(PREFIX_STUDENTID).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Attendance> attendances = descriptor.getTags().get();
            if (attendances.isEmpty()) {
                sb.append(PREFIX_ATTENDANCE_RECORD);
            } else {
                attendances.forEach(s -> sb.append(PREFIX_ATTENDANCE_RECORD).append(s.attendanceName).append(" "));
            }
        }
        return sb.toString();
    }
}
