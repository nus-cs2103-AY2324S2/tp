package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.Address;
import seedu.address.model.person.ClientStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Policy;
import seedu.address.model.person.Relationship;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    public static final String DEFAULT_RELATIONSHIP = "client";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Policy> policies;
    private Relationship relationship;
    private ClientStatus clientStatus;
    private Set<Tag> tags;

    private List<Meeting> meetings;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        policies = new HashSet<>();
        clientStatus = ClientStatus.initClientStatus();
        relationship = new Relationship(DEFAULT_RELATIONSHIP);
        tags = new HashSet<>();
        meetings = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        policies = new HashSet<>(personToCopy.getPolicies());
        relationship = personToCopy.getRelationship();
        clientStatus = personToCopy.getClientStatus();
        tags = new HashSet<>(personToCopy.getTags());
        meetings = new ArrayList<>(personToCopy.getMeetings());
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
     * Sets the {@code Policy} of the {@code Person} that we are building.
     */
    public PersonBuilder withPolicy(String... policy) {
        if (policy.length == 0) {
            this.policies = new HashSet<>();
            return this;
        }

        Set<Policy> newPolicies = Arrays.stream(policy)
                .map(SampleDataUtil::parsePolicy) // Call parsePolicy from SampleDataUtil
                .collect(Collectors.toSet());
        this.policies.addAll(newPolicies);
        return this;
    }

    /**
     * Sets the {@code Relationship} of the {@code Person} that we are building.
     */
    public PersonBuilder withRelationship(String relationship) {
        this.relationship = new Relationship(relationship);
        return this;
    }

    /**
     * Sets the {@code ClientStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withClientStatus(String clientStatus) {
        this.clientStatus = new ClientStatus(Integer.parseInt(clientStatus));
        return this;
    }

    /**
     * Adds a {@code Meeting} to the {@code Person} that we are building.
     */
    public PersonBuilder withMeeting(Meeting meeting) {
        this.meetings.add(meeting);
        return this;
    }

    /**
     * Builds and returns a new Person object with the specified details.
     *
     * @return A new Person object with the specified details.
     */
    public Person build() {
        Person p = new Person(name, phone, email, address, relationship, policies, clientStatus, tags);

        p.setMeetings(this.meetings);

        return p;
    }

}
