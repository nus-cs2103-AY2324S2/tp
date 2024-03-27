package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the addressbook that can generate the add command for the
 * person's details.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class FormattedCommandPerson extends Person {

    public FormattedCommandPerson(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    public String getFormattedCommand() {
        StringBuilder cmd = new StringBuilder();
        cmd.append(PREFIX_NAME);
        cmd.append(name);
        cmd.append(" " + PREFIX_PHONE);
        cmd.append(phone);
        cmd.append(" " + PREFIX_EMAIL);
        cmd.append(email);
        cmd.append(" " + PREFIX_ADDRESS);
        cmd.append(address);
        if (!this.tags.isEmpty()) {
            cmd.append(" " + PREFIX_TAG);
            String tag = tags.toArray()[0].toString();
            tag = tag.replace("[", "");
            tag = tag.replace("]", "");
            System.out.println(tag);
            cmd.append(tag);
        }
        return cmd.toString();
    }
}
