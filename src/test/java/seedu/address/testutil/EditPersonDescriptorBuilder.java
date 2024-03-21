package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employee;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Skills;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.TermsOfService;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private final EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setRemark(person.getRemark());
        if (person instanceof Client) {
            descriptor.setProducts(((Client) person).getProducts());
            descriptor.setPreferences(((Client) person).getPreferences());
        } else if (person instanceof Supplier) {
            descriptor.setProducts(((Supplier) person).getProducts());
            descriptor.setTermsOfService(((Supplier) person).getTermsOfService());
        } else if (person instanceof Employee) {
            descriptor.setDepartment(((Employee) person).getDepartment());
            descriptor.setJobTitle(((Employee) person).getJobTitle());
            descriptor.setSkills(((Employee) person).getSkills());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    /**
     * Sets the {@code Products} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withProducts(String... products) {
        Products productList = new Products(Arrays.asList(products));
        descriptor.setProducts(productList);
        return this;
    }

    /**
     * Sets the {@code Preferences} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPreferences(String preferences) {
        descriptor.setPreferences(preferences);
        return this;
    }

    /**
     * Sets the {@code TermsOfService} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTermsOfService(String termsOfService) {
        descriptor.setTermsOfService(new TermsOfService(termsOfService));
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDepartment(String department) {
        descriptor.setDepartment(new Department(department));
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withJobTitle(String jobTitle) {
        descriptor.setJobTitle(new JobTitle(jobTitle));
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skills>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withSkills(String... skills) {
        final Set<String> skillsSet = new HashSet<>();
        for (String skill : skills) {
            skillsSet.add(skill);
        }
        descriptor.setSkills(new Skills(skillsSet));
        return this;
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptor build() {
        return descriptor;
    }
}
