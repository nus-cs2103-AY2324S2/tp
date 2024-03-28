package seedu.hirehub.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.hirehub.logic.commands.SearchCommand.SearchPersonDescriptor;
import seedu.hirehub.model.person.Comment;
import seedu.hirehub.model.person.Country;
import seedu.hirehub.model.person.Email;
import seedu.hirehub.model.person.Name;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.model.person.Phone;
import seedu.hirehub.model.tag.Tag;

/**
 * A utility class to help with building SearchPersonDescriptor objects.
 */
public class SearchPersonDescriptorBuilder {

    private SearchPersonDescriptor descriptor;

    public SearchPersonDescriptorBuilder() {
        descriptor = new SearchPersonDescriptor();
    }

    public SearchPersonDescriptorBuilder(SearchPersonDescriptor descriptor) {
        this.descriptor = new SearchPersonDescriptor();
    }

    /**
     * Returns an {@code SearchPersonDescriptor} with fields containing {@code person}'s details
     */
    public SearchPersonDescriptorBuilder(Person person) {
        descriptor = new SearchPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setCountry(person.getCountry());
        descriptor.setComment(person.getComment());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code SearchPersonDescriptor} that we are building.
     */
    public SearchPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code SearchPersonDescriptor} that we are building.
     */
    public SearchPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code SearchPersonDescriptor} that we are building.
     */
    public SearchPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Country} of the {@code SearchPersonDescriptor} that we are building.
     */
    public SearchPersonDescriptorBuilder withCountry(String country) {
        descriptor.setCountry(new Country(country));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code SearchPersonDescriptor} that we are building.
     */
    public SearchPersonDescriptorBuilder withComment(String comment) {
        descriptor.setComment(new Comment(comment));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code SearchPersonDescriptor}
     * that we are building.
     */
    public SearchPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public SearchPersonDescriptor build() {
        return descriptor;
    }
}
