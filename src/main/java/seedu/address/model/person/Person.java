package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.note.Note;
import seedu.address.model.person.illness.Illness;

/**
 * Represents a Person in the patient book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Nric nric;
    private final Name name;
    private final Gender gender;
    private final BirthDate birthDate;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final DrugAllergy drugAllergy;
    private final Set<Illness> illnesses = new HashSet<>();
    private final ObservableList<Note> notes = FXCollections.observableArrayList();

    /**
     * Every field must be present and not null.
     */
    public Person(Nric nric, Name name, Gender gender, BirthDate birthDate,Phone phone,
                  Email email, DrugAllergy drugAllergy, Set<Illness> illnesses, ObservableList<Note> notes) {
        requireAllNonNull(nric, name, phone, email, illnesses, notes);
        this.nric = nric;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.drugAllergy = drugAllergy;
        this.illnesses.addAll(illnesses);
        this.notes.addAll(notes);
    }

    public Nric getNric() { return nric; }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public DrugAllergy getDrugAllergy() {
        return drugAllergy;
    }

    /**
     * Returns an immutable illness set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Illness> getIllnesses() {
        return Collections.unmodifiableSet(illnesses);
    }

    /**
     * Returns an immutable notes list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Note> getNotes() {
        return FXCollections.unmodifiableObservableList(notes);
    }

    /**
     * Returns true if both persons have the same nric.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
            && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return nric.equals(otherPerson.nric)
            && name.equals(otherPerson.name)
            && gender.equals(otherPerson.gender)
            && birthDate.equals(otherPerson.birthDate)
            && phone.equals(otherPerson.phone)
            && email.equals(otherPerson.email)
            && drugAllergy.equals(otherPerson.drugAllergy)
            && illnesses.equals(otherPerson.illnesses)
            && notes.equals(otherPerson.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nric, name, gender, birthDate, phone, email, drugAllergy, illnesses, notes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("nric", nric)
            .add("name", name)
            .add("gender", gender)
            .add("birthDate", birthDate)
            .add("phone", phone)
            .add("email", email)
            .add("drugAllergy", drugAllergy)
            .add("illnesses", illnesses)
            .add("notes", notes)
            .toString();
    }

    /**
     * Represents a builder for a {@link Person}.
     */
    public static class Builder {
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
         * Creates a {@code Builder} with the given parameters.
         */
        public Builder(Nric nric,
                       Name name,
                       Gender gender,
                       BirthDate birthDate,
                       Phone phone,
                       Email email,
                       DrugAllergy drugAllergy,
                       Set<Illness> illnesses,
                       ObservableList<Note> notes) {
            this.nric = nric;
            this.name = name;
            this.gender = gender;
            this.birthDate = birthDate;
            this.phone = phone;
            this.email = email;
            this.drugAllergy = drugAllergy;
            this.illnesses.addAll(illnesses);
            this.notes.addAll(notes);
        }

        /**
         * Initializes the {@code Builder} with the data of {@code person}.
         */
        public Builder(Person person) {
            this(person.getNric(),
                person.getName(),
                person.getGender(),
                person.getBirthDate(),
                person.getPhone(),
                person.getEmail(),
                person.getDrugAllergy(),
                person.getIllnesses(),
                person.getNotes());
        }

        public Nric getNric() { return nric; }
        public Name getName() {
            return name;
        }
        public Gender getGender() {
            return gender;
        }
        public BirthDate getBirthDate() {
            return birthDate;
        }
        public Phone getPhone() {
            return phone;
        }
        public Email getEmail() {
            return email;
        }
        public DrugAllergy getDrugAllergy() {
            return drugAllergy;
        }

        public Set<Illness> getIllnesses() {
            return illnesses;
        }

        public ObservableList<Note> getNotes() {
            return notes;
        }

        public Builder setNric(Nric nric) {
            requireNonNull(nric);

            this.nric = nric;
            return this;
        }

        public Builder setName(Name name) {
            requireNonNull(name);

            this.name = name;
            return this;
        }

        public Builder setGender(Gender gender) {
            requireNonNull(gender);

            this.gender = gender;
            return this;
        }

        public Builder setBirthDate(BirthDate birthDate) {
            requireNonNull(birthDate);

            this.birthDate = birthDate;
            return this;
        }

        public Builder setPhone(Phone phone) {
            requireNonNull(phone);

            this.phone = phone;
            return this;
        }

        public Builder setEmail(Email email) {
            requireNonNull(email);

            this.email = email;
            return this;
        }

        public Builder setDrugAllergy(DrugAllergy drugAllergy) {
            requireNonNull(drugAllergy);

            this.drugAllergy = drugAllergy;
            return this;
        }

        public Builder setIllnesses(Set<Illness> illnesses) {
            requireNonNull(illnesses);

            this.illnesses.clear();
            this.illnesses.addAll(illnesses);
            return this;
        }

        public Builder setNotes(ObservableList<Note> notes) {
            requireNonNull(notes);

            this.notes.clear();
            this.notes.addAll(notes);
            return this;
        }

        /**
         * Builds a {@link Person} with the latest values.
         */
        public Person build() {
            return new Person(nric, name, gender, birthDate, phone, email, drugAllergy, illnesses, notes);
        }
    }
}
