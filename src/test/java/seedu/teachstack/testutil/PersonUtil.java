package seedu.teachstack.testutil;

import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.Set;

import seedu.teachstack.logic.commands.AddCommand;
import seedu.teachstack.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.teachstack.model.group.Group;
import seedu.teachstack.model.person.Person;

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
        sb.append(PREFIX_STUDENTID + person.getStudentId().id + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_GRADE + person.getGrade().value + " ");
        person.getGroups().stream().forEach(
                s -> sb.append(PREFIX_GROUP + s.groupName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getStudentId().ifPresent(studentId -> sb.append(PREFIX_STUDENTID).append(studentId.id).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getGrade().ifPresent(grade -> sb.append(PREFIX_GRADE).append(grade.value).append(" "));
        if (descriptor.getGroups().isPresent()) {
            Set<Group> groups = descriptor.getGroups().get();
            if (groups.isEmpty()) {
                sb.append(PREFIX_GROUP);
            } else {
                groups.forEach(s -> sb.append(PREFIX_GROUP).append(s.groupName).append(" "));
            }
        }
        return sb.toString();
    }
}
