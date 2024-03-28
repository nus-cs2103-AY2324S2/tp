package seedu.realodex.testutil;

import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.realodex.logic.commands.AddCommand;
import seedu.realodex.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.tag.Tag;

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
        sb.append(PREFIX_INCOME + person.getIncome().toString() + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_FAMILY + person.getFamily().toString() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName.toString() + " ")
        );
        sb.append(PREFIX_REMARK + person.getRemark().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getIncome().ifPresent(income -> sb.append(PREFIX_INCOME).append(income.toString()).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getFamily().ifPresent(family -> sb.append(PREFIX_FAMILY).append(family.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.toString()).append(" "));
        return sb.toString();
    }
}
