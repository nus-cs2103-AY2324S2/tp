package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.illness.Illness;
import seedu.address.model.person.note.Note;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NRIC = "S1234567D";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_BIRTHDATE = "07-10-1999";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_DRUG_ALLERGY = "No allergy";
    private Nric nric;
    private Name name;
    private Gender gender;
    private BirthDate birthDate;
    private Phone phone;
    private Email email;
    private DrugAllergy drugAllergy;
    private Set<Illness> illnesses = new HashSet<>();
    private ObservableList<Note> notes = FXCollections.observableArrayList();

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
        gender = new Gender(DEFAULT_GENDER);
        birthDate = new BirthDate(DEFAULT_BIRTHDATE);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        drugAllergy = new DrugAllergy(DEFAULT_DRUG_ALLERGY);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        nric = personToCopy.getNric();
        name = personToCopy.getName();
        gender = personToCopy.getGender();
        birthDate = personToCopy.getBirthDate();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        drugAllergy = personToCopy.getDrugAllergy();
        this.illnesses.addAll(personToCopy.getIllnesses());
        this.notes.addAll(personToCopy.getNotes());
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
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code BirthDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthDate(String birthDate) {
        this.birthDate = new BirthDate(birthDate);
        return this;
    }

    /**
     * Sets the {@code DrugAllergy} of the {@code Person} that we are building.
     */
    public PersonBuilder withDrugAllergy(String drugAllergy) {
        this.drugAllergy = new DrugAllergy(drugAllergy);
        return this;
    }

    /**
     * Parses the {@code illness} into a {@code Set<Illness>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withIllnesses(String ... illness) {
        this.illnesses = SampleDataUtil.getIllnesses(illness);
        return this;
    }

    /**
     * Parses the {@code notes} into a {@code ObservableList<Note>} and set it to the {@code Person} that we are
     * building.
     */
    public PersonBuilder withNotes(Note[] notes) {
        this.notes = SampleDataUtil.getNotes(notes);
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

    public Person build() {
        return new Person(nric, name, gender, birthDate, phone, email, drugAllergy, illnesses, notes);
    }

}
