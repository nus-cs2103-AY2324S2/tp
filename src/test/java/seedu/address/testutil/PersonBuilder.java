package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Allergies;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Country;
import seedu.address.model.person.DateOfAdmission;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Status;
import seedu.address.model.person.Symptom;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {
    // Mandatory fields
    public static final String DEFAULT_NRIC = "T1234567B";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_DOB = "1998-07-03";
    public static final String DEFAULT_SEX = "M";
    // Data fields
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STATUS = "HEALTHY";
    /*
    public static final String DEFAULT_EMAIL = "test@email.com";
    public static final String DEFAULT_ALLERGIES = "Peanuts";
    public static final String[] DEFAULT_BLOODTYPE = {"A", "POSITIVE"};
    public static final String DEFAULT_COUNTRY = "Singapore";
    // Medical history
    public static final String DEFAULT_CONDITION = "High blood pressure";
    public static final String DEFAULT_DOA = "2024-01-01";
    public static final String DEFAULT_DIAGNOSIS = "Runny nose";
    public static final String DEFAULT_SYMPTOM = "Sneezing, sniffing";
    */
    // Mandatory fields
    private Nric nric;
    private Name name;
    private Phone phone;
    private DateOfBirth dateOfBirth;
    private Sex sex;
    // Data fields
    private Address address;
    private Allergies allergies;
    private BloodType bloodType;
    private Country country;
    private Email email;
    //Medical history
    private Condition condition;
    private DateOfAdmission dateOfAdmission;
    private Diagnosis diagnosis;
    private Status status;
    private Symptom symptom;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        dateOfBirth = new DateOfBirth(DEFAULT_DOB);
        sex = new Sex(DEFAULT_SEX);
        status = new Status(DEFAULT_STATUS);

        /*
        allergies = new Allergies(DEFAULT_ALLERGIES);
        bloodType = new BloodType(DEFAULT_BLOODTYPE[0], DEFAULT_BLOODTYPE[1]);
        country = new Country(DEFAULT_COUNTRY);
        email = new Email(DEFAULT_EMAIL);

        condition = new Condition(DEFAULT_CONDITION);
        dateOfAdmission = new DateOfAdmission(DEFAULT_DOA);
        diagnosis = new Diagnosis(DEFAULT_DIAGNOSIS);
        symptom = new Symptom(DEFAULT_SYMPTOM);
        */
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        nric = personToCopy.getNric();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        address = personToCopy.getAddress();
        dateOfBirth = personToCopy.getDateOfBirth();
        sex = personToCopy.getSex();
        address = personToCopy.getAddress();
        status = personToCopy.getStatus();

        /*
        allergies = personToCopy.getAllergies();
        bloodType = personToCopy.getBloodType();
        country = personToCopy.getCountry();
        email = personToCopy.getEmail();

        condition = personToCopy.getCondition();
        dateOfAdmission = personToCopy.getDateOfAdmission();
        diagnosis = personToCopy.getDiagnosis();
        symptom = personToCopy.getSymptom();
         */
    }

    /**
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public PersonBuilder withNric(String nric) {
        this.nric = new Nric(nric);
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
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
    public PersonBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Person} that we are building.
     */
    public PersonBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withCountry(String country) {
        this.country = new Country(country);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withAllergies(String allergies) {
        this.allergies = new Allergies(allergies);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withCondition(String condition) {
        this.condition = new Condition(condition);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = new DateOfAdmission(dateOfAdmission);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withDiagnosis(String diagnosis) {
        this.diagnosis = new Diagnosis(diagnosis);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withSymptom(String symptom) {
        this.symptom = new Symptom(symptom);
        return this;
    }

    /**
     * Returns a Person object with fields initialised to that of PersonBuilder object.
     */
    public Person build() {
        Person p = new Person(nric, name, phone, address, dateOfBirth, sex, status);
        /*
        p.setEmail(email);
        p.setCountry(country);
        p.setAllergies(allergies);
        p.setBloodType(bloodType);
        p.setCondition(condition);
        p.setDateOfAdmission(dateOfAdmission);
        p.setDiagnosis(diagnosis);
        p.setSymptom(symptom);
         */
        return p;
    }
}
