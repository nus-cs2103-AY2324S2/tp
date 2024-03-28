package seedu.address.testutil;

import static seedu.address.model.person.fields.Address.PREFIX_ADDRESS;
import static seedu.address.model.person.fields.Assets.PREFIX_ASSET;
import static seedu.address.model.person.fields.Email.PREFIX_EMAIL;
import static seedu.address.model.person.fields.Name.PREFIX_NAME;
import static seedu.address.model.person.fields.Phone.PREFIX_PHONE;
import static seedu.address.model.person.fields.Tags.PREFIX_TAG;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.person.Person;

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
        sb.append(PREFIX_NAME).append(person.getName()).append(" ");
        sb.append(PREFIX_PHONE).append(person.getPhone()).append(" ");
        sb.append(PREFIX_EMAIL).append(person.getEmail()).append(" ");
        sb.append(PREFIX_ADDRESS).append(person.getAddress()).append(" ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG).append(s.get()).append(" ")
        );
        person.getAssets().stream().forEach(
                s -> sb.append(PREFIX_ASSET).append(s.get()).append(" ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address).append(" "));
        descriptor.getTags().ifPresent(
                tags -> tags.stream().forEach(tag -> sb.append(PREFIX_TAG).append(tag.get()).append(" ")));
        descriptor.getAssets().ifPresent(
                assets -> assets.stream().forEach(asset -> sb.append(PREFIX_ASSET).append(asset.get()).append(" ")));
        return sb.toString();
    }

}
