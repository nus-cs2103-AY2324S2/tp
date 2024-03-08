package educonnect.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import educonnect.logic.commands.EditCommand.EditPersonDescriptor;
import educonnect.model.person.Email;
import educonnect.model.person.Name;
import educonnect.model.person.Person;
import educonnect.model.person.StudentId;
import educonnect.model.person.TelegramHandle;
import educonnect.model.tag.Tag;

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
        descriptor.setStudentId(person.getStudentId());
        descriptor.setEmail(person.getEmail());
        descriptor.setTelegramHandle(person.getTelegramHandle());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withStudentId(String studentId) {
        descriptor.setStudentId(new StudentId(studentId));
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
     * Sets the {@code Telegram Handle} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTelegramHandle(String telegramHandle) {
        descriptor.setTelegramHandle(new TelegramHandle(telegramHandle));
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

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
