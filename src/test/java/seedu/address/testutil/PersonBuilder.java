package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Bolt;
import seedu.address.model.person.Email;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Star;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_MAJOR = "Business";

    private Name name;
    private Phone phone;
    private Email email;
    private Major major;
    private Star star;
    private Bolt bolt;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        major = new Major(DEFAULT_MAJOR);
        star = Star.NO_STAR; // default value from Star
        bolt = Bolt.NO_BOLT; // default value from Bolt
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        major = personToCopy.getMajor();
        star = personToCopy.getStar();
        bolt = personToCopy.getBolt();
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
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        this.major = new Major(major);
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
     * Sets the {@code Star} of the {@code Person} that we are building.
     */
    public PersonBuilder withStar(Integer star) {
        this.star = new Star(star);
        return this;
    }

    /**
     * Sets the {@code Bolt} of the {@code Person} that we are building.
     */
    public PersonBuilder withBolt(Integer bolt) {
        this.bolt = new Bolt(bolt);
        return this;
    }

    /**
     * Builds a new Person.
     * @return A new Person built from the values given.
     */
    public Person build() {
        return new Person(name, phone, email, major, star, bolt, tags);
        // we do not include star here because our constructor creates a default value of new Star(0) and new Bolt(0)
    }

}
