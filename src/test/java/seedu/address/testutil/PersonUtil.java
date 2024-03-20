package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TERMSOFSERVICE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Client;
import seedu.address.model.person.Employee;
import seedu.address.model.person.Person;
import seedu.address.model.person.Products;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;

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
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark).append(" "));
        sb.append(PREFIX_ROLE + person.getRole() + " ");
        if (person instanceof Client) {
            Client client = (Client) person;
            client.getProducts().getProducts().stream().forEach(
                    s -> sb.append(PREFIX_PRODUCTS + s + " ")
            );
            sb.append(PREFIX_PREFERENCES + client.getPreferences().toString() + " ");
        } else if (person instanceof Employee) {
            Employee employee = (Employee) person;
            sb.append(PREFIX_DEPARTMENT + employee.getDepartment().toString() + " ");
            sb.append(PREFIX_JOBTITLE + employee.getJobTitle().toString() + " ");
            sb.append(PREFIX_SKILLS + employee.getSkills().toString() + " ");
        } else if (person instanceof Supplier) {
            Supplier supplier = (Supplier) person;
            supplier.getProducts().getProducts().stream().forEach(
                    s -> sb.append(PREFIX_PRODUCTS + s + " ")
            );
            sb.append(PREFIX_TERMSOFSERVICE + supplier.getTermsOfService().toString() + " ");
        }
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
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark).append(" "));
        if (descriptor.getProducts().isPresent()) {
            Products products = descriptor.getProducts().get();
            if (products.isEmpty()) {
                sb.append(PREFIX_PRODUCTS);
            } else {
                products.getProducts().forEach(s -> sb.append(PREFIX_PRODUCTS).append(s).append(" "));
            }
        }
        descriptor.getPreferences().ifPresent(preferences -> sb.append(PREFIX_PREFERENCES)
                .append(preferences).append(" "));
        descriptor.getDepartment().ifPresent(department -> sb.append(PREFIX_DEPARTMENT).append(department).append(" "));
        descriptor.getJobTitle().ifPresent(jobTitle -> sb.append(PREFIX_JOBTITLE).append(jobTitle).append(" "));
        descriptor.getSkills().ifPresent(skills -> sb.append(PREFIX_SKILLS).append(skills).append(" "));
        descriptor.getTermsOfService().ifPresent(termsOfService -> sb.append(PREFIX_TERMSOFSERVICE)
                .append(termsOfService).append(" "));
        return sb.toString();
    }
}
