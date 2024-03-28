package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_GRADE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_TIMESLOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

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
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.timeslots.Timeslots;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a single detail of the student identified "
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + OPTION_PRINT_NAME + "] or "
            + "[" + OPTION_PRINT_PHONE + "] or "
            + "[" + OPTION_PRINT_EMAIL + "] or "
            + "[" + OPTION_PRINT_ADDRESS + "] or "
            + "[" + OPTION_PRINT_TIMESLOT + "] or "
            + "[" + OPTION_PRINT_GRADE + "]\n"
            + "Example: " + COMMAND_WORD + " 1 " + OPTION_PRINT_EMAIL;

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_EDITED_BUT_MORE_THAN_ONE =
            "Please ensure that only one field is edited at most.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    private final boolean isPrintNameRequested;
    private final boolean isPrintPhoneRequested;
    private final boolean isPrintEmailRequested;
    private final boolean isPrintAddressRequested;
    private final boolean isPrintTimeslotRequested;
    private final boolean isPrintGradeRequested;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor, boolean isPrintNameRequested,
            boolean isPrintPhoneRequested, boolean isPrintEmailRequested, boolean isPrintAddressRequested,
                       boolean isPrintTimeslotRequested, boolean isPrintGradeRequested) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
        this.isPrintNameRequested = isPrintNameRequested;
        this.isPrintPhoneRequested = isPrintPhoneRequested;
        this.isPrintEmailRequested = isPrintEmailRequested;
        this.isPrintAddressRequested = isPrintAddressRequested;
        this.isPrintTimeslotRequested = isPrintTimeslotRequested;
        this.isPrintGradeRequested = isPrintGradeRequested;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (isPrintNameRequested) {
            return new CommandResult(getStudentName(model, index));
        } else if (isPrintPhoneRequested) {
            return new CommandResult(getStudentPhone(model, index));
        } else if (isPrintEmailRequested) {
            return new CommandResult(getStudentEmail(model, index));
        } else if (isPrintAddressRequested) {
            return new CommandResult(getStudentAddress(model, index));
        } else if (isPrintTimeslotRequested) {
            return new CommandResult(getStudentTimeslot(model, index));
        } else if (isPrintGradeRequested) {
            return new CommandResult(getStudentGrade(model, index));
        }

        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedStudent)));
    }

    private String getStudentName(Model model, Index index) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(index.getZeroBased());
        return student.getName().fullName;
    }
    private String getStudentPhone(Model model, Index index) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(index.getZeroBased());
        return student.getPhone().value;
    }
    private String getStudentEmail(Model model, Index index) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(index.getZeroBased());
        return student.getEmail().value;
    }
    private String getStudentAddress(Model model, Index index) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(index.getZeroBased());
        return student.getAddress().value;
    }
    private String getStudentTimeslot(Model model, Index index) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(index.getZeroBased());
        Set<Timeslots> timeslots = student.getTimeslots();

        // Remove curly braces from timeslots and join them into a comma-separated string
        String timeslotString = timeslots.stream()
                .map(timeslot -> timeslot.toString().replaceAll("[\\[\\]]", "").trim())
                .collect(Collectors.joining(", "));

        return timeslotString;
    }

    private String getStudentGrade(Model model, Index index) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(index.getZeroBased());
        return student.getGrades().toString();
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Set<Timeslots> updatedTimeslots = editStudentDescriptor.getTimeslots().orElse(studentToEdit.getTimeslots());
        Set<Grade> updatedGrades = editStudentDescriptor.getGrades().orElse(studentToEdit.getGrades());

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTimeslots, updatedGrades);
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
                && editStudentDescriptor.equals(otherEditCommand.editStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editStudentDescriptor", editStudentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Timeslots> timeslots;
        private Set<Grade> grades;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code timeslots} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTimeslots(toCopy.timeslots);
            setGrades(toCopy.grades);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, timeslots, grades);
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
            if (timeslots != null) {
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
         * Sets {@code timeslots} to this object's {@code timeslots}.
         * A defensive copy of {@code timeslots} is used internally.
         */
        public void setTimeslots(Set<Timeslots> timeslots) {
            this.timeslots = (timeslots != null) ? new HashSet<>(timeslots) : null;
        }

        /**
         * Returns an unmodifiable timeslot set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code timeslots} is null.
         */
        public Optional<Set<Timeslots>> getTimeslots() {
            return (timeslots != null) ? Optional.of(Collections.unmodifiableSet(timeslots)) : Optional.empty();
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
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            EditStudentDescriptor otherEditStudentDescriptor = (EditStudentDescriptor) other;
            return Objects.equals(name, otherEditStudentDescriptor.name)
                    && Objects.equals(phone, otherEditStudentDescriptor.phone)
                    && Objects.equals(email, otherEditStudentDescriptor.email)
                    && Objects.equals(address, otherEditStudentDescriptor.address)
                    && Objects.equals(timeslots, otherEditStudentDescriptor.timeslots);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("timeslots", timeslots)
                    .add("grades", grades)
                    .toString();
        }
    }
}
