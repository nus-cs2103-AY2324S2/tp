package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANKDETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENTTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIRSTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.BankDetails;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.WorkHours;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person"
            + " identified "
            + "by the phone number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: Phone number"
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_FIRSTNAME + "FIRST NAME] "
            + "[" + PREFIX_LASTNAME + "LAST NAME] "
            + "[" + PREFIX_EMPLOYMENTTYPE + "EMPLOYMENT TYPE] "
            + "[" + PREFIX_BANKDETAILS + "BANK DETAILS] "
            + "[" + PREFIX_SEX + "SEX] "
            + "Example: " + COMMAND_WORD + " 85789476 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Phone number;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param phoneNumber                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Phone phoneNumber, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(phoneNumber);
        requireNonNull(editPersonDescriptor);

        this.number = phoneNumber;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;
        Name updatedFirstName = editPersonDescriptor.getFirstName().orElse(personToEdit.getFirstName());
        Name updatedLastName = editPersonDescriptor.getLastName().orElse(personToEdit.getLastName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Sex updatedSex = editPersonDescriptor.getSex().orElse(personToEdit.getSex());
        EmploymentType updatedEmploymentType = editPersonDescriptor.getEmploymentType()
                .orElse(personToEdit.getEmploymentType());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        BankDetails updatedBankDetails = editPersonDescriptor.getBankDetails().orElse(personToEdit.getBankDetails());
        WorkHours updatedWorkHours = editPersonDescriptor.getHoursWorked().orElse(personToEdit.getWorkHours());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedFirstName, updatedLastName, updatedPhone, updatedSex, updatedEmploymentType,
                updatedAddress, updatedBankDetails, updatedWorkHours, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        boolean exists = false;
        Person personToEdit = null;
        for (Person person : lastShownList) {
            if (person.getPhone().equals(number)) {
                exists = true;
                personToEdit = person;
                break;
            }
        }
        if (!exists) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return number.equals(otherEditCommand.number)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", number)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name firstName;
        private Name lastName;
        private Phone phone;
        private Address address;
        private Set<Tag> tags;
        private Sex sex;
        private EmploymentType employmentType;
        private BankDetails bankDetails;
        private WorkHours hoursWorked;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setFirstName(toCopy.firstName);
            setLastName(toCopy.lastName);
            setPhone(toCopy.phone);
            setTags(toCopy.tags);
            setEmploymentType(toCopy.employmentType);
            setSex(toCopy.sex);
            setAddress(toCopy.address);
            setBankDetails(toCopy.bankDetails);
            setHoursWorked(toCopy.hoursWorked);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(firstName, lastName, phone, address, tags, sex, employmentType,
                    bankDetails);
        }

        public Optional<Name> getFirstName() {
            return Optional.ofNullable(firstName);
        }

        public void setFirstName(Name name) {
            this.firstName = name;
        }
        public Optional<Name> getLastName() {
            return Optional.ofNullable(lastName);
        }
        public void setLastName(Name name) {
            this.lastName = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Sex> getSex() {
            return Optional.ofNullable(sex);
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public Optional<BankDetails> getBankDetails() {
            return Optional.ofNullable(bankDetails);
        }
        public void setBankDetails(BankDetails bankDetails) {
            this.bankDetails = bankDetails;
        }

        public Optional<EmploymentType> getEmploymentType() {
            return Optional.ofNullable(employmentType);
        }
        public void setEmploymentType(EmploymentType employmentType) {
            this.employmentType = employmentType;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<WorkHours> getHoursWorked() {
            return Optional.ofNullable(hoursWorked);
        }

        public void setHoursWorked(WorkHours hoursWorked) {
            this.hoursWorked = hoursWorked;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return tags != null ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = tags != null ? new HashSet<>(tags) : null;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(firstName, otherEditPersonDescriptor.firstName)
                    && Objects.equals(lastName, otherEditPersonDescriptor.lastName)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(sex, otherEditPersonDescriptor.sex)
                    && Objects.equals(employmentType, otherEditPersonDescriptor.employmentType)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(bankDetails, otherEditPersonDescriptor.bankDetails)
                    && Objects.equals(hoursWorked, otherEditPersonDescriptor.hoursWorked);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("firstName", firstName)
                    .add("lastName", lastName)
                    .add("phone", phone)
                    .add("sex", sex)
                    .add("employmentType", employmentType)
                    .add("address", address)
                    .add("bankDetails", bankDetails)
                    .add("hoursWorked", hoursWorked)
                    .add("tags", tags)
                    .toString();
        }
    }
}
