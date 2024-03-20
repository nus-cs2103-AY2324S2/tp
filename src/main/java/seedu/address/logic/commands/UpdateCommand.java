package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;


/**
 * Updates the details of an existing person in the address book.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_UPDATE_PERSON_SUCCESS = "Updated Person: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final UpdatePersonDescriptor updatePersonDescriptor;

    /**
     * @param index of the person in the filtered person list to update
     * @param updatePersonDescriptor details to update the person with
     */
    public UpdateCommand(Index index, UpdatePersonDescriptor updatePersonDescriptor) {
        requireNonNull(index);
        requireNonNull(updatePersonDescriptor);

        this.index = index;
        this.updatePersonDescriptor = new UpdatePersonDescriptor(updatePersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        boolean personNotFound = true;
        Nric targetNric = updatePersonDescriptor.getNric();
        Person personToUpdate = null;
        for (Person p : lastShownList) {
            if (p.getNric().equals(targetNric)) {
                personToUpdate = p;
                personNotFound = false;
                break;
            }
        }

        if (personNotFound) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        Person updatedPerson = createUpdatedPerson(personToUpdate, updatePersonDescriptor);

        if (!personToUpdate.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToUpdate, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UPDATE_PERSON_SUCCESS, Messages.format(updatedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToUpdate}
     * updated with {@code updatePersonDescriptor}.
     */
    private static Person createUpdatedPerson(Person personToUpdate, UpdatePersonDescriptor updatePersonDescriptor) {
        assert personToUpdate != null;
        Nric nric = personToUpdate.getNric();
        Name updatedName = updatePersonDescriptor.getName().orElse(personToUpdate.getName());
        Phone updatedPhone = updatePersonDescriptor.getPhone().orElse(personToUpdate.getPhone());
        //TODO: change personToEdit class to enable update of dob and sex
        DateOfBirth dob = personToUpdate.getDateOfBirth();
        Sex sex = personToUpdate.getSex();
        Status status = updatePersonDescriptor.getStatus().orElse(personToUpdate.getStatus());
        Email updatedEmail = updatePersonDescriptor.getEmail().orElse(personToUpdate.getEmail());
        Address updatedAddress = updatePersonDescriptor.getAddress().orElse(personToUpdate.getAddress());
        Set<Tag> updatedTags = updatePersonDescriptor.getTags().orElse(personToUpdate.getTags());

        return new Person(nric, updatedName, updatedPhone, updatedAddress, dob, sex, status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        UpdateCommand otherUpdateCommand = (UpdateCommand) other;
        return index.equals(otherUpdateCommand.index)
                && updatePersonDescriptor.equals(otherUpdateCommand.updatePersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("updatePersonDescriptor", updatePersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to update the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class UpdatePersonDescriptor {
        private Nric nric;
        private Name name;
        private Phone phone;
        private Address address;
        private DateOfBirth dateOfBirth;
        private Sex sex;
        private Status status;
        // Optional fields
        // Data fields
        private Set<Tag> tags = new HashSet<>();
        private Email email;
        private Country country;
        //Medical information
        private Allergies allergies;
        private BloodType bloodType;
        private Condition condition;
        private DateOfAdmission dateOfAdmission;
        private Diagnosis diagnosis;
        private Symptom symptom;

        public UpdatePersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdatePersonDescriptor(UpdatePersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is updated.
         */
        public boolean isAnyFieldUpdated() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setNric(Nric nric) {
            this.nric = nric;
        }

        public Nric getNric() {
            return nric;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDateOfBirth(DateOfBirth dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Optional<DateOfBirth> getDateOfBirth() {
            return Optional.ofNullable(dateOfBirth);
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public Optional<Sex> getSex() {
            return Optional.ofNullable(sex);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }


        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }

        public void setAllergies(Allergies allergies) {
            this.allergies = allergies;
        }

        public Optional<Allergies> getAllergies() {
            return Optional.ofNullable(allergies);
        }

        public void setBloodType(BloodType bloodType) {
            this.bloodType = bloodType;
        }

        public Optional<BloodType> getBloodType() {
            return Optional.ofNullable(bloodType);
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public Optional<Condition> getCondition() {
            return Optional.ofNullable(condition);
        }

        public void setDateOfAdmission(DateOfAdmission dateOfAdmission) {
            this.dateOfAdmission = dateOfAdmission;
        }

        public Optional<DateOfAdmission> getDateOfAdmission() {
            return Optional.ofNullable(dateOfAdmission);
        }

        public void setDiagnosis(Diagnosis diagnosis) {
            this.diagnosis = diagnosis;
        }

        public Optional<Diagnosis> getDiagnosis() {
            return Optional.ofNullable(diagnosis);
        }

        public void setSymptom(Symptom symptom) {
            this.symptom = symptom;
        }

        public Optional<Symptom> getSymptom() {
            return Optional.ofNullable(symptom);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdatePersonDescriptor)) {
                return false;
            }

            UpdatePersonDescriptor otherUpdatePersonDescriptor = (UpdatePersonDescriptor) other;
            return Objects.equals(nric, otherUpdatePersonDescriptor.nric)
                    && Objects.equals(name, otherUpdatePersonDescriptor.name)
                    && Objects.equals(phone, otherUpdatePersonDescriptor.phone)
                    && Objects.equals(address, otherUpdatePersonDescriptor.address)
                    && Objects.equals(dateOfBirth, otherUpdatePersonDescriptor.dateOfBirth)
                    && Objects.equals(sex, otherUpdatePersonDescriptor.sex)
                    && Objects.equals(status, otherUpdatePersonDescriptor.status)
                    && Objects.equals(email, otherUpdatePersonDescriptor.email)
                    && Objects.equals(country, otherUpdatePersonDescriptor.country)
                    && Objects.equals(allergies, otherUpdatePersonDescriptor.allergies)
                    && Objects.equals(bloodType, otherUpdatePersonDescriptor.bloodType)
                    && Objects.equals(condition, otherUpdatePersonDescriptor.condition)
                    && Objects.equals(dateOfAdmission, otherUpdatePersonDescriptor.dateOfAdmission)
                    && Objects.equals(diagnosis, otherUpdatePersonDescriptor.diagnosis)
                    && Objects.equals(symptom, otherUpdatePersonDescriptor.symptom)
                    && Objects.equals(tags, otherUpdatePersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("nric", nric)
                    .add("name", name)
                    .add("phone", phone)
                    .add("address", address)
                    .add("date of birth", dateOfBirth)
                    .add("sex", sex)
                    .add("status", status)
                    .add("email", email)
                    .add("country", country)
                    .add("allergies", allergies)
                    .add("bloodtype", bloodType)
                    .add("condition", condition)
                    .add("date of admission", dateOfAdmission)
                    .add("diagnosis", diagnosis)
                    .add("symptom", symptom)
                    .add("tags", tags)
                    .toString();
        }
    }
}
