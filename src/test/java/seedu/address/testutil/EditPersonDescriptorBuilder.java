package seedu.address.testutil;

import java.util.Optional;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

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
        descriptor.setClassGroup(person.getClassGroup());
        descriptor.setTelegram(person.getTelegram());
        descriptor.setGithub(person.getGithub());
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
        descriptor.setPhone(Optional.of(new Phone(phone)));
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
     * Sets the {@code ClassGroup} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withClassGroup(String classGroup) {
        descriptor.setClassGroup(new ClassGroup(classGroup));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTelegram(String telegram) {
        descriptor.setTelegram(Optional.of(new Telegram(telegram)));
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGithub(String github) {
        descriptor.setGithub(Optional.of(new Github(github)));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
