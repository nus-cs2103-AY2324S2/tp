package seedu.internhub.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_INTERN_DURATION;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internhub.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.internhub.commons.core.index.Index;
import seedu.internhub.commons.util.CollectionUtil;
import seedu.internhub.commons.util.ToStringBuilder;
import seedu.internhub.logic.Messages;
import seedu.internhub.logic.commands.exceptions.CommandException;
import seedu.internhub.model.Model;
import seedu.internhub.model.person.Address;
import seedu.internhub.model.person.Email;
import seedu.internhub.model.person.InternDuration;
import seedu.internhub.model.person.InterviewDate;
import seedu.internhub.model.person.JobDescription;
import seedu.internhub.model.person.Name;
import seedu.internhub.model.person.Note;
import seedu.internhub.model.person.Person;
import seedu.internhub.model.person.Phone;
import seedu.internhub.model.person.Salary;
import seedu.internhub.model.person.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_JOB_DESCRIPTION + "JOB DESCRIPTION] "
            + "[" + PREFIX_INTERVIEW_DATE + "INTERVIEW DATE] "
            + "[" + PREFIX_INTERN_DURATION + "INTERN DURATION] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
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

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getCompanyName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        JobDescription updatedJobDescription = editPersonDescriptor
                .getJobDescription()
                .orElse(personToEdit.getJobDescription());
        InterviewDate updatedInterviewDate = editPersonDescriptor
                .getInterviewDate()
                .orElse(personToEdit.getInterviewDate());
        InternDuration updatedInternDuration = editPersonDescriptor
                .getInternDuration()
                .orElse(personToEdit.getInternDuration());
        Salary updatedSalary = editPersonDescriptor.getSalary().orElse(personToEdit.getSalary());
        Tag updatedTags = editPersonDescriptor.getTag().orElse(personToEdit.getTag());

        Note updatedNote = editPersonDescriptor.getNote().orElse(personToEdit.getNote());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedJobDescription, updatedInterviewDate,
                updatedInternDuration, updatedSalary, updatedNote);
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
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Tag tag;
        private JobDescription jobDescription;
        private InternDuration internDuration;
        private InterviewDate interviewDate;
        private Salary salary;
        private Note note;


        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTag(toCopy.tag);
            setJobDescription(toCopy.jobDescription);
            setInternDuration(toCopy.internDuration);
            setInterviewDate(toCopy.interviewDate);
            setSalary(toCopy.salary);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    name, phone, email, address, tag, jobDescription, interviewDate, internDuration, salary, note
            );
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

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setJobDescription(JobDescription jobDescription) {
            this.jobDescription = jobDescription;
        }

        public Optional<JobDescription> getJobDescription() {
            return Optional.ofNullable(jobDescription);
        }

        public void setInternDuration(InternDuration internDuration) {
            this.internDuration = internDuration;
        }

        public Optional<InternDuration> getInternDuration() {
            return Optional.ofNullable(internDuration);
        }

        public void setInterviewDate(InterviewDate interviewDate) {
            this.interviewDate = interviewDate;
        }

        public Optional<InterviewDate> getInterviewDate() {
            return Optional.ofNullable(interviewDate);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }

        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }
        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
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
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(tag, otherEditPersonDescriptor.tag)
                    && Objects.equals(jobDescription, otherEditPersonDescriptor.jobDescription)
                    && Objects.equals(internDuration, otherEditPersonDescriptor.internDuration)
                    && Objects.equals(interviewDate, otherEditPersonDescriptor.interviewDate)
                    && Objects.equals(salary, otherEditPersonDescriptor.salary);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tag", tag)
                    .add("job description", jobDescription)
                    .add("intern duration", internDuration)
                    .add("interview date", interviewDate)
                    .add("salary", salary)
                    .add("note", note)
                    .toString();
        }
    }
}
