package vitalconnect.testutil;

import java.util.HashSet;
import java.util.Set;

import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.identificationinformation.Name;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.allergytag.AllergyTag;
import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.model.person.medicalinformation.Weight;
import vitalconnect.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_NRIC = "S7654321F";
    public static final String DEFAULT_EMAIL = "";
    public static final String DEFAULT_PHONE = "";
    public static final String DEFAULT_ADDRESS = "";
    public static final String DEFAULT_HEIGHT = "170";
    public static final String DEFAULT_WEIGHT = "60";

    private Name name;
    private Nric nric;
    private Email email;
    private Phone phone;
    private Address address;
    private Height height;
    private Weight weight;
    private Set<AllergyTag> allergyTags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        nric = new Nric(DEFAULT_NRIC);
        email = new Email(DEFAULT_EMAIL);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        height = new Height(DEFAULT_HEIGHT);
        weight = new Weight(DEFAULT_WEIGHT);
        allergyTags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getIdentificationInformation().getName();
        nric = personToCopy.getIdentificationInformation().getNric();
        email = personToCopy.getContactInformation().getEmail();
        phone = personToCopy.getContactInformation().getPhone();
        address = personToCopy.getContactInformation().getAddress();

        allergyTags = new HashSet<>(personToCopy.getMedicalInformation().getAllergyTag());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code allergyTags} into a {@code Set<AllergyTag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.allergyTags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public PersonBuilder withNric(String nric) {
        this.nric = new Nric(nric);
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Build a person.
     */
    public Person build() {
        return new Person(new IdentificationInformation(name, nric),
            new ContactInformation(email, phone, address), new MedicalInformation(height, weight, allergyTags));
    }

}
