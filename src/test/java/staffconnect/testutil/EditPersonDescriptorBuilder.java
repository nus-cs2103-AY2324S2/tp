package staffconnect.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import staffconnect.logic.commands.EditCommand.EditPersonDescriptor;
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Email;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Person;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;
import staffconnect.model.tag.Tag;

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
        descriptor.setFaculty(person.getFaculty());
        descriptor.setVenue(person.getVenue());
        descriptor.setModule(person.getModule());
        descriptor.setTags(person.getTags());
        descriptor.setAvailabilities(person.getAvailabilities());
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
     * Sets the {@code Faculty} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withFaculty(String faculty) {
        descriptor.setFaculty(new Faculty(faculty));
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withVenue(String venue) {
        descriptor.setVenue(new Venue(venue));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModule(String module) {
        descriptor.setModule(new Module(module));
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
     * Parses the {@code availabilities} into a {@code Set<Availability>} and set it to the
     * {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAvailabilities(String... availabilities) {
        Set<Availability> availabilitySet =
                Stream.of(availabilities).map(Availability::new).collect(Collectors.toSet());
        descriptor.setAvailabilities(availabilitySet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
