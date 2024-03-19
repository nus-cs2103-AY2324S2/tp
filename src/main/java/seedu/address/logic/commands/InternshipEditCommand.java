package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.InternshipModel.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Remark;
import seedu.address.model.internship.Role;

/**
 * Edits the details of an existing internship in the internship data.
 */
public class InternshipEditCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship data. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_CONTACT_NAME + " CONTACT_NAME] "
            + "[" + PREFIX_CONTACT_EMAIL + " CONTACT_EMAIL] "
            + "[" + PREFIX_CONTACT_NUMBER + " CONTACT_NUMBER] "
            + "[" + PREFIX_LOCATION + " LOCATION] "
            + "[" + PREFIX_STATUS + " STATUS] "
            + "[" + PREFIX_DESCRIPTION + " DESCRIPTION] "
            + "[" + PREFIX_ROLE + " ROLE] "
            + "[" + PREFIX_REMARK + " REMARK] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CONTACT_EMAIL + " john@example.com"
            + PREFIX_CONTACT_NUMBER + "9666 1666";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This entry already exists in the internship data.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param index of the internship in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public InternshipEditCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternshipDescriptor);

        this.index = index;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                InternshipMessages.format(editedInternship)));
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
                                                     EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        CompanyName updatedCompanyName = editInternshipDescriptor.getCompanyName()
                .orElse(internshipToEdit.getCompanyName());
        Location updatedLocation = editInternshipDescriptor.getLocation()
                .orElse(internshipToEdit.getLocation());
        Description updatedDescription = editInternshipDescriptor.getDescription()
                .orElse(internshipToEdit.getDescription());
        Role updatedRole = editInternshipDescriptor.getRole().orElse(internshipToEdit
                .getRole());
        ContactName updatedContactName = editInternshipDescriptor.getContactName()
                .orElse(internshipToEdit.getContactName());
        ContactEmail updatedContactEmail = editInternshipDescriptor.getContactEmail()
                .orElse(internshipToEdit.getContactEmail());
        ContactNumber updatedContactNumber = editInternshipDescriptor.getContactNumber()
                .orElse(internshipToEdit.getContactNumber());
        ApplicationStatus updatedApplicationStatus = editInternshipDescriptor.getApplicationStatus()
                .orElse(internshipToEdit.getApplicationStatus());
        Remark updatedRemark = editInternshipDescriptor.getRemark().orElse(internshipToEdit.getRemark());
        return new Internship(updatedCompanyName, updatedContactName, updatedContactEmail, updatedContactNumber,
                updatedLocation, updatedApplicationStatus, updatedDescription, updatedRole, updatedRemark);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipEditCommand)) {
            return false;
        }

        InternshipEditCommand otherEditCommand = (InternshipEditCommand) other;
        return index.equals(otherEditCommand.index)
                && editInternshipDescriptor.equals(otherEditCommand.editInternshipDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editInternshipDescriptor", editInternshipDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the internship with. Each non-empty field value will replace the
     * corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private CompanyName companyName;
        private Location location;
        private Description description;
        private Role role;

        private ContactName contactName;
        private ContactEmail contactEmail;
        private ContactNumber contactNumber;
        private ApplicationStatus applicationStatus;
        private Remark remark;

        /**
         * Copy constructor.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompanyName(toCopy.companyName);
            setLocation(toCopy.location);
            setDescription(toCopy.description);
            setRole(toCopy.role);
            setContactName(toCopy.contactName);
            setContactEmail(toCopy.contactEmail);
            setContactNumber(toCopy.contactNumber);
            setApplicationStatus(toCopy.applicationStatus);
            setRemark(toCopy.remark);
        }

        public EditInternshipDescriptor() {}

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, location, description, role, contactName,
                    contactEmail, contactNumber, applicationStatus, remark);
        }
        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }
        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }
        public void setLocation(Location location) {
            this.location = location;
        }
        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }
        public void setDescription(Description description) {
            this.description = description;
        }
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }
        public void setRole(Role role) {
            this.role = role;
        }
        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }
        public void setContactName(ContactName contactName) {
            this.contactName = contactName;
        }
        public Optional<ContactName> getContactName() {
            return Optional.ofNullable(contactName);
        }
        public void setContactEmail(ContactEmail contactEmail) {
            this.contactEmail = contactEmail;
        }
        public Optional<ContactEmail> getContactEmail() {
            return Optional.ofNullable(contactEmail);
        }
        public void setContactNumber(ContactNumber contactNumber) {
            this.contactNumber = contactNumber;
        }
        public Optional<ContactNumber> getContactNumber() {
            return Optional.ofNullable(contactNumber);
        }
        public void setApplicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
        }
        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(applicationStatus);
        }
        public void setRemark(Remark remark) {
            this.remark = remark;
        }
        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            EditInternshipDescriptor otherEditInternshipDescriptor = (EditInternshipDescriptor) other;
            return Objects.equals(companyName, otherEditInternshipDescriptor.companyName)
                    && Objects.equals(location, otherEditInternshipDescriptor.location)
                    && Objects.equals(description, otherEditInternshipDescriptor.description)
                    && Objects.equals(role, otherEditInternshipDescriptor.role)
                    && Objects.equals(contactName, otherEditInternshipDescriptor.contactName)
                    && Objects.equals(contactEmail, otherEditInternshipDescriptor.contactEmail)
                    && Objects.equals(contactNumber, otherEditInternshipDescriptor.contactNumber)
                    && Objects.equals(applicationStatus, otherEditInternshipDescriptor.applicationStatus)
                    && Objects.equals(remark, otherEditInternshipDescriptor.remark);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("Company Name", companyName)
                    .add("Location", location)
                    .add("Description", description)
                    .add("Role", role)
                    .add("Contact Name", contactName)
                    .add("Contact Email", contactEmail)
                    .add("Contact Number", contactNumber)
                    .add("Application Status", applicationStatus)
                    .add("Remark", remark)
                    .toString();
        }
    }
}
