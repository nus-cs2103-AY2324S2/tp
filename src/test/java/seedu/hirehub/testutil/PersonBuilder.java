package seedu.hirehub.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.hirehub.model.person.Comment;
import seedu.hirehub.model.person.Country;
import seedu.hirehub.model.person.Email;
import seedu.hirehub.model.person.Name;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.model.person.Phone;
import seedu.hirehub.model.tag.Tag;
import seedu.hirehub.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_COUNTRY = "SG";
    public static final String DEFAULT_COMMENT = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Country country;
    private Comment comment;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        country = new Country(DEFAULT_COUNTRY);
        comment = new Comment(DEFAULT_COMMENT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        country = personToCopy.getCountry();
        comment = personToCopy.getComment();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Set the {@code Set<Tag>} to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Sets the {@code Country} of the {@code Person} that we are building.
     */
    public PersonBuilder withCountry(String country) {
        this.country = new Country(country);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code Person} that we are building.
     */
    public PersonBuilder withComment(String comment) {
        this.comment = new Comment(comment);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, country, comment, tags);
    }

}
