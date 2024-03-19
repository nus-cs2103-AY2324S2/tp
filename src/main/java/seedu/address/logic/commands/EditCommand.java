package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

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
import seedu.address.model.employee.Address;
import seedu.address.model.employee.AssignedTasks;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing employee in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by the index number used in the displayed employee list. "
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

    public static final String MESSAGE_EDIT_EMPLOYEE_SUCCESS = "Edited Employee: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in the address book.";

    private final Index index;
    private final EditEmployeeDescriptor editEmployeeDescriptor;

    /**
     * @param index of the employee in the filtered employee list to edit
     * @param editEmployeeDescriptor details to edit the employee with
     */
    public EditCommand(Index index, EditEmployeeDescriptor editEmployeeDescriptor) {
        requireNonNull(index);
        requireNonNull(editEmployeeDescriptor);

        this.index = index;
        this.editEmployeeDescriptor = new EditEmployeeDescriptor(editEmployeeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEEID);
        }

        Employee employeeToEdit = lastShownList.get(index.getZeroBased());
        Employee editedEmployee = createEditedEmployee(employeeToEdit, editEmployeeDescriptor);

        if (!employeeToEdit.isSameEmployee(editedEmployee) && model.hasEmployee(editedEmployee)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.setEmployee(employeeToEdit, editedEmployee);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(String.format(MESSAGE_EDIT_EMPLOYEE_SUCCESS, Messages.format(editedEmployee)));
    }

    /**
     * Creates and returns a {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private static Employee createEditedEmployee(Employee employeeToEdit,
                                                 EditEmployeeDescriptor editEmployeeDescriptor) {
        assert employeeToEdit != null;

        Name updatedName = editEmployeeDescriptor.getName().orElse(employeeToEdit.getName());
        Phone updatedPhone = editEmployeeDescriptor.getPhone().orElse(employeeToEdit.getPhone());
        Email updatedEmail = editEmployeeDescriptor.getEmail().orElse(employeeToEdit.getEmail());
        Address updatedAddress = editEmployeeDescriptor.getAddress().orElse(employeeToEdit.getAddress());
        AssignedTasks updatedTask = editEmployeeDescriptor.getTasks().orElse(employeeToEdit.getTasks());
        Set<Tag> updatedTags = editEmployeeDescriptor.getTags().orElse(employeeToEdit.getTags());

        return new Employee(employeeToEdit.getEmployeeId(), updatedName, updatedPhone, updatedEmail,
                updatedAddress, updatedTask, updatedTags);
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
                && editEmployeeDescriptor.equals(otherEditCommand.editEmployeeDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editEmployeeDescriptor", editEmployeeDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the employee with. Each non-empty field value will replace the
     * corresponding field value of the employee.
     */
    public static class EditEmployeeDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private AssignedTasks tasks;
        private Set<Tag> tags;

        public EditEmployeeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEmployeeDescriptor(EditEmployeeDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTasks(toCopy.tasks);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tasks, tags);
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

        public void setTasks(AssignedTasks tasks) {
            this.tasks = tasks;
        }

        public Optional<AssignedTasks> getTasks() {
            return Optional.ofNullable(tasks);
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
            if (!(other instanceof EditEmployeeDescriptor)) {
                return false;
            }

            EditEmployeeDescriptor otherEditEmployeeDescriptor = (EditEmployeeDescriptor) other;
            return Objects.equals(name, otherEditEmployeeDescriptor.name)
                    && Objects.equals(phone, otherEditEmployeeDescriptor.phone)
                    && Objects.equals(email, otherEditEmployeeDescriptor.email)
                    && Objects.equals(address, otherEditEmployeeDescriptor.address)
                    && Objects.equals(tasks, otherEditEmployeeDescriptor.tasks)
                    && Objects.equals(tags, otherEditEmployeeDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tasks", tasks)
                    .add("tags", tags)
                    .toString();
        }


    }
}
