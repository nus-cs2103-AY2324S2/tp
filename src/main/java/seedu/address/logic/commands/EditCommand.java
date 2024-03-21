package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TERMSOFSERVICE;
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
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employee;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Skills;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.TermsOfService;
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
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT] "
            + "[" + PREFIX_JOBTITLE + "JOBTITLE] "
            + "[" + PREFIX_SKILLS + "SKILLS] "
            + "[" + PREFIX_PRODUCTS + "PRODUCTS] "
            + "[" + PREFIX_TERMSOFSERVICE + "TERMSOFSERVICE] "
            + "[" + PREFIX_PREFERENCES + "PREFERENCES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
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
    static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemark = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        if (personToEdit instanceof Client) {
            if (editPersonDescriptor.getDepartment().isPresent() || editPersonDescriptor.getJobTitle().isPresent()
                    || editPersonDescriptor.getSkills().isPresent()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_PROPERTY);
            }
            String updatedPreferences = editPersonDescriptor.getPreferences()
                    .orElse(((Client) personToEdit).getPreferences());
            Products updatedProducts = editPersonDescriptor.getProducts().orElse(((Client) personToEdit).getProducts());
            return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark, updatedTags,
                    updatedProducts, updatedPreferences);
        } else if (personToEdit instanceof Employee) {
            if (editPersonDescriptor.getPreferences().isPresent() || editPersonDescriptor.getProducts().isPresent()
                    || editPersonDescriptor.getTermsOfService().isPresent()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_PROPERTY);
            }
            Department updatedDepartment = editPersonDescriptor.getDepartment()
                    .orElse(((Employee) personToEdit).getDepartment());
            JobTitle updatedJobTitle = editPersonDescriptor.getJobTitle()
                    .orElse(((Employee) personToEdit).getJobTitle());
            Skills updatedSkills = editPersonDescriptor.getSkills().orElse(((Employee) personToEdit).getSkills());
            return new Employee(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark, updatedTags,
                    updatedDepartment, updatedJobTitle, updatedSkills);
        } else if (personToEdit instanceof Supplier) {
            if (editPersonDescriptor.getDepartment().isPresent() || editPersonDescriptor.getJobTitle().isPresent()
                    || editPersonDescriptor.getSkills().isPresent()) {
                throw new CommandException(Messages.MESSAGE_INVALID_SUPPLIER_PROPERTY);
            }
            TermsOfService updatedTermsOfService = editPersonDescriptor.getTermsOfService()
                    .orElse(((Supplier) personToEdit).getTermsOfService());
            Products updatedProducts = editPersonDescriptor.getProducts()
                    .orElse(((Supplier) personToEdit).getProducts());
            return new Supplier(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark, updatedTags,
                    updatedProducts, updatedTermsOfService);
        }

        return personToEdit;
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
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Remark remark;
        private Department department;
        private JobTitle jobTitle;
        private Skills skills;
        private Products products;
        private TermsOfService termsOfService;
        private String preferences;

        public EditPersonDescriptor() {
        }

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
            setRemark(toCopy.remark);
            setDepartment(toCopy.department);
            setJobTitle(toCopy.jobTitle);
            setSkills(toCopy.skills);
            setProducts(toCopy.products);
            setTermsOfService(toCopy.termsOfService);
            setPreferences(toCopy.preferences);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, remark, department, jobTitle, skills,
                    products, termsOfService, preferences);
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
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public Optional<Department> getDepartment() {
            return Optional.ofNullable(department);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setSkills(Skills skills) {
            this.skills = skills;
        }

        public Optional<Skills> getSkills() {
            return Optional.ofNullable(skills);
        }

        public void setProducts(Products products) {
            this.products = products;
        }

        public Optional<Products> getProducts() {
            return Optional.ofNullable(products);
        }

        public void setTermsOfService(TermsOfService termsOfService) {
            this.termsOfService = termsOfService;
        }

        public Optional<TermsOfService> getTermsOfService() {
            return Optional.ofNullable(termsOfService);
        }

        public void setPreferences(String preferences) {
            this.preferences = preferences;
        }

        public Optional<String> getPreferences() {
            return Optional.ofNullable(preferences);
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
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(remark, otherEditPersonDescriptor.remark)
                    && Objects.equals(department, otherEditPersonDescriptor.department)
                    && Objects.equals(jobTitle, otherEditPersonDescriptor.jobTitle)
                    && Objects.equals(skills, otherEditPersonDescriptor.skills)
                    && Objects.equals(products, otherEditPersonDescriptor.products)
                    && Objects.equals(termsOfService, otherEditPersonDescriptor.termsOfService)
                    && Objects.equals(preferences, otherEditPersonDescriptor.preferences);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("remark", remark)
                    .add("department", department)
                    .add("jobTitle", jobTitle)
                    .add("skills", skills)
                    .add("products", products)
                    .add("termsOfService", termsOfService)
                    .add("preferences", preferences)
                    .toString();
        }
    }
}
