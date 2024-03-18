package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMaintainerCommand;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditMaintainerCommand;
import seedu.address.logic.commands.EditMaintainerCommand.EditMaintainerDescriptor;
import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

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

    public static String getAddStaffCommand(Staff person) {
        return AddStaffCommand.COMMAND_WORD + " " + getStaffDetails(person);
    }

    public static String getAddSupplierCommand(Supplier person) {
        return AddSupplierCommand.COMMAND_WORD + " " + getSupplierDetails(person);
    }

    public static String getAddMaintainerCommand(Maintainer person) {
        return AddMaintainerCommand.COMMAND_WORD + " " + getMaintainerDetails(person);
    }

    /**
     * Returns an edit command string for adding the {@code person}.
     */
    public static String getEditCommand(Person person) {
        return EditCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    public static String getEditStaffCommand(Staff person) {
        return EditStaffCommand.COMMAND_WORD + " " + getStaffDetails(person);
    }

    public static String getEditSupplierCommand(Supplier person) {
        return EditSupplierCommand.COMMAND_WORD + " " + getSupplierDetails(person);
    }

    public static String getEditMaintainerCommand(Maintainer person) {
        return EditMaintainerCommand.COMMAND_WORD + " " + getMaintainerDetails(person);
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
        /*
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
         */
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code staff}'s details.
     */
    public static String getStaffDetails(Staff person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_SALARY + person.getSalary().value + " ");
        sb.append(PREFIX_EMPLOYMENT + person.getEmployment().employment + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code supplier}'s details.
     */
    public static String getSupplierDetails(Supplier person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_PRODUCT + person.getProduct().product + " ");
        sb.append(PREFIX_PRICE + person.getPrice().price + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code maintainer}'s details.
     */
    public static String getMaintainerDetails(Maintainer person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_SKILL + person.getSkill().skill + " ");
        sb.append(PREFIX_COMMISSION + person.getCommission().commission + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStaffDescriptor}'s details.
     */
    public static String getEditStaffDescriptorDetails(EditStaffDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.value).append(" "));
        descriptor.getEmployment().ifPresent(employment -> sb.append(PREFIX_EMPLOYMENT)
                .append(employment.employment).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditSupplierDescriptor}'s details.
     */
    public static String getEditSupplierDescriptorDetails(EditSupplierDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getProduct().ifPresent(product -> sb.append(PREFIX_PRODUCT).append(product.product).append(" "));
        descriptor.getPrice().ifPresent(price -> sb.append(PREFIX_PRICE).append(price.price).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditMaintainerDescriptor}'s details.
     */
    public static String getEditMaintainerDescriptorDetails(EditMaintainerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getSkill().ifPresent(skill -> sb.append(PREFIX_SKILL).append(skill.skill).append(" "));
        descriptor.getCommission().ifPresent(commission -> sb.append(PREFIX_COMMISSION)
                .append(commission.commission).append(" "));
        return sb.toString();
    }
}
