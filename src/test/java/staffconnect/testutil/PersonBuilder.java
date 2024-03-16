package staffconnect.testutil;

import java.util.HashSet;
import java.util.Set;

import staffconnect.model.person.Email;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Person;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;
import staffconnect.model.tag.Tag;
import staffconnect.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_FACULTY = "Computing";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_VENUE = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_MODULE = "CS2103T";

    private Name name;
    private Phone phone;
    private Email email;
    private Faculty faculty;
    private Venue venue;
    private Module module;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        faculty = new Faculty(DEFAULT_FACULTY);
        venue = new Venue(DEFAULT_VENUE);
        module = new Module(DEFAULT_MODULE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        faculty = personToCopy.getFaculty();
        venue = personToCopy.getVenue();
        module = personToCopy.getModule();
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
     * Sets the {@code Faculty} of the {@code Person} that we are building.
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Person} that we are building.
     */
    public PersonBuilder withVenue(String venue) {
        this.venue = new Venue(venue);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Person} that we are building.
     */
    public PersonBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, faculty, venue, module, tags);
    }

}
