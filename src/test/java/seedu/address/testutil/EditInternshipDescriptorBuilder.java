package seedu.address.testutil;

import seedu.address.logic.commands.InternshipEditCommand.EditInternshipDescriptor;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {

    private EditInternshipDescriptor descriptor;

    public EditInternshipDescriptorBuilder() {
        descriptor = new EditInternshipDescriptor();
    }

    public EditInternshipDescriptorBuilder(EditInternshipDescriptor descriptor) {
        this.descriptor = new EditInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code person}'s details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditInternshipDescriptor();
        descriptor.setCompanyName(internship.getCompanyName());
        descriptor.setLocation(internship.getLocation());
        descriptor.setDescription(internship.getDescription());
        descriptor.setRole(internship.getRole());
        descriptor.setContactName(internship.getContactName());
        descriptor.setContactEmail(internship.getContactEmail());
        descriptor.setContactNumber(internship.getContactNumber());
        descriptor.setApplicationStatus(internship.getApplicationStatus());
    }

    /**
     * Sets the {@code companyName} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code location} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code description} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code role} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code contactName} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withContactName(String contactName) {
        descriptor.setContactName(new ContactName(contactName));
        return this;
    }

    /**
     * Sets the {@code contactEmail} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withContactEmail(String contactEmail) {
        descriptor.setContactEmail(new ContactEmail(contactEmail));
        return this;
    }

    /**
     * Sets the {@code contactNumber} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withContactNumber(String contactNumber) {
        descriptor.setContactNumber(new ContactNumber(contactNumber));
        return this;
    }

    /**
     * Sets the {@code applicationStatus} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withApplicationStatus(String applicationStatus) {
        descriptor.setApplicationStatus(new ApplicationStatus(applicationStatus));
        return this;
    }

    public EditInternshipDescriptor build() {
        return descriptor;
    }
}
