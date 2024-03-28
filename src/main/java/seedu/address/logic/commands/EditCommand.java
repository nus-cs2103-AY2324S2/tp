package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
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
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Info;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY NAME] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_INTERVIEWTIME + "INTERVIEW-TIME] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_INFO + "INFO] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_PROGRAMMING_LANGUAGE + "PROGRAMMING-LANGUAGE]...\n"
            + "[" + PREFIX_PRIORITY + "PRIORITY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;
        CompanyName updatedCompanyName = editPersonDescriptor.getCompanyName().orElse(personToEdit.getCompanyName());
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        InterviewTime updatedDateTime = editPersonDescriptor.getDateTime().orElse(personToEdit.getDateTime());
        Salary updatedSalary = editPersonDescriptor.getSalary().orElse(personToEdit.getSalary());
        Info updatedInfo = editPersonDescriptor.getInfo().orElse(personToEdit.getInfo());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<ProgrammingLanguage> updatedProgrammingLanguages = editPersonDescriptor.getProgrammingLanguages()
                .orElse(personToEdit.getProgrammingLanguages());
        int updatedPriority = editPersonDescriptor.getPriority().orElse(personToEdit.getPriority());
        return new Person(
                updatedCompanyName, updatedName, updatedPhone, updatedEmail,
                updatedAddress, updatedDateTime, updatedSalary, updatedInfo,
                updatedTags, updatedProgrammingLanguages, updatedPriority);
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
        System.out.println("EditCommand equals");
        System.out.println("index: " + index + " other index: " + ((EditCommand) other).index);
        System.out.println("editPersonDescriptor: " + editPersonDescriptor + " other editPersonDescriptor: "
                + ((EditCommand) other).editPersonDescriptor);
        System.out.println(index.equals(otherEditCommand.index));
        System.out.println(editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor));
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private CompanyName companyName;
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private InterviewTime dateTime;
        private Salary salary;
        private Info info;
        private Set<Tag> tags;
        private Set<ProgrammingLanguage> programmingLanguages;
        private Integer priority;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setCompanyName(toCopy.companyName);
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setDateTime(toCopy.dateTime);
            setSalary(toCopy.salary);
            setInfo(toCopy.info);
            setTags(toCopy.tags);
            setProgrammingLanguages(toCopy.programmingLanguages);
            setPriority(toCopy.priority);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, dateTime, salary, info, tags,
                    programmingLanguages, priority);
        }
        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public void setName(Name name) {
            this.name = name;
        }
        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
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

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }
        public void setDateTime(InterviewTime dateTime) {
            this.dateTime = dateTime;
        }
        public Optional<InterviewTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setInfo(Info info) {
            this.info = info; }

        public Optional<Info> getInfo() {
            return Optional.ofNullable(info); }

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

        /**
         * Sets {@code programmingLanguages} to this object's {@code programmingLanguages}.
         * A defensive copy of {@code programmingLanguages} is used internally.
         */
        public void setProgrammingLanguages(Set<ProgrammingLanguage> programmingLanguages) {
            this.programmingLanguages = (programmingLanguages != null) ? new HashSet<>(programmingLanguages) : null;
        }

        /**
         * Returns an unmodifiable programmingLanguages set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code programmingLanguages} is null.
         */
        public Optional<Set<ProgrammingLanguage>> getProgrammingLanguages() {
            return (programmingLanguages != null) ? Optional.of(Collections
                    .unmodifiableSet(programmingLanguages)) : Optional.empty();
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Optional<Integer> getPriority() {
            return Optional.ofNullable(priority);
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
            return Objects.equals(companyName, otherEditPersonDescriptor.companyName)
                    && Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(dateTime, otherEditPersonDescriptor.dateTime)
                    && Objects.equals(salary, otherEditPersonDescriptor.salary)
                    && Objects.equals(info, otherEditPersonDescriptor.info)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(programmingLanguages, otherEditPersonDescriptor.programmingLanguages)
                    && Objects.equals(priority, otherEditPersonDescriptor.priority);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("company name", companyName)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("dateTime", dateTime)
                    .add("salary", salary)
                    .add("info", info)
                    .add("tags", tags)
                    .add("programmingLanguages", programmingLanguages)
                    .add("priority", priority)
                    .toString();
        }


    }
}
