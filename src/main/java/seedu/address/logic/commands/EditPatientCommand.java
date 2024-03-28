package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.DateOfBirth;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing patient in CLInic.
 */
public class EditPatientCommand extends Command {

    public static final String COMMAND_WORD = "editPatient";

    public static final String COMMAND_WORD_ALT = "ep";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the patient identified by their Nric number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: NRIC (must be a valid NRIC in the system) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DOB + "DOB] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " T0123456A "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_EDIT_PATIENT_NO_FIELDS_FAILURE =
            "At least one field to edit must be provided.";

    private final Nric targetNric;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param nric of the patient to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditPatientCommand(Nric nric, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(nric);
        requireNonNull(editPatientDescriptor);

        this.targetNric = nric;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPatientWithNric(targetNric)) {
            throw new CommandException(Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
        }

        Patient patientToEdit = model.getPatientWithNric(targetNric);
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);
        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, Messages.format(editedPatient)));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        DateOfBirth updatedDob = editPatientDescriptor.getDateOfBirth().orElse(patientToEdit.getDob());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Address updatedAddress = editPatientDescriptor.getAddress().orElse(patientToEdit.getAddress());
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(patientToEdit.getTags());

        return new Patient(updatedName, patientToEdit.getNric(), updatedDob, updatedPhone,
                updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientCommand)) {
            return false;
        }

        EditPatientCommand otherEditPatientCommand = (EditPatientCommand) other;
        return targetNric.equals(otherEditPatientCommand.targetNric)
                && editPatientDescriptor.equals(otherEditPatientCommand.editPatientDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nric", targetNric)
                .add("editPatientDescriptor", editPatientDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     * Nric cannot be edited.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private DateOfBirth dob;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditPatientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setDateOfBirth(toCopy.dob);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, dob, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDateOfBirth(DateOfBirth dob) {
            this.dob = dob;
        }

        public Optional<DateOfBirth> getDateOfBirth() {
            return Optional.ofNullable(dob);
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

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            EditPatientDescriptor otherEditPatientDescriptor = (EditPatientDescriptor) other;
            return Objects.equals(name, otherEditPatientDescriptor.name)
                    && Objects.equals(dob, otherEditPatientDescriptor.dob)
                    && Objects.equals(phone, otherEditPatientDescriptor.phone)
                    && Objects.equals(email, otherEditPatientDescriptor.email)
                    && Objects.equals(address, otherEditPatientDescriptor.address)
                    && Objects.equals(tags, otherEditPatientDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("dob", dob)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .toString();
        }
    }
}
