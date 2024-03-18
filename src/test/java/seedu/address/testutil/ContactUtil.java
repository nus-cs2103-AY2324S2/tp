package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * A utility class for Contact.
 */
public class ContactUtil {

    /**
     * Returns an add command string for adding the {@code contact}.
     */
    public static String getAddCommand(Contact contact) {
        return AddCommand.COMMAND_WORD + " " + getContactDetails(contact);
    }

    /**
     * Returns the part of command string for the given {@code contact}'s details.
     */
    public static String getContactDetails(Contact contact) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + contact.getName().fullName + " ");
        sb.append(PREFIX_PHONE + contact.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + contact.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + contact.getAddress().value + " ");
        sb.append(PREFIX_GITHUB_USERNAME + contact.getGitHubUsername().username + " ");
        contact.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditContactDescriptorDetails(EditContactDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getGitHubUsername().ifPresent(gitHubUsername -> sb.append(PREFIX_GITHUB_USERNAME)
                .append(gitHubUsername.username).append(" "));
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
