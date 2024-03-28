package seedu.address.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.model.exam.Exam;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Score;
import seedu.address.model.student.Matric;
import seedu.address.model.student.Reflection;
import seedu.address.model.student.Studio;
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
    public static final String DEFAULT_MATRIC = "A1234567X";
    public static final String DEFAULT_REFLECTION = "R1";
    public static final String DEFAULT_STUDIO = "S2";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Matric matric;
    private Reflection reflection;
    private Studio studio;
    private Map<Exam, Score> scores;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        matric = new Matric(DEFAULT_MATRIC);
        reflection = new Reflection(DEFAULT_REFLECTION);
        studio = new Studio(DEFAULT_STUDIO);
        scores = new HashMap<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        matric = personToCopy.getMatric();
        reflection = personToCopy.getReflection();
        studio = personToCopy.getStudio();
        scores = personToCopy.getScores();
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
     * Sets the {@code Matric} of the {@code Person} that we are building.
     * @param matric matric number
     * @return PersonBuilder
     */
    public PersonBuilder withMatric(String matric) {
        this.matric = new Matric(matric);
        return this;
    }

    /**
     * Sets the {@code Studio} of the {@code Person} that we are building.
     * @param studio studio number
     * @return PersonBuilder
     */
    public PersonBuilder withStudio(String studio) {
        this.studio = new Studio(studio);
        return this;
    }

    /**
     * Sets the {@code Reflection} of the {@code Person} that we are building.
     * @param reflection reflection
     * @return PersonBuilder
     */
    public PersonBuilder withReflection(String reflection) {
        this.reflection = new Reflection(reflection);
        return this;
    }

    /**
     * Sets the {@code Exam} and {@code Score} of the {@code Person} that we are building.
     * @param scores exam scores
     * @return PersonBuilder
     */
    public PersonBuilder withScores(Map<Exam, Score> scores) {
        this.scores = scores;
        return this;
    }

    /**
     * Builds the person object.
     * @return Person
     */
    public Person build() {
        return new Person(name, phone, email, address, tags, matric, reflection, studio, scores);
    }

}
