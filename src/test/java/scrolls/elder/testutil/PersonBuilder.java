package scrolls.elder.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import scrolls.elder.model.person.Address;
import scrolls.elder.model.person.Email;
import scrolls.elder.model.person.Name;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.PersonFactory;
import scrolls.elder.model.person.Phone;
import scrolls.elder.model.person.Role;
import scrolls.elder.model.tag.Tag;
import scrolls.elder.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_ID = "0";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_VOLUNTEER_ROLE_STRING = "volunteer";

    private int id;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Role role;
    private Optional<Name> pairedWithName;
    private Optional<Integer> pairedWithId;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        id = -1;
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        role = new Role(DEFAULT_VOLUNTEER_ROLE_STRING);
        pairedWithName = Optional.empty();
        pairedWithId = Optional.empty();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        id = personToCopy.getPersonId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        role = personToCopy.getRole();
        pairedWithName = personToCopy.getPairedWithName();
        pairedWithId = personToCopy.getPairedWithId();
    }

    /**
     * Sets the {@code id} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(int id) {
        this.id = id;
        return this;
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
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
     * Sets the {@code isVolunteer} of the {@code Person} that we are building.
     */
    public PersonBuilder withRole(String roleString) {
        this.role = new Role(roleString);
        return this;
    }

    /**
     * Sets the {@code pairedWithName} of the {@code Person} that we are building.
     */
    public PersonBuilder withPairedWithName(Optional<Name> pairedWithName) {
        this.pairedWithName = pairedWithName;
        return this;
    }

    /**
     * Sets the {@code pairedWithId} of the {@code Person} that we are building.
     */
    public PersonBuilder withPairedWithID(Optional<Integer> pairedWithId) {
        this.pairedWithId = pairedWithId;
        return this;
    }

    /**
     * Builds a Person based on the fields in the person builder
     */
    public Person build() {
        return PersonFactory.withIdFromParams(id, name, phone, email, address, role, tags, pairedWithName,
                pairedWithId);
    }

}
