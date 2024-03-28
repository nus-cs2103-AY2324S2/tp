package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_GRADE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grade.Grade;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a single detail of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + OPTION_PRINT_NAME + "] or "
            + "[" + OPTION_PRINT_PHONE + "] or "
            + "[" + OPTION_PRINT_EMAIL + "] or "
            + "[" + OPTION_PRINT_TAG + "] or "
            + "[" + OPTION_PRINT_GRADE + "] or "
            + "[" + OPTION_PRINT_ADDRESS + "]\n"
            + "Example: " + COMMAND_WORD + " 1 " + OPTION_PRINT_EMAIL;

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_EDITED_BUT_MORE_THAN_ONE =
            "Please ensure that only one field is edited at most.";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    private final boolean isPrintNameRequested;
    private final boolean isPrintPhoneRequested;
    private final boolean isPrintEmailRequested;
    private final boolean isPrintAddressRequested;
    private final boolean isPrintTagRequested;
    private final boolean isPrintGradeRequested;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor, boolean isPrintNameRequested,
            boolean isPrintPhoneRequested, boolean isPrintEmailRequested, boolean isPrintAddressRequested,
                       boolean isPrintTagRequested, boolean isPrintGradeRequested) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.isPrintNameRequested = isPrintNameRequested;
        this.isPrintPhoneRequested = isPrintPhoneRequested;
        this.isPrintEmailRequested = isPrintEmailRequested;
        this.isPrintAddressRequested = isPrintAddressRequested;
        this.isPrintTagRequested = isPrintTagRequested;
        this.isPrintGradeRequested = isPrintGradeRequested;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (isPrintNameRequested) {
            return new CommandResult(getPersonName(model, index));
        } else if (isPrintPhoneRequested) {
            return new CommandResult(getPersonPhone(model, index));
        } else if (isPrintEmailRequested) {
            return new CommandResult(getPersonEmail(model, index));
        } else if (isPrintAddressRequested) {
            return new CommandResult(getPersonAddress(model, index));
        } else if (isPrintTagRequested) {
            return new CommandResult(getPersonTag(model, index));
        } else if (isPrintGradeRequested) {
            return new CommandResult(getPersonGrade(model, index));
        }

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

    private String getPersonName(Model model, Index index) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        return person.getName().fullName;
    }
    private String getPersonPhone(Model model, Index index) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        return person.getPhone().value;
    }
    private String getPersonEmail(Model model, Index index) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        return person.getEmail().value;
    }
    private String getPersonAddress(Model model, Index index) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        return person.getAddress().value;
    }
    private String getPersonTag(Model model, Index index) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        Set<Tag> tags = person.getTags();

        // Remove curly braces from tags and join them into a comma-separated string
        String tagString = tags.stream()
                .map(tag -> tag.toString().replaceAll("[\\[\\]]", "").trim())
                .collect(Collectors.joining(", "));

        return tagString;
    }


    private String getPersonGrade(Model model, Index index) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        return person.getGrades().toString();
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Grade> updatedGrades = editPersonDescriptor.getGrades().orElse(personToEdit.getGrades());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedGrades);
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
        private Set<Tag> tags;
        private Set<Grade> grades;

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
            setTags(toCopy.tags);
            setGrades(toCopy.grades);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, grades);
        }

        /**
         * Returns true if only one field is edited and false if more than one field is edited.
         */
        public boolean isSingleFieldEdited() {
            int editedFieldCount = 0;

            if (name != null) {
                editedFieldCount++;
            }
            if (phone != null) {
                editedFieldCount++;
            }
            if (email != null) {
                editedFieldCount++;
            }
            if (address != null) {
                editedFieldCount++;
            }
            if (tags != null) {
                editedFieldCount++;
            }
            if (grades != null) {
                editedFieldCount++;
            }

            return editedFieldCount == 1;
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
         * Sets {@code grades} to this object's {@code grades}.
         * A defensive copy of {@code grades} is used internally.
         */
        public void setGrades(Set<Grade> grades) {
            this.grades = (grades != null) ? new HashSet<>(grades) : null;
        }

        /**
         * Returns an unmodifiable grades set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code grades} is null.
         */
        public Optional<Set<Grade>> getGrades() {
            return (grades != null) ? Optional.of(Collections.unmodifiableSet(grades)) : Optional.empty();
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
                    && Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("grades", grades)
                    .toString();
        }
    }
}
