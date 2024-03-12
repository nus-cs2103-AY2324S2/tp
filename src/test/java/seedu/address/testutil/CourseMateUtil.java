package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.skill.Skill;

/**
 * A utility class for CourseMate.
 */
public class CourseMateUtil {

    /**
     * Returns an add command string for adding the {@code courseMate}.
     */
    public static String getAddCommand(CourseMate courseMate) {
        return AddCommand.COMMAND_WORD + " " + getCourseMateDetails(courseMate);
    }

    /**
     * Returns the part of command string for the given {@code courseMate}'s details.
     */
    public static String getCourseMateDetails(CourseMate courseMate) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + courseMate.getName().fullName + " ");
        sb.append(PREFIX_PHONE + courseMate.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + courseMate.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + courseMate.getAddress().value + " ");
        courseMate.getSkills().stream().forEach(
            s -> sb.append(PREFIX_SKILL + s.skillName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCourseMateDescriptor}'s details.
     */
    public static String getEditCourseMateDescriptorDetails(EditCommand.EditCourseMateDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getSkills().isPresent()) {
            Set<Skill> skills = descriptor.getSkills().get();
            if (skills.isEmpty()) {
                sb.append(PREFIX_SKILL);
            } else {
                skills.forEach(s -> sb.append(PREFIX_SKILL).append(s.skillName).append(" "));
            }
        }
        return sb.toString();
    }
}
